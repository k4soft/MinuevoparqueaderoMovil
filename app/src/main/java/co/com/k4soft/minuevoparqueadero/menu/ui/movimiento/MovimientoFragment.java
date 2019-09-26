package co.com.k4soft.minuevoparqueadero.menu.ui.movimiento;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.minuevoparqueadero.entities.MovimientoParqueadero;
import co.com.k4soft.minuevoparqueadero.entities.TipoVehiculo;
import co.com.k4soft.minuevoparqueadero.menu.R;
import co.com.k4soft.minuevoparqueadero.persistence.database.DataBase;
import co.com.k4soft.minuevoparqueadero.util.DateUtil;
import co.com.k4soft.minuevoparqueadero.util.GenericUtil;

public class MovimientoFragment extends Fragment {


    private static final Integer ACTIVO = 1;
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
    @BindView(R.id.cardInfoVehiculo)
    public CardView cardInfoVehiculo;
    @BindView(R.id.txtTipoVehiculo)
    public TextView txtTipoVehiculo;
    @BindView(R.id.txtHoras)
    public TextView txtHoras;
    @BindView(R.id.txtValorPagar)
    public TextView txtValorPagar;


    private DataBase db;
    private List<TipoVehiculo> tipoVehiculos;
    private String[] arrayTipoVenhiculo;
    private MovimientoParqueadero movimientoParqueaderoEntrada;
    private MovimientoParqueadero movimientoParqueaderoConsulta;
    private TipoVehiculo tipoVehiculoSeleccionado;
    private int posTipoVehiculo;
    private double cantidadHoras;
    private double totalPagar;
    private TipoVehiculo tipoVehiculo;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movimiento, container, false);
        ButterKnife.bind(this, root);
        initComponents();
        hideComponents();
        verificarTipoVehiculo();
        buscarOnClickListener();
        ingresoClickListener();
        salidaClickLister();
        onItemSpinnerSelected();
        return root;
    }

    private void salidaClickLister() {
        btnSalida.setOnClickListener(view -> {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle(getString(R.string.confirm));
            alertDialog.setIcon(R.drawable.ic_triangle_warning);
            alertDialog.setMessage(getString(R.string.confirm_message_guardar));
            alertDialog.setNegativeButton(getString(R.string.no_value_button),
                    (dialog, which) -> dialog.cancel());
            alertDialog.setPositiveButton(getString(R.string.yes_value_button),
                    (dialog, which) -> {
                        guardarSalidaVehiculo();
                        dialog.cancel();
                    });
            alertDialog.show();

        });

    }

    private void guardarSalidaVehiculo() {
        movimientoParqueaderoConsulta.setCobro(totalPagar);
        movimientoParqueaderoConsulta.setFechaSalida(DateUtil.getCurrenDate());
        movimientoParqueaderoConsulta.setActivo(false);
        db.getMovimientoParqueaderoDAO().update(movimientoParqueaderoConsulta);
        restablecerComponentes();
    }

    private void onItemSpinnerSelected() {
        tipoVehiculoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                posTipoVehiculo = i;
                if (posTipoVehiculo != 0) {
                    tipoVehiculoSeleccionado = tipoVehiculos.get(posTipoVehiculo - 1);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void ingresoClickListener() {
        btnIngreso.setOnClickListener(view -> {
            if (validarTipoVehiculo()) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle(getString(R.string.confirm));
                alertDialog.setIcon(R.drawable.ic_triangle_warning);
                alertDialog.setMessage(getString(R.string.confirm_message_guardar));
                alertDialog.setNegativeButton(getString(R.string.no_value_button),
                        (dialog, which) -> dialog.cancel());
                alertDialog.setPositiveButton(getString(R.string.yes_value_button),
                        (dialog, which) -> {
                            guardar();
                            dialog.cancel();
                        });
                alertDialog.show();


            } else {
                Toast.makeText(getActivity(), R.string.tipo_vehiculo_requerido, Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void guardar() {
        setInformacionEntradaVehiculo();
        db.getMovimientoParqueaderoDAO().insert(movimientoParqueaderoEntrada);
        Toast.makeText(getActivity(), R.string.informacion_alamacenada_satisfactoriamente, Toast.LENGTH_SHORT).show();
        restablecerComponentes();
    }

    private void restablecerComponentes() {
        movimientoParqueaderoEntrada = null;
        txtPlaca.setText("");
        hideComponents();
    }

    private boolean validarTipoVehiculo() {
        return posTipoVehiculo != 0;
    }

    private void setInformacionEntradaVehiculo() {
        if (movimientoParqueaderoEntrada == null) {
            movimientoParqueaderoEntrada = new MovimientoParqueadero();
        }
        movimientoParqueaderoEntrada.setPlaca(txtPlaca.getText().toString());
        movimientoParqueaderoEntrada.setIdTipoVehiculo(tipoVehiculoSeleccionado.getId());
        movimientoParqueaderoEntrada.setFechaEntrada(DateUtil.getCurrenDate());
        movimientoParqueaderoEntrada.setActivo(true);
    }

    private void initComponents() {
        db = DataBase.getMainThreadDB(getActivity());
    }

    private void buscarOnClickListener() {

        btnSearch.setOnClickListener(view -> {
            movimientoParqueaderoConsulta = null;
            hideComponents();
            if (validarPlaca()) {
                movimientoParqueaderoConsulta = db.getMovimientoParqueaderoDAO().findByPlaca(txtPlaca.getText().toString(), ACTIVO);
                validarMovimiento();
            } else {
                txtPlaca.setError(getString(R.string.requerido));
            }
        });

    }

    private void validarMovimiento() {
        if (movimientoParqueaderoConsulta == null) {
            showComponentsEntradaVehiculo();
        } else {
            cargarInformacionMovimiento();
            showComponentsSalidaVehiculo();
        }
    }

    private void cargarInformacionMovimiento() {
        txtTipoVehiculo.setText(getTipoVehiculo());
        calcularHoras();
        txtHoras.setText(GenericUtil.getDecimalFormat(0, cantidadHoras));
        calcularValorPagar();
        txtValorPagar.setText(GenericUtil.formatoMiles(totalPagar));
    }

    private void calcularValorPagar() {
        if (cantidadHoras == 0) {
            cantidadHoras = 1;
        }
        totalPagar = cantidadHoras * tipoVehiculo.getTarifa();
    }

    private void calcularHoras() {
        Date fechaEntrada = DateUtil.convertStringToDate(movimientoParqueaderoConsulta.getFechaEntrada());
        Date fechaSalida = DateUtil.convertStringToDate(DateUtil.getCurrenDate());
        cantidadHoras = DateUtil.minutesDiff(fechaEntrada, fechaSalida);

    }

    private String getTipoVehiculo() {
        tipoVehiculo = db.geTipoVehiculoDAO().findById(movimientoParqueaderoConsulta.getIdTipoVehiculo());
        return tipoVehiculo.getNombre();
    }

    private boolean validarPlaca() {
        return !"".equals(txtPlaca.getText().toString());
    }

    private void verificarTipoVehiculo() {
        cargarInformacionTipoVehiculo();
        if (tipoVehiculoIsEmpty()) {
            Toast.makeText(getActivity(), R.string.configurar_tipo_vehiculo, Toast.LENGTH_SHORT).show();
            onStop();
        }

    }

    private void cargarInformacionTipoVehiculo() {
        tipoVehiculos = db.geTipoVehiculoDAO().listAll();
        arrayTipoVenhiculo = new String[tipoVehiculos.size() + 1];
        arrayTipoVenhiculo[0] = getString(R.string.item_seleccione);
        for (int i = 0; i < tipoVehiculos.size(); i++) {
            arrayTipoVenhiculo[i + 1] = tipoVehiculos.get(i).getNombre();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.support_simple_spinner_dropdown_item, arrayTipoVenhiculo);
        tipoVehiculoSpinner.setAdapter(arrayAdapter);
    }


    private boolean tipoVehiculoIsEmpty() {
        return tipoVehiculos == null || tipoVehiculos.isEmpty();
    }

    private void hideComponents() {
        tipoVehiculoSpinner.setVisibility(View.GONE);
        btnIngreso.setVisibility(View.GONE);
        btnSalida.setVisibility(View.GONE);
        cardInfoVehiculo.setVisibility(View.GONE);
    }

    private void showComponentsEntradaVehiculo() {
        tipoVehiculoSpinner.setVisibility(View.VISIBLE);
        btnIngreso.setVisibility(View.VISIBLE);
    }

    private void showComponentsSalidaVehiculo() {
        btnSalida.setVisibility(View.VISIBLE);
        cardInfoVehiculo.setVisibility(View.VISIBLE);
    }


}