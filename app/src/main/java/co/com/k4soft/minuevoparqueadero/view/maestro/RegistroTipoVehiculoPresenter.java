package co.com.k4soft.minuevoparqueadero.view.maestro;

import android.content.Context;

public class RegistroTipoVehiculoPresenter implements RegistroTipoVehiculo.Presenter {

    private RegistroTipoVehiculo.View view;
    private RegistroTipoVehiculo.Model model;


    public RegistroTipoVehiculoPresenter(RegistroTipoVehiculo.View registroTipoVehiculoActivity, Context context) {
        view = registroTipoVehiculoActivity;
        model = new RegistroTipoVehiculoModel(this,context);
    }


    @Override
    public void guardar() {
        if (view != null) {
            model.setNombreTipoVehiculo(view.getNombreTipoVehiculo());
            model.setPrecio(view.getPrecio());
            model.guardar();
        }
    }

    @Override
    public void informacionGuardadaSatisfactoriament() {
        if(view != null){
           view.informacionGuardadaSatisfactoriament();
        }
    }

    @Override
    public void requerirNombreTipoVehiculo() {
        if (view != null) {
            view.requerirNombreTipoVehiculo();
        }
    }

    @Override
    public void requerirPrecio() {
        if (view != null) {
            view.requerirPrecio();
        }
    }
}
