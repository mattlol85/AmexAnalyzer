# AmexAnalyzer

AmexAnalyzer is a Java-based application designed to process and analyze American Express credit card statements in CSV
format. The tool reads CSV files, marshals the data into POJOs (Plain Old Java Objects), and allows for various
interactions with the transaction data.

## Features

- Read and parse CSV files containing Amex credit card transactions.
- Map CSV data to Java POJOs for easy data manipulation.
- Filter and analyze transactions based on different criteria.
- (Add more features specific to AmexAnalyzer here)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes.

### Prerequisites

- Java JDK 11 or later
- Gradle (for building the project)

### Installation

1. Clone the repository to your local machine:

   ```sh
   git clone https://github.com/your-username/AmexAnalyzer.git

2. Navigate to the project directory:
   ```sh
   cd AmexAnalyzer

3. Build the project using Gradle:
    ```sh
   ./gradlew clean build

## Usage

To use AmexAnalyzer, follow these steps:

```sh
java -jar build/libs/AmexAnalyzer.jar "path/to/your/statement.csv"
```

Replace `"path/to/your/statement.csv"` with the actual path to your CSV file.

## Contributing to AmexAnalyzer

To contribute to AmexAnalyzer, follow these steps:

1. Fork this repository.
2. Create a new branch: `git checkout -b <branch_name>`.
3. Make your changes and commit them: `git commit -m '<commit_message>'`.
4. Push to the original branch: `git push origin <project_name>/<location>`.
5. Create the pull request.

Alternatively, see the GitHub documentation
on [creating a pull request](https://help.github.com/articles/creating-a-pull-request/).

## Contact

If you want to contact me, you can reach me at <mattfitzgerald85@gmail.com>.

## License

This project uses the following license: [MIT License](https://opensource.org/licenses/MIT).

