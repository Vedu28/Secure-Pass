package service;
public class EntropyCalculator {

    public double calculateEntropy(String password) {

        int charsetSize = 0;

        if(password.matches(".*[a-z].*"))
            charsetSize += 26;

        if(password.matches(".*[A-Z].*"))
            charsetSize += 26;

        if(password.matches(".*[0-9].*"))
            charsetSize += 10;

        if(password.matches(".*[^a-zA-Z0-9].*"))
            charsetSize += 32;

        if(charsetSize == 0)
            return 0;

        return password.length() *
               (Math.log(charsetSize) / Math.log(2));
    }
}