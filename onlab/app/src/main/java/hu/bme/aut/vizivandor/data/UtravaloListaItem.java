package hu.bme.aut.vizivandor.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "utravalolista")
public class UtravaloListaItem {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;

    @ColumnInfo(name = "targynev")
    public String targynev;

    @ColumnInfo(name = "checkbox")
    public Boolean checkbox;

}


