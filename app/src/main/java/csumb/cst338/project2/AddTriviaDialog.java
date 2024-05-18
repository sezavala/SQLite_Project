package csumb.cst338.lab11;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import csumb.cst338.project2.AddBookActivity;
import csumb.cst338.project2.AddBookConfirmationActivity;
import csumb.cst338.project2.Book;
import csumb.cst338.project2.LibraryDatabase;
import csumb.cst338.project2.databinding.DialogAddTriviaBinding;

public class AddTriviaDialog extends DialogFragment {
    private DialogAddTriviaBinding binding;
    private RadioGroup radioGroup;
    private LibraryDatabase db;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        binding = DialogAddTriviaBinding.inflate(LayoutInflater.from(getContext()));
        db = LibraryDatabase.getInstance(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(binding.getRoot())
                .setTitle("Add A Book")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = String.valueOf(binding.title.getText());
                        String author = String.valueOf(binding.author.getText());
                        String genre = String.valueOf(binding.genre.getText());

                        Intent intent = new Intent(getActivity(), AddBookConfirmationActivity.class);
                        intent.putExtra("title", title);
                        intent.putExtra("author", author);
                        intent.putExtra("genre", genre);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
        return builder.create();
    }
}
