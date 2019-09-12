package co.com.k4soft.minuevoparqueadero.persistence.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.com.k4soft.minuevoparqueadero.entities.TipoVehiculo;
import co.com.k4soft.minuevoparqueadero.persistence.dao.TipoVehiculoDAO;
import co.com.k4soft.minuevoparqueadero.persistence.util.DBParameter;


@Database(entities = {
        TipoVehiculo.class
}, version = DBParameter.DATA_BASE_VERSION, exportSchema = false)
public abstract class DataBase  extends RoomDatabase {

    private static DataBase INSTANCE;

    public static DataBase getdDB(Context context) {
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, DataBase.class, DBParameter.DATA_BASE_NAME).build();
        }
        return INSTANCE;
    }

    public static DataBase getMainThreadDB(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, DataBase.class, DBParameter.DATA_BASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    public abstract TipoVehiculoDAO geTipoVehiculoDAO();


}
