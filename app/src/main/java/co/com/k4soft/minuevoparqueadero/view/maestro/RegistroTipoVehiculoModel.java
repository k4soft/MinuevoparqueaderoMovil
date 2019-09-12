package co.com.k4soft.minuevoparqueadero.view.maestro;

import android.content.Context;

import co.com.k4soft.minuevoparqueadero.entities.TipoVehiculo;
import co.com.k4soft.minuevoparqueadero.menu.ui.maestro.MaestroPresenter;
import co.com.k4soft.minuevoparqueadero.persistence.database.DataBase;

public class RegistroTipoVehiculoModel implements RegistroTipoVehiculo.Model {
    private RegistroTipoVehiculo.Presenter presenter;
    private String nombreTipoVehiculo;
    private String precio;
    private Context context;

    public RegistroTipoVehiculoModel(RegistroTipoVehiculoPresenter registroTipoVehiculoPresenter, Context context) {
        presenter = registroTipoVehiculoPresenter;
        this.context = context;
    }



    @Override
    public void guardar() {
       boolean isValido = true;
       if(nombreIsEmpty()){
           isValido = false;
           requerirNombreTipoVehiculo();
       }

       if(getPrecioDouble() == 0){
           isValido = false;
           requerirPrecio();
       }

       if(isValido){
           guardarTipoVehiculo();
       }


    }

    private void guardarTipoVehiculo() {
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        tipoVehiculo.setActivo(true);
        tipoVehiculo.setNombre(nombreTipoVehiculo);
        tipoVehiculo.setTarifa(getPrecioDouble());
        DataBase.getdDB(context).geTipoVehiculoDAO().insert(tipoVehiculo);
        presenter.informacionGuardadaSatisfactoriament();
    }

    private void requerirPrecio() {
        presenter.requerirPrecio();
    }

    private void requerirNombreTipoVehiculo() {
        presenter.requerirNombreTipoVehiculo();
    }

    private boolean nombreIsEmpty() {
        return "".equals(nombreTipoVehiculo);
    }

    private double getPrecioDouble() {
        return precio == null || "".equals(precio)?0:Double.parseDouble(precio);
    }

    @Override
    public void setNombreTipoVehiculo(String nombreTipoVehiculo) {

        this.nombreTipoVehiculo = nombreTipoVehiculo;
    }

    @Override
    public void setPrecio(String precio) {

        this.precio = precio;
    }
}
