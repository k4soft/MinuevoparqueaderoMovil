package co.com.k4soft.minuevoparqueadero.view.maestro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.k4soft.minuevoparqueadero.menu.R;

public class RegistroTipoVehiculoActivity extends AppCompatActivity implements RegistroTipoVehiculo.View{

    @BindView(R.id.txtNombre)
    public EditText txtNombre;
    @BindView(R.id.txtPrecio)
    public EditText txtPrecio;


    private ArrayAdapter<String> arrayAdapter;
    RegistroTipoVehiculo.Presenter presenter;

    @Override
    public String getNombreTipoVehiculo() {
        return txtNombre.getText().toString();
    }

    @Override
    public String getPrecio() {
        return txtPrecio.getText().toString();
    }

    @Override
    public void informacionGuardadaSatisfactoriament() {
       Toast.makeText(this,R.string.satisfactorio,Toast.LENGTH_SHORT).show();
       finish();
    }

    @Override
    public void requerirNombreTipoVehiculo() {
        txtNombre.setError(getString(R.string.requerido));
    }

    @Override
    public void requerirPrecio() {
        txtPrecio.setError(getString(R.string.requerido));
    }


    public void guardar(View view) {
        presenter.guardar();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tipo_vehiculo);
        ButterKnife.bind(this);
        initComponent();
    }

    private void initComponent() {
        presenter = new RegistroTipoVehiculoPresenter(this,getApplicationContext());
    }


}
