package es.ufv.dis.front.final2025.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import es.ufv.dis.front.final2025.model.Usuario;
import es.ufv.dis.front.final2025.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MainContents extends VerticalLayout {

    public MainContents(@Autowired UsuarioService service) {
        addClassName("centered-content");
        // TODO Auto-generated constructor stub

        ArrayList<Usuario> usuarios = service.getUsers();

        Grid<Usuario> grid = new Grid<>(Usuario.class, false);
        grid.addClassName("grid");
        grid.addColumn(Usuario -> Usuario.getNombre()).setHeader("Nombre").setFlexGrow(0)
                .setWidth("230px");
        grid.addColumn(Usuario -> Usuario.getApellidos()).setHeader("Apellidos").setFlexGrow(0)
                .setWidth("230px");
        grid.addColumn(Usuario -> Usuario.getNif()).setHeader("NIF").setFlexGrow(0)
                .setWidth("230px");
        grid.addColumn(Usuario -> Usuario.getEmail()).setHeader("Email");
        grid.addColumn(
                new NativeButtonRenderer<>("Editar",
                        clickedItem -> {
                            EditDialog editDialog = new EditDialog(clickedItem, service);
                            editDialog.addSaveListener(e -> {
                                List<Usuario> updatedData = service.getUsers();
                                grid.setItems(updatedData);
                                grid.getDataProvider().refreshAll();
                            });
                            editDialog.open();
                        })
        );

        grid.setItems(usuarios);

        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        grid.addItemDoubleClickListener(event -> {
            DetailView detailView = new DetailView(event.getItem(), service);
            detailView.open();
        });

        add(grid);

        HorizontalLayout buttons = new HorizontalLayout();


        // Generación del pdf
        Button pdfButton = new Button("Genera PDF", e -> {
            add(new Paragraph("Generando PDF..."));
            service.createPDF();
        });

        Button addButton = new Button("Añadir Usuario", e -> {
            AddDialog addDialog = new AddDialog(service);
            addDialog.addSaveListener(eventAdd -> {
                List<Usuario> updatedData = service.getUsers();
                grid.setItems(updatedData);
                grid.getDataProvider().refreshAll();
            });
            addDialog.open();
        });
        pdfButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttons.add(addButton, pdfButton);
        add (buttons);


    }


}
