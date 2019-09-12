package co.com.k4soft.minuevoparqueadero.menu.ui.maestro;

import java.util.List;

import co.com.k4soft.minuevoparqueadero.entities.TipoVehiculo;

public class MaestroModel implements Maestro.Model{

    private final Maestro.Presenter presenter;
    private List<TipoVehiculo> tipoVehiculos;

    public MaestroModel(MaestroPresenter listaTipoVehiculoPresenter) {
        presenter = listaTipoVehiculoPresenter;
    }


    @Override
    public void setTipoVehiculos(List<TipoVehiculo> tipoVehiculos) {

        this.tipoVehiculos = tipoVehiculos;
    }

    @Override
    public void validarTipoVehiculoVacio() {
         if(tipoVehiculosIsEmpty()){
             sinTipoVehiculos();
         }
    }

    private void sinTipoVehiculos() {
        presenter.sinTipoVehiculos();
    }

    private boolean tipoVehiculosIsEmpty() {
        return tipoVehiculos == null || tipoVehiculos.isEmpty();
    }
}
