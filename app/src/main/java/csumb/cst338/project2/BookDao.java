package csumb.cst338.project2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    long addBook(Book book);

    @Insert
    long[] addBooks(Book... books);

    @Query("UPDATE library SET reserved = 1 WHERE book_title IS :book_title ")
    void changeStatus(String book_title);

    @Query("SELECT count(*) FROM library")
    int count();

    @Query("SELECT DISTINCT genre FROM library")
    List<String> getAllGenres();

    @Query("SELECT book_title FROM library WHERE genre IS :genre AND reserved IS :reserved")
    List<String> getAvailableGenreBooks(String genre, boolean reserved);

    @Query("SELECT count(*) FROM library WHERE genre IS :genre AND reserved IS :reserved")
    int bookCount(String genre, boolean reserved);

}
