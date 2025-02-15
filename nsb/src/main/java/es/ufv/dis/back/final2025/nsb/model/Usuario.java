package es.ufv.dis.back.final2025.nsb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class Usuario {

    private String id;
    private String nombre;
    private String apellidos;
    private String nif;
    private String email;
    private Direccion direccion;
    private MetodoPago metodoPago;

    public Usuario() {

    }

    public Usuario(String nombre, String apellidos, String nif, String email, Direccion direccion, MetodoPago metodoPago) {
        this.id = this.id == null ? UUID.randomUUID().toString() : this.id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nif = nif;
        this.email = email;
        this.direccion = direccion;
        this.metodoPago = metodoPago;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                ", direccion=" + direccion +
                ", metodoPago=" + metodoPago +
                '}';
    }
}
