package org.fitznet;

import lombok.extern.slf4j.Slf4j;
import org.fitznet.dto.Transaction;
import org.fitznet.util.FileUtil;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBeanBuilder;

@Slf4j
public class Main {
    public static final List<String> MERCHANT_LIST = List.of("LOWE'S", "TARGET");
    static List<Transaction> filteredTransactions;

    public static void main(String[] args) {
        log.info("Starting Application.");

        if (args.length < 1) {
            log.error("No arguments found, exiting application.");
            throw new IllegalArgumentException("No arguments found to process. Use this syntax programName" +
                    " fileName1 filename2 ");
        }

        validateFilesExist(args);
        
        processFiles(args);
        
        printTotals();
    }

    private static void printTotals() {
        if (filteredTransactions == null || filteredTransactions.isEmpty()) {
            log.info("No filtered transactions available to total.");
            return;
        }

        BigDecimal totalAmount = filteredTransactions.stream()
                .map(transaction -> BigDecimal.valueOf(Double.parseDouble(transaction.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal splitAmount = totalAmount.divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP);

        // Displaying the total and split
        log.info(String.format("Total amount for all filtered transactions: $%,.2f", totalAmount));
        log.info(String.format("Each party's share in a 50/50 split: $%,.2f", splitAmount));

        // Calculating and displaying the total for each merchant
        Map<String, BigDecimal> totalByMerchant = filteredTransactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getDescription, // Assuming getDescription gives the merchant name
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                transaction -> BigDecimal.valueOf(Double.parseDouble(transaction.getAmount())),
                                BigDecimal::add)));

        totalByMerchant.forEach((merchant, amount) -> log.info(String.format("Total for %s: $%,.2f", merchant, amount)));
    }


    private static void processFiles(String[] args) {
        Arrays.stream(args).forEach(fileName -> {
            log.info("Processing file: {}", fileName);
            try (InputStreamReader reader = new InputStreamReader(
                    Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(fileName)),
                    StandardCharsets.UTF_8)) {

                List<Transaction> transactions = new CsvToBeanBuilder<Transaction>(reader)
                        .withType(Transaction.class)
                        .build()
                        .parse();

                // For each transaction that matches the MERCHANT_LIST we will collect them
                transactions.forEach(transaction -> {

                });

                filteredTransactions = transactions.stream()
                        .filter(transaction -> containsMerchant(transaction.getDescription().toUpperCase()))
                        .collect(Collectors.toList());

                System.out.println();
            } catch (Exception e) {
                log.error("Error processing file {}: {}", fileName, e.getMessage());
            }
        });
    }

    public static void validateFilesExist(String[] args){
        List<String> fileNameList = Arrays.stream(args).toList();
        fileNameList.forEach(FileUtil::readResource);
        log.info("All files validated: {}", Arrays.toString(fileNameList.toArray()));
    }

    private static boolean containsMerchant(String description) {
        for (String merchant : MERCHANT_LIST) {
            if (description.contains(merchant.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}