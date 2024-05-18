package csumb.cst338.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Insert;

import csumb.cst338.project2.databinding.ActivityAccountBinding;

public class AccountActivity extends AppCompatActivity {
    private ActivityAccountBinding binding;

    private Button signUp;

    private EditText user;
    private EditText pass;

    private LibraryDatabase db;

    boolean missing;
    boolean dupe;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDatabase.getInstance(this);
        dupe = false;
        missing = false;

        signUp = binding.signUpButton;
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = binding.signUpUsername;
                String username = user.getText().toString();
                pass = binding.signUpPassword;
                String password = pass.getText().toString();

                if(username.isEmpty() || password.isEmpty() || username.trim().isEmpty() || password.trim().isEmpty()){
                    if(missing){
                        Toast.makeText(AccountActivity.this, R.string.error, Toast.LENGTH_LONG).show();
                        finish();
                    }
                    Toast.makeText(AccountActivity.this, R.string.missing_info, Toast.LENGTH_LONG).show();
                    user.getText().clear();
                    pass.getText().clear();
                    missing = true;
                }
                else if(duplicates(username) || username.equals("!admin2")){
                    if (dupe){
                        Toast.makeText(AccountActivity.this, R.string.error, Toast.LENGTH_LONG).show();
                        finish();
                    }
                    Toast.makeText(AccountActivity.this, R.string.username_dupe, Toast.LENGTH_LONG).show();
                    user.getText().clear();
                    pass.getText().clear();
                    dupe = true;
                } else{
                    Account newUser = new Account(username, password);
                    Transaction t = new Transaction("=New Account", username, "");
                    db.accounts().newAccount(newUser);
                    db.transactions().addTransaction(t);
                    Toast.makeText(AccountActivity.this, R.string.account_created, Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    public boolean duplicates(String u){
        return db.accounts().usernameDuplicates(u) > 0;
    }
}
