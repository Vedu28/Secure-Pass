package model;

public class PasswordRecord {

    private int id;
    private int score;
    private String strength;
    private double entropy;
    private String passwordHash;

    public PasswordRecord(
            int id,
            int score,
            String strength,
            double entropy,
            String passwordHash
    ) {
        this.id = id;
        this.score = score;
        this.strength = strength;
        this.entropy = entropy;
        this.passwordHash = passwordHash;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getStrength() {
        return strength;
    }

    public double getEntropy() {
        return entropy;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}