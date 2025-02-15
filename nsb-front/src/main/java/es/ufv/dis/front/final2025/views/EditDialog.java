package es.ufv.dis.front.final2025.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
import es.ufv.dis.front.final2025.model.Direccion;
import es.ufv.dis.front.final2025.model.MetodoPago;
import es.ufv.dis.front.final2025.model.Usuario;
import es.ufv.dis.front.final2025.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

public class EditDialog extends Dialog {
    private TextField nombreField;
    private TextField apellidosField;
    private TextField dniField;
    private TextField emailField;
    //Datos de la dirección

    private TextField calleField;
    private TextField numeroField;
    private TextField codigoPostalField;
    private TextField pisoLetraField;
    private TextField ciudadField;

    //Datos de la tarjeta
    private TextField numeroTarjetaField;
    private TextField nombreAsociadoField;

    private Button saveButton;
    private Button cancelButton;


    public EditDialog(Usuario usuario, @Autowired UsuarioService service) {
        // TODO Auto-generated constructor stub
        setWidth("1000px");
        setHeight("600px");
        setModal(true);
        setDraggable(true);
        setResizable(false);
        getElement().getStyle().set("background", "rgba(0, 0, 0, 0.5)");

        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout datosPersonales = new HorizontalLayout();
        HorizontalLayout datosDireccion = new HorizontalLayout();
        HorizontalLayout datosPago = new HorizontalLayout();
        layout.addClassName("centered-content");

        nombreField = new TextField("Nombre");
        nombreField.setValue(usuario.getNombre());
        apellidosField = new TextField("Apellidos");
        apellidosField.setValue(usuario.getApellidos());
        dniField = new TextField("DNI");
        dniField.setValue(usuario.getNif());
        emailField = new TextField("Email");
        emailField.setValue(usuario.getEmail());

        calleField = new TextField("Calle");
        calleField.setValue(usuario.getDireccion().getCalle());
        codigoPostalField = new TextField("CP");
        codigoPostalField.setValue(usuario.getDireccion().getCodigoPostal());
        pisoLetraField = new TextField("Piso/Letra");
        pisoLetraField.setValue(usuario.getDireccion().getPisoLetra());
        ciudadField = new TextField("Ciudad");
        ciudadField.setValue(usuario.getDireccion().getCiudad());
        numeroField = new TextField("Número");
        numeroField.setValue(String.valueOf(usuario.getDireccion().getNumero()));

        numeroTarjetaField = new TextField("Número de tarjeta");
        numeroTarjetaField.setValue(usuario.getMetodoPago().getNumeroTarjeta());
        nombreAsociadoField = new TextField("Nombre asociado");
        nombreAsociadoField.setValue(usuario.getMetodoPago().getNombreAsociado());

        datosPersonales.add(nombreField, apellidosField, dniField, emailField);
        datosDireccion.add(calleField, numeroField, codigoPostalField, pisoLetraField, ciudadField);
        datosPago.add(numeroTarjetaField, nombreAsociadoField);

        saveButton = new Button("Guardar", event -> {
            // Guardar los cambios

            Usuario usuarioUpdated = new Usuario(
                    nombreField.getValue(),
                    apellidosField.getValue(),
                    dniField.getValue(),
                    emailField.getValue(),
                    new Direccion(
                            calleField.getValue(),
                            Integer.parseInt(numeroField.getValue()),
                            codigoPostalField.getValue(),
                            pisoLetraField.getValue(),
                            ciudadField.getValue()
                    ),
                    new MetodoPago(
                            numeroTarjetaField.getValue(),
                            nombreAsociadoField.getValue()
                    )
            );
            usuarioUpdated.setId(usuario.getId());
            service.updateUser(usuario.getId(), usuarioUpdated);
            close();
            fireEvent(new SaveEvent(this));
        });

        cancelButton = new Button("Cancelar", event -> close());

        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);

        layout.add(new H3("Datos Personales"), datosPersonales, new H3("Dirección"), datosDireccion, new H3("Datos Pago"), datosPago, buttonsLayout);
        add(layout);
    }

    public static class SaveEvent extends ComponentEvent<EditDialog> {
        public SaveEvent(EditDialog source) {
            super(source, false);
        }
    }

    public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
        return addListener(SaveEvent.class, listener);
    }
}



