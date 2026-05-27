package service;
import java.util.HashSet;

public class CommonPasswordChecker {

    private HashSet<String> commonPasswords;

    public CommonPasswordChecker() {

        commonPasswords = new HashSet<>();

        commonPasswords.add("123456");
        commonPasswords.add("password");
        commonPasswords.add("admin");
        commonPasswords.add("qwerty");
        commonPasswords.add("welcome");
    }

    public boolean isCommonPassword(String password) {
        return commonPasswords.contains(password.toLowerCase());
    }
}