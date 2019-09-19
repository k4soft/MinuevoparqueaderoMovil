package co.com.k4soft.minuevoparqueadero.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import co.com.k4soft.minuevoparqueadero.persistence.table.Table;
import lombok.Data;

import static androidx.room.ForeignKey.CASCADE;


@Data
@Entity(tableName = Table.MOVIMIENTO_PARQUEADERO, foreignKeys = @ForeignKey(entity = TipoVehiculo.class, parentColumns = "id", childColumns = "idTipoVehiculo",onDelete = CASCADE), indices = {@Index("idTipoVehiculo")})

public class MovimientoParqueadero {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private Long id;
    @ColumnInfo(name = "placa")
    private String placa;
    @ColumnInfo(name = "fechaEntrada")
    private String fechaEntrada;
    @ColumnInfo(name = "fechaSalida")
    private String fechaSalida;
    @ColumnInfo(name = "idTipoVehiculo")
    private Integer idTipoVehiculo;
    @ColumnInfo(name = "cobro")
    private double cobro;
    @ColumnInfo(name = "mp_activo")
    private Boolean activo;

    public MovimientoParqueadero() {
        super();
    }

}
