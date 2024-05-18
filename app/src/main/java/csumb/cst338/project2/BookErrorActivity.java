package csumb.cst338.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import csumb.cst338.project2.databinding.ActivityBookErrorBinding;

public class BookErrorActivity extends AppCompatActivity {
    private ActivityBookErrorBinding binding;
    private Button exit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookErrorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        exit = binding.exitButton;

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BookErrorActivity.this, MainActivity.class);
                finish();
                startActivity(i);
            }
        });
    }
}
