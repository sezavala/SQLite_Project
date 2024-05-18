package csumb.cst338.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import csumb.cst338.project2.databinding.ActivityConfirmationBinding;

public class ConfirmationActivity extends AppCompatActivity {
    private ActivityConfirmationBinding binding;
    private TextView usernameDisplay;
    private TextView bookDisplay;
    private TextView reservationNumberDisplay;
    private Bundle b;
    private Button yes;
    private Button no;
    private LibraryDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConfirmationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        b = getIntent().getExtras();
        db = LibraryDatabase.getInstance(this);

        usernameDisplay = binding.username;
        bookDisplay = binding.book;
        reservationNumberDisplay = binding.reservationNumber;
        yes = binding.yesButton;
        no = binding.noButton;

        if(b != null) {
            usernameDisplay.setText(b.getString("username"));
            bookDisplay.setText(b.getString("book"));
            reservationNumberDisplay.setText(b.getString("reservation_number"));
        }

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transaction t = new Transaction("=Place Hold", b.getString("username"), b.getString("reservation_number"));
                db.transactions().addTransaction(t);
                db.books().changeStatus(b.getString("book"));
                Intent i = new Intent(ConfirmationActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmationActivity.this, HoldActivity.class);
                finish();
                startActivity(i);
            }
        });
    }
}
