package csumb.cst338.project2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AccountDao {

    @Insert
    long newAccount(Account account);

    @Insert
    long[] newAccounts(Account... accounts);

    @Query("SELECT id FROM accounts WHERE username IS :username")
    int userID(String username);

    @Query("SELECT count(*) FROM accounts")
    int count();

    @Query("SELECT count(*) FROM accounts WHERE username IS :username")
    int usernameDuplicates(String username);

    @Query("SELECT count(*) FROM accounts WHERE username IS :username AND password IS :password")
    int accountFound(String username, String password);


}
