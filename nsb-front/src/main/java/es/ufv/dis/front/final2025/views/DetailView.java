package es.ufv.dis.front.final2025.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import es.ufv.dis.front.final2025.model.Usuario;
import es.ufv.dis.front.final2025.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

public class DetailView extends Dialog {

    private Div nombreField;
    private Div apellidosField;
    private Div dniField;
    private Div emailField;
    //Datos de la dirección

    private Div calleField;
    private Div numeroField;
    private Div codigoPostalField;
    private Div pisoLetraField;
    private Div ciudadField;

    //Datos de la tarjeta
    private Div numeroTarjetaField;
    private Div nombreAsociadoField;

    private Button saveButton;
    private Button cancelButton;

    public DetailView(Usuario usuario, @Autowired UsuarioService service) {
        // TODO Auto-generated constructor stub

        setWidth("600px");
        setHeight("400px");
        setModal(true);
        setDraggable(true);
        setResizable(false);
        getElement().getStyle().set("background", "rgba(0, 0, 0, 0.5)");

        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout datosPersonales = new HorizontalLayout();
        HorizontalLayout datosDireccion = new HorizontalLayout();
        HorizontalLayout datosPago = new HorizontalLayout();
        layout.addClassName("centered-content");

        nombreField = new Div("Nombre");
        nombreField.setText(usuario.getNombre());
        apellidosField = new Div("Apellidos");
        apellidosField.setText(usuario.getApellidos());
        dniField = new Div("DNI");
        dniField.setText(usuario.getNif());
        emailField = new Div("Email");
        emailField.setText(usuario.getEmail());

        calleField = new Div("Calle");
        calleField.setText(usuario.getDireccion().getCalle());
        codigoPostalField = new Div("CP");
        codigoPostalField.setText(usuario.getDireccion().getCodigoPostal());
        pisoLetraField = new Div("Piso/Letra");
        pisoLetraField.setText(usuario.getDireccion().getPisoLetra());
        ciudadField = new Div("Ciudad");
        ciudadField.setText(usuario.getDireccion().getCiudad());
        numeroField = new Div("Número");
        numeroField.setText(String.valueOf(usuario.getDireccion().getNumero()));

        numeroTarjetaField = new Div("Número de tarjeta");
        numeroTarjetaField.setText(usuario.getMetodoPago().getNumeroTarjeta());
        nombreAsociadoField = new Div("Nombre asociado");
        nombreAsociadoField.setText(usuario.getMetodoPago().getNombreAsociado());

        datosPersonales.add(nombreField, apellidosField, dniField, emailField);
        datosDireccion.add(calleField, numeroField, codigoPostalField, pisoLetraField, ciudadField);
        datosPago.add(numeroTarjetaField, nombreAsociadoField);


        cancelButton = new Button("Cerrar", event -> close());

        HorizontalLayout buttonsLayout = new HorizontalLayout(cancelButton);

        layout.add(new H3("Datos Personales"), datosPersonales, new H3("Dirección"), datosDireccion, new H3("Datos Pago"), datosPago, buttonsLayout);
        add(layout);

    }



}
