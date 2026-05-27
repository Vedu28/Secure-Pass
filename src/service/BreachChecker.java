package service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BreachChecker {

    public boolean isBreached(String password) {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader("breached_passwords.txt")
                    );

            String line;

            while((line = reader.readLine()) != null) {

                if(line.equalsIgnoreCase(password)) {

                    reader.close();
                    return true;
                }
            }

            reader.close();

        } catch(IOException e) {

            System.out.println(
                    "Breach database not found."
            );
        }

        return false;
    }
}