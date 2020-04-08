package hu.bme.aut.vizivandor.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


    @Dao
    public interface UtravaloListaDao {
        @Query("SELECT * FROM utravalolista")
        List<UtravaloListaItem> getAll();

        @Query("SELECT * FROM utravalolista WHERE id IN (:id)")
        UtravaloListaItem loadById(long id);

        @Insert
        long insert(UtravaloListaItem item);

        @Update
        void update(UtravaloListaItem item);

        @Delete
        void deleteItem(UtravaloListaItem item);


    }
