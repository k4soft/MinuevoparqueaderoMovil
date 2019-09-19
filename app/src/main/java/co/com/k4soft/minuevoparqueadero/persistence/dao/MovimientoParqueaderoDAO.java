package co.com.k4soft.minuevoparqueadero.persistence.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import co.com.k4soft.minuevoparqueadero.entities.MovimientoParqueadero;
import co.com.k4soft.minuevoparqueadero.persistence.table.Table;

@Dao
public interface MovimientoParqueaderoDAO {

    @Insert
    void insert(MovimientoParqueadero movimientoParqueadero);

    @Query("SELECT * FROM "+ Table.MOVIMIENTO_PARQUEADERO+" WHERE placa=:placa AND mp_activo=:activo")
    MovimientoParqueadero findByPlaca(String placa, Integer activo);


}
