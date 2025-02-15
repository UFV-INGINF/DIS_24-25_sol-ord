package es.ufv.dis.front.final2025.views;

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


public class AddDialog extends Dialog {

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


    public AddDialog(@Autowired UsuarioService service) {
        setWidth("900px");
        setHeight("600px");
        setModal(true);
        setDraggable(true);
        setResizable(false);
        getElement().getStyle().set("background", "rgba(0, 0, 0, 0.5)");

        VerticalLayout layout = new VerticalLayout();
        layout.addClassName("centered-content");
        HorizontalLayout datosPersonales = new HorizontalLayout();
        HorizontalLayout datosDireccion = new HorizontalLayout();
        HorizontalLayout datosPago = new HorizontalLayout();

        nombreField = new TextField("Nombre");
        apellidosField = new TextField("Apellidos");
        dniField = new TextField("DNI");
        emailField = new TextField("Email");

        calleField = new TextField("Calle");
        codigoPostalField = new TextField("CP");
        pisoLetraField = new TextField("Piso/Letra");
        ciudadField = new TextField("Ciudad");
        numeroField = new TextField("Número");

        numeroTarjetaField = new TextField("Número de tarjeta");
        nombreAsociadoField = new TextField("Nombre asociado");

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
            service.addUser(usuarioUpdated);
            close();
            fireEvent(new AddDialog.SaveEventAdd(this));
        });

        cancelButton = new Button("Cancelar", event -> close());

        HorizontalLayout buttonsLayout = new HorizontalLayout(saveButton, cancelButton);


        layout.add(new H3("Datos Personales"), datosPersonales, new H3("Dirección"), datosDireccion, new H3("Datos Pago"), datosPago, buttonsLayout);
        add(layout);
    }

    public static class SaveEventAdd extends ComponentEvent<AddDialog> {
        public SaveEventAdd(AddDialog source) {
            super(source, false);
        }
    }

    public Registration addSaveListener(ComponentEventListener<AddDialog.SaveEventAdd> listener) {
        return addListener(AddDialog.SaveEventAdd.class, listener);
    }
}
