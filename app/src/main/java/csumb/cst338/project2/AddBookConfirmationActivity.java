package csumb.cst338.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import csumb.cst338.project2.databinding.ActivityAddBookConfirmationActivityBinding;

public class AddBookConfirmationActivity extends AppCompatActivity {

    private String title;
    private String author;
    private String genre;
    private TextView titleTextView;
    private TextView authorTextView;
    private TextView genreTextView;
    private ActivityAddBookConfirmationActivityBinding binding;

    private LibraryDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBookConfirmationActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDatabase.getInstance(this);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        author = intent.getStringExtra("author");
        genre = intent.getStringExtra("genre");

        titleTextView = binding.titleTextView;
        authorTextView = binding.authorTextView;
        genreTextView = binding.genreTextView;

        titleTextView.setText(title);
        authorTextView.setText(author);
        genreTextView.setText(genre);

        Button confirmButton = binding.confirmButton;
        Button cancelButton = binding.cancelButton;

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book(title, author, genre, false);
                db.books().addBook(book);
                setResult(RESULT_OK);
                Intent i = new Intent(AddBookConfirmationActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
