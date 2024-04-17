package com.esteban.firebase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addQuoteButton;
    private FloatingActionButton viewQuotesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los botones flotantes
        addQuoteButton = findViewById(R.id.addQuoteButton);
        viewQuotesButton = findViewById(R.id.viewQuotesButton);

        // Configurar OnClickListener para el botón "Add Quote"
        addQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddQuoteActivity.class);
                startActivity(intent);
            }
        });

        // Configurar OnClickListener para el botón "View Quotes"
        viewQuotesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewQuotesActivity.class);
                startActivity(intent);
            }
        });
    }
}
