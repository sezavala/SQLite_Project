package csumb.cst338.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import csumb.cst338.project2.databinding.ActivityBookBinding;

public class BookActivity extends AppCompatActivity {
    private ActivityBookBinding binding;
    private Button checkOut;
    private LibraryDatabase db;
    private Bundle b;
    private List<String> bookList;
    private ArrayAdapter<String> bookAdapter;
    private Spinner mySpinner;
    private String selectedBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mySpinner = binding.bookSpinner;
        db = LibraryDatabase.getInstance(this);
        b = getIntent().getExtras();

        if(b != null) {
            String genre = b.getString("genre");
            bookList = db.books().getAvailableGenreBooks(genre, false);

            bookAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bookList);
            bookAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mySpinner.setAdapter(bookAdapter);
        }

        checkOut = binding.checkoutButton;
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBook = (String) mySpinner.getSelectedItem();
                Intent i = new Intent(BookActivity.this, SignInActivity.class);
                b.putString("book", selectedBook);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }
}
