package service;
import java.util.ArrayList;

public class SuggestionEngine {

    public ArrayList<String> generateSuggestions(String password) {

        ArrayList<String> suggestions = new ArrayList<>();

        if(password.length() < 8)
            suggestions.add("Increase password length to at least 8 characters.");

        if(!password.matches(".*[A-Z].*"))
            suggestions.add("Add at least one uppercase letter.");

        if(!password.matches(".*[a-z].*"))
            suggestions.add("Add at least one lowercase letter.");

        if(!password.matches(".*[0-9].*"))
            suggestions.add("Add at least one number.");

        if(!password.matches(".*[^a-zA-Z0-9].*"))
            suggestions.add("Add at least one special character.");

        return suggestions;
    }
}