package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatisticsWindow {

    private static final String URL =
            "jdbc:mysql://localhost:3307/secure_pass";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "vedant2802";

    public void show() {

        int total = 0;
        int strong = 0;
        int medium = 0;
        int weak = 0;

        try {

            Connection conn =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            Statement stmt =
                    conn.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT strength, COUNT(*) AS count "
                            + "FROM password_records "
                            + "GROUP BY strength"
                    );

            while(rs.next()) {

                String strength =
                        rs.getString("strength");

                int count =
                        rs.getInt("count");

                total += count;

                if(strength.equalsIgnoreCase("Strong")) {

                    strong = count;
                }

                else if(strength.equalsIgnoreCase("Medium")) {

                    medium = count;
                }

                else if(strength.equalsIgnoreCase("Weak")) {

                    weak = count;
                }
            }

            conn.close();

        } catch(Exception e) {

            e.printStackTrace();
        }

        Label totalLabel =
                new Label(
                        "Total Records: " + total
                );

        Label strongLabel =
                new Label(
                        "Strong Passwords: " + strong
                );

        Label mediumLabel =
                new Label(
                        "Medium Passwords: " + medium
                );

        Label weakLabel =
                new Label(
                        "Weak Passwords: " + weak
                );

        VBox root =
                new VBox(
                        15,
                        totalLabel,
                        strongLabel,
                        mediumLabel,
                        weakLabel
                );

        root.setPadding(
                new Insets(20)
        );

        Scene scene =
                new Scene(root, 400, 250);

        Stage stage =
                new Stage();

        stage.setTitle(
                "Statistics Dashboard"
        );

        stage.setScene(scene);

        stage.show();
    }
}