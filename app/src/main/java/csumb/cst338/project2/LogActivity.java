package csumb.cst338.project2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import csumb.cst338.project2.databinding.ActivityLogBinding;

public class LogActivity extends AppCompatActivity {
    private ActivityLogBinding binding;
    private LibraryDatabase db;
    private ArrayAdapter<String> tAdapter;
    private List<Transaction> logs;
    private List<String> fullLogs;
    private Button okay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = LibraryDatabase.getInstance(this);
        logs = db.transactions().getTransactions();
        fullLogs = new ArrayList<String>();
        okay = binding.okayButton;

        for (Transaction items : logs){
            List<String> temp = new ArrayList<String>();
            temp.add(items.getTransactionType());
            temp.add(items.getUsername());
            temp.add(items.getReservationNumber());
            fullLogs.add(temp.toString());
        }

        if (tAdapter == null) {
            tAdapter = new ArrayAdapter<>(this,
                    R.layout.item_transaction,
                    R.id.transaction,
                    fullLogs);
            binding.listOfLogs.setAdapter(tAdapter);
        } else {
            tAdapter.clear();
            logs = db.transactions().getTransactions();
            tAdapter.addAll(fullLogs);
            tAdapter.notifyDataSetChanged();
        }

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogActivity.this, AddBookActivity.class);
                startActivity(i);
            }
        });
    }
}
