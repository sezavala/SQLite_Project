package csumb.cst338.project2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import csumb.cst338.project2.databinding.ActivitySystemBinding;

public class SystemActivity extends AppCompatActivity {
    private ActivitySystemBinding binding;
    private EditText user;
    private EditText pass;
    private Button signIn;
    private boolean missing;
    private boolean wrong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySystemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        missing = false;
        wrong = false;
        signIn = binding.signInAdmin;

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = binding.adminUser;
                String username = user.getText().toString();
                pass = binding.adminPass;
                String password = pass.getText().toString();

                if(username.isEmpty() || password.isEmpty() || username.trim().isEmpty() || password.trim().isEmpty()){
                    if(missing){
                        Toast.makeText(SystemActivity.this, R.string.error, Toast.LENGTH_LONG).show();
                        finish();
                    }
                    Toast.makeText(SystemActivity.this, R.string.missing_info, Toast.LENGTH_LONG).show();
                    user.getText().clear();
                    pass.getText().clear();
                    missing = true;
                } else if((!username.equals("!admin2")) || (!password.equals("!admin2"))){
                    if(wrong){
                        Toast.makeText(SystemActivity.this, R.string.error, Toast.LENGTH_LONG).show();
                        finish();
                    }
                    Toast.makeText(SystemActivity.this, R.string.incorrect_info, Toast.LENGTH_LONG).show();
                } else{
                    Intent i = new Intent(SystemActivity.this, LogActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
