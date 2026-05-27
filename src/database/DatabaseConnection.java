package database;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static void main(String[] args) {

        try {

            Connection conn =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3307/secure_pass",
                            "root",
                            "vedant2802"
                    );

            System.out.println(
                    "Database Connected Successfully!"
            );

            conn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}