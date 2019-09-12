package co.com.k4soft.minuevoparqueadero.menu.ui.maestro;

import java.util.List;

import co.com.k4soft.minuevoparqueadero.entities.TipoVehiculo;

public interface Maestro {

    interface View{

        List<TipoVehiculo> getTipoVehiculos();

        void sinTipoVehiculos();
    }

    interface Presenter{

        void sinTipoVehiculos();

        void validarTipoVehiculoVacio();
    }

    interface Model{

        void setTipoVehiculos(List<TipoVehiculo> tipoVehiculos);

        void validarTipoVehiculoVacio();
    }
}
