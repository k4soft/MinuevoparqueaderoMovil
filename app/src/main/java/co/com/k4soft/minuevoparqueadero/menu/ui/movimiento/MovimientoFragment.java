package co.com.k4soft.minuevoparqueadero.menu.ui.movimiento;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.minuevoparqueadero.entities.TipoVehiculo;
import co.com.k4soft.minuevoparqueadero.menu.R;
import co.com.k4soft.minuevoparqueadero.persistence.database.DataBase;

public class MovimientoFragment extends Fragment {


    @BindView(R.id.txtPlaca)
    public EditText txtPlaca;
    @BindView(R.id.tipoVehiculoSpinner)
    public Spinner tipoVehiculoSpinner;
    @BindView(R.id.btnIngreso)
    public Button btnIngreso;
    @BindView(R.id.btnSalida)
    public Button btnSalida;
    @BindView(R.id.btnSearch)
    public ImageButton btnSearch;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movimiento, container, false);
        ButterKnife.bind(this, root);
        hideComponents();
        verificarTipoVehiculo();
        return root;
    }

    private void verificarTipoVehiculo() {
       if(tipoVehiculoIsEmpty()){
           Toast.makeText(getActivity(),R.string.configurar_tipo_vehiculo, Toast.LENGTH_SHORT).show();
           onStop();
       }

    }

    private boolean tipoVehiculoIsEmpty() {
        List<TipoVehiculo> tipoVehiculos = DataBase.getMainThreadDB(getActivity()).geTipoVehiculoDAO().listAll();
        return tipoVehiculos == null ||  tipoVehiculos.isEmpty();
    }

    private void hideComponents() {
        tipoVehiculoSpinner.setVisibility(View.GONE);
        btnIngreso.setVisibility(View.GONE);
        btnSalida.setVisibility(View.GONE);
    }

    private void showComponentsEntradaVehiculo(){
        tipoVehiculoSpinner.setVisibility(View.VISIBLE);
        btnIngreso.setVisibility(View.VISIBLE);
    }

    private void showComponentsSalidaVehiculo(){
       btnSalida.setVisibility(View.VISIBLE);
    }

    private void initComponents() {
    }
}