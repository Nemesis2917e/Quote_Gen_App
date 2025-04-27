package com.example.quotesapp;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuoteManager {
    private Context context;

    public QuoteManager(Context context) {
        this.context = context;
    }

    // Class to hold both quote and author
    public static class Quote {
        String quoteText;
        String authorName;

        public Quote(String quoteText, String authorName) {
            this.quoteText = quoteText;
            this.authorName = authorName;
        }
    }

    // Read quotes with authors from file
    public List<Quote> getQuotesFromFile(String fileName) {
        List<Quote> quotes = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String quoteText = null;
            while ((line = reader.readLine()) != null) {
                if (quoteText == null) {
                    quoteText = line; // First line is the quote
                } else {
                    String authorName = line.startsWith("-") ? line.substring(1).trim() : "Unknown";
                    quotes.add(new Quote(quoteText, authorName));
                    quoteText = null; // Reset for next quote
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quotes;
    }

    // Get a random quote with author
    public Quote getRandomQuote(String fileName) {
        List<Quote> quotes = getQuotesFromFile(fileName);
        if (!quotes.isEmpty()) {
            return quotes.get(new Random().nextInt(quotes.size()));
        }
        return new Quote("No quotes available!", "Unknown");
    }
}