package co.com.k4soft.minuevoparqueadero.menu.ui.maestro;

public class MaestroPresenter implements Maestro.Presenter {

    private final Maestro.View view;
    private final Maestro.Model model;

    public MaestroPresenter(Maestro.View view) {
        this.view = view;
        model = new MaestroModel(this);
    }

    @Override
    public void sinTipoVehiculos() {
        if (view != null) {
            view.sinTipoVehiculos();
        }
    }

    @Override
    public void validarTipoVehiculoVacio() {
        if(view != null){
            model.setTipoVehiculos(view.getTipoVehiculos());
            model.validarTipoVehiculoVacio();
        }
    }
}
