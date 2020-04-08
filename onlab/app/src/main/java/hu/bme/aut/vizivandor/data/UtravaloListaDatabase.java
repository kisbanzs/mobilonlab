package hu.bme.aut.vizivandor.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(
        entities = {UtravaloListaItem.class},
        version = 1
)
//@TypeConverters(value = {UtravaloListaItem.Category.class})
public abstract class UtravaloListaDatabase extends RoomDatabase {
    public abstract UtravaloListaDao utravaloListaItemDao();
}