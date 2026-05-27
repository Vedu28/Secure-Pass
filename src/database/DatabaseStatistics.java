package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseStatistics {

    private static final String URL =
            "jdbc:mysql://localhost:3307/secure_pass";

    private static final String USER = "root";

    private static final String PASSWORD = "vedant2802";

    public void showStatistics() {

        try {

            Connection conn =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            Statement stmt =
                    conn.createStatement();

            ResultSet total =
                    stmt.executeQuery(
                            "SELECT COUNT(*) AS total FROM password_records"
                    );

            total.next();

            System.out.println(
                    "\n===== DATABASE STATISTICS ====="
            );

            System.out.println(
                    "Total Records: "
                    + total.getInt("total")
            );

            ResultSet strength =
                    stmt.executeQuery(
                            "SELECT strength, COUNT(*) AS count "
                            + "FROM password_records "
                            + "GROUP BY strength"
                    );

            while(strength.next()) {

                System.out.println(
                        strength.getString("strength")
                        + ": "
                        + strength.getInt("count")
                );
            }

            conn.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}