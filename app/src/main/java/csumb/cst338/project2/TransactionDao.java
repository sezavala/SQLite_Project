package csumb.cst338.project2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {

    @Insert
    long addTransaction(Transaction transaction);

    @Query("SELECT * FROM transactions")
    List<Transaction> getTransactions();
}
