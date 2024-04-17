package com.esteban.firebase;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ViewQuotesActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> quotesList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quotes);

        listView = findViewById(R.id.vista);

        quotesList = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, quotesList);
        listView.setAdapter(adapter);

        retrieveQuotesFromFirebase();
    }

    private void retrieveQuotesFromFirebase() {
        DatabaseReference quotesRef = FirebaseDatabase.getInstance().getReference("quotes");

        // Add listener to retrieve data
        quotesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear previous data
                quotesList.clear();

                // Loop through each child node
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get quote and author from each child node
                    String quote = snapshot.child("quote").getValue(String.class);
                    String author = snapshot.child("author").getValue(String.class);

                    // Add quote and author to ArrayList
                    quotesList.add("Quote: " + quote + "\nAuthor: " + author);
                }

                // Notify adapter of data change
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
