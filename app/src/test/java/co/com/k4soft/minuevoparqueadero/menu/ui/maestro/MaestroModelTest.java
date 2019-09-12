package co.com.k4soft.minuevoparqueadero.menu.ui.maestro;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MaestroModelTest {

    @Mock
    private Maestro.View view;
    private MaestroPresenter presenter;
    private MaestroModel model;

    @Before
    public void setUp(){
        presenter = new MaestroPresenter(view);
        model = new MaestroModel(presenter);

    }

    @Test
    public void validarListaVacia(){
        when(view.getTipoVehiculos()).thenReturn(null);
        model.setTipoVehiculos(view.getTipoVehiculos());
        model.validarTipoVehiculoVacio();
        verify(view).sinTipoVehiculos();
    }

}