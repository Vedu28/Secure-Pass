package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseViewer {

    private static final String URL =
            "jdbc:mysql://localhost:3307/secure_pass";

    private static final String USER = "root";

    private static final String PASSWORD = "vedant2802";

    public void viewRecords() {

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
                            "SELECT * FROM password_records"
                    );

            System.out.println(
                    "\n===== DATABASE RECORDS ====="
            );

            while(rs.next()) {

                System.out.println(
                        "\nID: " +
                        rs.getInt("id")
                );

                System.out.println(
                        "Date: " +
                        rs.getTimestamp("analysis_date")
                );

                System.out.println(
                        "Score: " +
                        rs.getInt("score")
                );

                System.out.println(
                        "Strength: " +
                        rs.getString("strength")
                );

                System.out.println(
                        "Entropy: " +
                        rs.getDouble("entropy")
                );

                 System.out.println(
                        "Hash: " +
                        rs.getString("password_hash")
                );
            }

            conn.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}