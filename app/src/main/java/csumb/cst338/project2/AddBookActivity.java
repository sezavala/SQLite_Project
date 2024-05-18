package csumb.cst338.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import csumb.cst338.project2.databinding.ActivityAddBookBinding;

public class AddBookActivity extends AppCompatActivity {
    private ActivityAddBookBinding binding;
    private Button yes;
    private Button no;
    private DialogFragment dialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialogFragment = new csumb.cst338.lab11.AddTriviaDialog();
        yes = binding.yesButton;
        no = binding.noButton;

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(getSupportFragmentManager(), "AddBookDialog");
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddBookActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });
    }
}
