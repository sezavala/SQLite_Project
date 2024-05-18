package csumb.cst338.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import csumb.cst338.project2.databinding.ActivityHoldBinding;

public class HoldActivity extends AppCompatActivity {
    private ActivityHoldBinding binding;

    private Button findBooks;

    private Spinner mySpinner;

    private LibraryDatabase db;

    private List<String> genres;

    private ArrayAdapter<String> genreAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHoldBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDatabase.getInstance(this);
        mySpinner = binding.genreSpinner;
        genres = db.books().getAllGenres();

        genreAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genres);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(genreAdapter);

        findBooks = binding.booksButton;
        findBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedGenre = (String) mySpinner.getSelectedItem();
                if (selectedGenre != null) {
                    if(db.books().bookCount(selectedGenre, false) == 0 ){
                        Intent i = new Intent(HoldActivity.this, BookErrorActivity.class);
                        startActivity(i);
                    } else {
                        Intent i = new Intent(HoldActivity.this, BookActivity.class);
                        Bundle b = new Bundle();
                        b.putString("genre", selectedGenre);
                        i.putExtras(b);
                        startActivity(i);
                    }
                }
            }
        });
    }
}
