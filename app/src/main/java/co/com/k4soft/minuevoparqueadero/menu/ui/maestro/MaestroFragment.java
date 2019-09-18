package co.com.k4soft.minuevoparqueadero.menu.ui.maestro;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.minuevoparqueadero.entities.TipoVehiculo;
import co.com.k4soft.minuevoparqueadero.menu.R;
import co.com.k4soft.minuevoparqueadero.persistence.database.DataBase;
import co.com.k4soft.minuevoparqueadero.view.maestro.RegistroTipoVehiculoActivity;

public class MaestroFragment extends Fragment implements Maestro.View {


    private List<TipoVehiculo> tipoVehiculos;
    private Maestro.Presenter presenter;
    @BindView(R.id.addTipo)
    public FloatingActionButton addTipo;
    private ArrayAdapter<String> arrayAdapter;
    @BindView(R.id.listViewTipoVehiculos)
    public ListView listViewTipoVehiculos;

    @Override
    public List<TipoVehiculo> getTipoVehiculos() {
        return tipoVehiculos;
    }

    @Override
    public void sinTipoVehiculos() {
        Toast.makeText(getActivity(),R.string.sin_tipo_vehiculos,Toast.LENGTH_SHORT).show();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_maestro, container, false);
        ButterKnife.bind(this, root);
        initComponents();
        cargarInformacion();
        onClickListenerAddTipo();
        return root;
    }

    private void onClickListenerAddTipo() {
        addTipo.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), RegistroTipoVehiculoActivity.class);
            startActivity(intent);
        });


    }

    private void initComponents() {
        presenter = new MaestroPresenter(this);
    }

    private void cargarInformacion() {
        tipoVehiculos = DataBase.getMainThreadDB(getActivity()).geTipoVehiculoDAO().listAll();

        String[] vehiculoArray = new String[tipoVehiculos.size()];
        for (int i = 0; i < tipoVehiculos.size(); i++) {
            vehiculoArray[i] = tipoVehiculos.get(i).getNombre().concat(" Precio: ").concat(Double.toString(tipoVehiculos.get(i).getTarifa()));
        }
        arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, vehiculoArray);
        listViewTipoVehiculos.setAdapter(arrayAdapter);



        presenter.validarTipoVehiculoVacio();

    }

    @Override
    public void onResume(){
        super.onResume();
        cargarInformacion();
    }
}