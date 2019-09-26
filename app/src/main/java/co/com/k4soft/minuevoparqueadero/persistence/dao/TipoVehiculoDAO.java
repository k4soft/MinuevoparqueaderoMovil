package co.com.k4soft.minuevoparqueadero.persistence.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import co.com.k4soft.minuevoparqueadero.entities.TipoVehiculo;
import co.com.k4soft.minuevoparqueadero.persistence.table.Table;

@Dao
public interface TipoVehiculoDAO {
    @Query("SELECT * FROM "+Table.TABLA_TIPO_VEHICULO+ " WHERE id=:idTipoVehiculo")
    TipoVehiculo findById(Integer idTipoVehiculo);

    @Query("SELECT * FROM "+ Table.TABLA_TIPO_VEHICULO)
    List<TipoVehiculo> listAll();

    @Insert
    void insert(TipoVehiculo tipoVehiculo);

    @Update
    void update(TipoVehiculo tipoVehiculo);


}
