package co.com.k4soft.minuevoparqueadero.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import co.com.k4soft.minuevoparqueadero.persistence.table.Table;
import lombok.Data;


@Data
@Entity(tableName = Table.TABLA_TIPO_VEHICULO)
public class TipoVehiculo {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    @NonNull
    private Integer id;
    @ColumnInfo(name="nombre")
    private String nombre;
    @ColumnInfo(name="tarifa")
    private double tarifa;
    @ColumnInfo(name="tv_activo")
    private Boolean activo;

    public TipoVehiculo(){
        super();
    }

}
