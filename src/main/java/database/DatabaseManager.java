package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class DatabaseManager {

    private static final String URL =
            "jdbc:mysql://localhost:3307/secure_pass";

    private static final String USER = "root";

    private static final String PASSWORD = "vedant2802";

    public void saveRecord(
            int score,
            String strength,
            double entropy,
            String hash) {

        try {

            Connection conn =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            String sql =
                    "INSERT INTO password_records "
                    + "(analysis_date, score, strength, entropy, password_hash) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setObject(1, LocalDateTime.now());
            ps.setInt(2, score);
            ps.setString(3, strength);
            ps.setDouble(4, entropy);
            ps.setString(5, hash);

            ps.executeUpdate();

            System.out.println(
                    "\nRecord saved to database successfully!"
            );

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}