package service;
public class PasswordAnalyzer {

    public int calculateScore(String password) {

        int score = 0;

        if(password.length() >= 8)
            score += 20;

        if(password.matches(".*[A-Z].*"))
            score += 20;

        if(password.matches(".*[a-z].*"))
            score += 20;

        if(password.matches(".*[0-9].*"))
            score += 20;

        if(password.matches(".*[^a-zA-Z0-9].*"))
            score += 20;

        return score;
    }
    public String getStrength(int score) {

    if(score <= 40)
        return "Weak";

    if(score <= 70)
        return "Medium";

    return "Strong";
}
}