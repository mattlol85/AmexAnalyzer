package org.fitznet.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    String date;
    String description;
    String amount;
    String extendedDetails;
    String nameThatAppearsOnStatement;
    String address;
    String cityOrState;
    String zipCode;
    String country;
    String reference;
    String category;
}
