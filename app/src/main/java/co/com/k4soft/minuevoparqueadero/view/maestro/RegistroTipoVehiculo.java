package co.com.k4soft.minuevoparqueadero.view.maestro;

public interface RegistroTipoVehiculo {
    interface View{

        String getNombreTipoVehiculo();

        String getPrecio();

        void informacionGuardadaSatisfactoriament();

        void requerirNombreTipoVehiculo();

        void requerirPrecio();
    }

    interface Presenter{

        void guardar();

        void informacionGuardadaSatisfactoriament();

        void requerirNombreTipoVehiculo();

        void requerirPrecio();
    }

    interface Model{

        void guardar();

        void setNombreTipoVehiculo(String nombreTipoVehiculo);

        void setPrecio(String precio);
    }
}
