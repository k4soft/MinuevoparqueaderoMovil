package co.com.k4soft.minuevoparqueadero.view.maestro;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RegistroTipoVehiculoModelTest {

    @Mock
    private RegistroTipoVehiculo.View view;
    private RegistroTipoVehiculoPresenter presenter;
    private RegistroTipoVehiculoModel model;



    @Before
    public void setUp(){
        presenter = new RegistroTipoVehiculoPresenter(view);
        model = new RegistroTipoVehiculoModel(presenter);
    }

    @Test
    public void validarInformacionRequerida(){
        when(view.getNombreTipoVehiculo()).thenReturn("");
        when(view.getPrecio()).thenReturn("");
        model.setNombreTipoVehiculo(view.getNombreTipoVehiculo());
        model.setPrecio(view.getPrecio());
        model.guardar();
        verify(view).requerirNombreTipoVehiculo();
        verify(view).requerirPrecio();
    }

}