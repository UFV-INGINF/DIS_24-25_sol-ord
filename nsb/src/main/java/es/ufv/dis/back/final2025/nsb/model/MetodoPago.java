package es.ufv.dis.back.final2025.nsb.model;

import com.google.gson.annotations.SerializedName;

public class MetodoPago {

    @SerializedName("numeroTarjeta")
    private String numeroTarjeta;
    @SerializedName("nombreAsociado")
    private String nombreAsociado;

    public MetodoPago() {

    }

    public MetodoPago(String numeroTarjeta, String nombreAsociado) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreAsociado = nombreAsociado;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNombreAsociado() {
        return nombreAsociado;
    }

    public void setNombreAsociado(String nombreAsociado) {
        this.nombreAsociado = nombreAsociado;
    }

    @Override
    public String toString() {
        return "MetodoPago{" +
                "numeroTarjeta=" + numeroTarjeta +
                ", nombreAsociado='" + nombreAsociado + '\'' +
                '}';
    }
}
