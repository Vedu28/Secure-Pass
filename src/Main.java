
import database.DatabaseManager;
import database.DatabaseSearch;
import database.DatabaseStatistics;
import database.DatabaseViewer;
import java.util.ArrayList;
import java.util.Scanner;
import service.BreachChecker;
import service.CommonPasswordChecker;
import service.EntropyCalculator;
import service.PasswordAnalyzer;
import service.PasswordGenerator;
import service.PasswordHasher;
import service.SuggestionEngine;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("1. Analyze Password");
            System.out.println("2. View Saved Records");
            System.out.println("3. Generate Strong Password");
            System.out.println("4. View Statistics");
            System.out.println("5. Search by Strength");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:

                    System.out.print("\nEnter Password: ");
                    String password = sc.nextLine();

                    PasswordAnalyzer analyzer
                            = new PasswordAnalyzer();

                    SuggestionEngine engine
                            = new SuggestionEngine();

                    CommonPasswordChecker checker
                            = new CommonPasswordChecker();

                    int score
                            = analyzer.calculateScore(password);

                    String strength
                            = analyzer.getStrength(score);

                    BreachChecker breachChecker
                            = new BreachChecker();

                    boolean breached
                            = breachChecker.isBreached(password);
                    if (breached) {

                        System.out.println(
                                "WARNING: Password found in breach list!"
                        );
                    }

                    EntropyCalculator entropyCalculator
                            = new EntropyCalculator();

                    double entropy
                            = entropyCalculator.calculateEntropy(password);

                    ArrayList<String> suggestions
                            = engine.generateSuggestions(password);

                    System.out.println(
                            "\n===== PASSWORD ANALYSIS REPORT =====");

                    System.out.println("Score      : " + score);
                    System.out.println("Strength   : " + strength);
                    System.out.printf(
                            "Entropy   : %.2f bits%n",
                            entropy
                    );

                    if (checker.isCommonPassword(password)) {

                        System.out.println(
                                "\nWARNING: This is a commonly used password.");
                    }

                    System.out.println("\nSuggestions:");

                    if (suggestions.isEmpty()) {

                        System.out.println(
                                "Password is already strong.");

                    } else {

                        for (String suggestion : suggestions) {

                            System.out.println(
                                    "- " + suggestion);
                        }
                    }

                    PasswordHasher hasher
                            = new PasswordHasher();

                    String hash
                            = hasher.hashPassword(password);

                    System.out.println("\nSHA-256 Hash:");
                    System.out.println(hash);

                    DatabaseManager db
                            = new DatabaseManager();

                    db.saveRecord(
                            score,
                            strength,
                            entropy,
                            hash
                    );

                    break;

                case 2: {

                    DatabaseViewer viewer
                            = new DatabaseViewer();

                    viewer.viewRecords();

                    break;
                }

                case 3:

                    PasswordGenerator generator
                            = new PasswordGenerator();

                    String generatedPassword
                            = generator.generatePassword(12);

                    System.out.println(
                            "\nGenerated Password: "
                            + generatedPassword
                    );

                    PasswordAnalyzer analyzerr
                            = new PasswordAnalyzer();

                    int scoree
                            = analyzerr.calculateScore(generatedPassword);

                    String strengthh
                            = analyzerr.getStrength(scoree);

                    EntropyCalculator entropyCalculatorr
                            = new EntropyCalculator();

                    double entropyy
                            = entropyCalculatorr.calculateEntropy(
                                    generatedPassword
                            );

                    System.out.println("Score: " + scoree);
                    System.out.println("Strength: " + strengthh);
                    System.out.printf(
                            "Entropy: %.2f bits%n",
                            entropyy
                    );

                    break;

                case 4: {

                    DatabaseStatistics stats
                            = new DatabaseStatistics();

                    stats.showStatistics();

                    break;
                }

                case 5: {

                    System.out.print(
                            "Enter Strength (Weak/Medium/Strong): "
                    );

                    String strengthInput
                            = sc.nextLine();

                    DatabaseSearch search
                            = new DatabaseSearch();

                    search.searchByStrength(
                            strengthInput
                    );

                    break;
                }

                case 6:

                    running = false;

                    System.out.println(
                            "\nThank you for using Password Strength Analyzer.");

                    break;

                default:

                    System.out.println(
                            "\nInvalid Choice. Try Again.");
            }
        }

        sc.close();
    }
}
