package com.example.quotesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    private QuoteManager quoteManager;
    private TextView quoteText, authorText;
    private CardView quoteCard;
    private Button shareButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteManager = new QuoteManager(this);

        quoteText = findViewById(R.id.quote_text);
        authorText = findViewById(R.id.author_text);
        quoteCard = findViewById(R.id.quote_card);
        shareButton = findViewById(R.id.btn_share);
        nextButton = findViewById(R.id.btn_next);

        displayRandomQuote();

        nextButton.setOnClickListener(v -> displayRandomQuote());

        shareButton.setOnClickListener(v -> shareQuote());
    }

    private void displayRandomQuote() {
        QuoteManager.Quote quote = quoteManager.getRandomQuote("quotes_motivation.txt");
        quoteText.setText(quote.quoteText);
        authorText.setText("- " + quote.authorName);
    }

    private void shareQuote() {
        String quote = quoteText.getText().toString() + "\n" + authorText.getText().toString();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, quote);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}




//XML , Not part of the File , will cause errors 

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="16dp"
    android:background="@color/background">

    <androidx.cardview.widget.CardView
        android:id="@+id/quote_card"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cardCornerRadius="16dp"
        app:cardElevation="8dp"
        android:layout_marginBottom="16dp"
        android:padding="16dp"
        android:background="@color/white">

        <LinearLayout
            android:orientation="vertical"
            android:gravity="center">

            <TextView
                android:id="@+id/quote_text"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="20sp"
                android:textStyle="italic"
                android:gravity="center"
                android:textColor="@color/black"
                android:padding="16dp"
                android:text="Loading Quote..."/>

            <TextView
                android:id="@+id/author_text"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="16sp"
                android:textColor="@color/gray"
                android:gravity="center"
                android:text="- Author Name"/>
        </LinearLayout>
    </androidx.cardview.widget.CardView>

    <Button
        android:id="@+id/btn_next"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Next Quote"
        android:layout_marginTop="10dp"/>

    <Button
        android:id="@+id/btn_share"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Share Quote"
        android:layout_marginTop="10dp"/>
</LinearLayout>