package bo.com.bienvenido18.android.repository.local.dao;

        import androidx.lifecycle.LiveData;
        import androidx.room.Dao;
        import androidx.room.Insert;
        import androidx.room.OnConflictStrategy;
        import androidx.room.Query;

        import java.util.List;

        import bo.com.bienvenido18.android.model.users.Tramites;
@Dao
public interface TramitesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Tramites> tramites);

    @Query("SELECT * FROM tramites_table ORDER BY titlePhoto ASC" )
    LiveData<List<Tramites>> getAll();

    @Query("DELETE FROM tramites_table")
    void deleteAll();
}
