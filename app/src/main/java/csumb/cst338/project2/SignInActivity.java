package csumb.cst338.project2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import csumb.cst338.project2.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding binding;
    private LibraryDatabase db;
    private boolean secondTry;
    private boolean missing;
    private Button signIn;
    private EditText user;
    private EditText pass;
    private Bundle b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        b = getIntent().getExtras();
        db = LibraryDatabase.getInstance(this);
        String selectedBook = b.getString("book");

        secondTry = false;
        missing = false;

        signIn = binding.signInButton;

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = binding.userSignIn;
                String username = user.getText().toString();
                pass = binding.passSignIn;
                String password = pass.getText().toString();

                if(username.isEmpty() || password.isEmpty() || username.trim().isEmpty() || password.trim().isEmpty()){
                    if(missing){
                        Toast.makeText(SignInActivity.this, R.string.error, Toast.LENGTH_LONG).show();
                        finish();
                    }
                    Toast.makeText(SignInActivity.this, R.string.missing_info, Toast.LENGTH_LONG).show();
                    user.getText().clear();
                    pass.getText().clear();
                    missing = true;
                }
                else if(db.accounts().accountFound(username,password) == 0){
                    if(secondTry){
                        Toast.makeText(SignInActivity.this, R.string.error, Toast.LENGTH_LONG).show();
                        finish();
                    }
                    Toast.makeText(SignInActivity.this, R.string.invalid_info, Toast.LENGTH_LONG).show();
                    user.getText().clear();
                    pass.getText().clear();
                    secondTry = true;
                } else{
                    int reservationNum = (int) ((Math.random()*10000) + 1000);
                    String reserveNum = String.valueOf(reservationNum);
                    Intent i = new Intent(SignInActivity.this, ConfirmationActivity.class);
                    b.putString("book", selectedBook);
                    b.putString("username", username);
                    b.putString("reservation_number", reserveNum);
                    i.putExtras(b);
                    user.getText().clear();
                    pass.getText().clear();
                    startActivity(i);
                }
            }
        });
    }
}
