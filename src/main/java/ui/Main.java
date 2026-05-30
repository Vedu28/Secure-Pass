package ui;

import database.DatabaseManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.EntropyCalculator;
import service.PasswordAnalyzer;
import service.PasswordGenerator;
import service.PasswordHasher;
import service.SuggestionEngine;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Label title
                = new Label("Password Strength Analyzer");

        Label passwordLabel
                = new Label("Password:");

        PasswordField passwordField
                = new PasswordField();

        TextField visiblePasswordField
                = new TextField();

        visiblePasswordField.setVisible(false);

        CheckBox showPasswordCheckBox
                = new CheckBox("Show Password");

        Label generatedPasswordLabel
                = new Label("");

        Button analyzeButton
                = new Button("Analyze");

        Button generateButton
                = new Button("Generate Password");

        Button viewRecordsButton
                = new Button("View Records");

        Button statisticsButton
                = new Button("View Statistics");

        Label scoreLabel
                = new Label("Score: 0");

        Label strengthLabel
                = new Label("Strength: -");

        Label entropyLabel
                = new Label("Entropy: 0.00 bits");

        title.setId("title");

        scoreLabel.setId("scoreLabel");

        strengthLabel.setId("strengthLabel");

        entropyLabel.setId("entropyLabel");

        TextArea suggestionArea
                = new TextArea();

        suggestionArea.setEditable(false);

        showPasswordCheckBox.setOnAction(e -> {

            if (showPasswordCheckBox.isSelected()) {

                visiblePasswordField.setText(
                        passwordField.getText()
                );

                visiblePasswordField.setVisible(true);

                passwordField.setVisible(false);

            } else {

                passwordField.setText(
                        visiblePasswordField.getText()
                );

                passwordField.setVisible(true);

                visiblePasswordField.setVisible(false);
            }
        });

        analyzeButton.setOnAction(e -> {

            String password;

            if (showPasswordCheckBox.isSelected()) {

                password
                        = visiblePasswordField.getText();

            } else {

                password
                        = passwordField.getText();
            }

            if (password.isBlank()) {

                suggestionArea.setText(
                        "Please enter a password."
                );

                return;
            }

            System.out.println("BUTTON CLICKED");

            PasswordAnalyzer analyzer
                    = new PasswordAnalyzer();

            EntropyCalculator entropyCalculator
                    = new EntropyCalculator();

            SuggestionEngine suggestionEngine
                    = new SuggestionEngine();

            int score
                    = analyzer.calculateScore(password);

            String strength
                    = analyzer.getStrength(score);

            double entropy
                    = entropyCalculator.calculateEntropy(password);

            PasswordHasher hasher
                    = new PasswordHasher();

            String hash
                    = hasher.hashPassword(password);

            scoreLabel.setText(
                    "Score: " + score
            );

            strengthLabel.setText(
                    "Strength: " + strength
            );

            entropyLabel.setText(
                    String.format(
                            "Entropy: %.2f bits",
                            entropy
                    )
            );

            StringBuilder result
                    = new StringBuilder();

            for (String suggestion
                    : suggestionEngine.generateSuggestions(password)) {

                result.append("• ")
                        .append(suggestion)
                        .append("\n");
            }

            if (result.length() == 0) {

                result.append(
                        "Password is already strong."
                );
            }

            suggestionArea.setText(
                    result.toString()
            );

            DatabaseManager db
                    = new DatabaseManager();

            db.saveRecord(
                    score,
                    strength,
                    entropy,
                    hash
            );

        });

        generateButton.setOnAction(e -> {

            PasswordGenerator generator
                    = new PasswordGenerator();

            String generatedPassword
                    = generator.generatePassword(16);

            passwordField.setText(generatedPassword);

            visiblePasswordField.setText(
                    generatedPassword
            );

            generatedPasswordLabel.setText(
                    "Generated Password: "
                    + generatedPassword
            );

            analyzeButton.fire();

        });

        viewRecordsButton.setOnAction(e -> {

            RecordWindow window
                    = new RecordWindow();

            window.show();

        });

        statisticsButton.setOnAction(e -> {

            StatisticsWindow window
                    = new StatisticsWindow();

            window.show();

        });

        VBox root
                = new VBox(
                        10,
                        title,
                        passwordLabel,
                        passwordField,
                        visiblePasswordField,
                        showPasswordCheckBox,
                        analyzeButton,
                        generateButton,
                        scoreLabel,
                        strengthLabel,
                        entropyLabel,
                        suggestionArea,
                        generatedPasswordLabel,
                        viewRecordsButton,
                        statisticsButton
                );

        root.setPadding(new Insets(20));

        Scene scene
                = new Scene(root, 700, 500);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/style.css")
                        .toExternalForm()
        );

        stage.setTitle(
                "Secure Pass"
        );

        stage.setScene(scene);

        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
