package csumb.cst338.project2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Account.class, Book.class, Transaction.class}, version = 1, exportSchema = false)
public abstract class LibraryDatabase extends RoomDatabase {

    public abstract AccountDao accounts();
    public abstract BookDao books();
    public abstract TransactionDao transactions();

    private static LibraryDatabase sInstance;

    public static synchronized LibraryDatabase getInstance(Context context){
        if(sInstance == null){
            sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            LibraryDatabase.class, "account.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

        }
        return sInstance;
    }

    public void seed(){
        runInTransaction(() -> {
            if(accounts().count() == 0){
                Account one = new Account("hShuard", "m@thl3t3");
                Account two = new Account("bMishra", "bioN@no");
                Account three = new Account("shirleyBee", "carmel2Chicago");
                long[] account_ids = accounts().newAccounts(one, two, three);
            }
            if(books().count() == 0){
                Book one = new Book("Meditations", "Marcus Aurelius", "Self-Help", false);
                Book two = new Book("Letters to a Young Poet", "Rainer Maria Rilke", "Self-Help", false);
                Book three = new Book("Circe", "Madeline Miller", "Historical Fantasy", false);
                Book four = new Book("Intro to Machine Learning", "Anita Faul", "Computer Science", false);
                long[] library_books = books().addBooks(one, two, three, four);
            }
        });
    }
}
