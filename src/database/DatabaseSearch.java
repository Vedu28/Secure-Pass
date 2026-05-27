package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseSearch {

    private static final String URL =
            "jdbc:mysql://localhost:3307/secure_pass";

    private static final String USER = "root";

    private static final String PASSWORD = "vedant2802";

    public void searchByStrength(String strength) {

        try {

            Connection conn =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            String sql =
                    "SELECT * FROM password_records "
                    + "WHERE strength = ?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, strength);

            ResultSet rs =
                    ps.executeQuery();

            System.out.println(
                    "\n===== SEARCH RESULTS ====="
            );

            boolean found = false;

            while(rs.next()) {

                found = true;

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
            }

            if(!found) {

                System.out.println(
                        "No records found."
                );
            }

            conn.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}