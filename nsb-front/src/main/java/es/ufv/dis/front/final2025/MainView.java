package es.ufv.dis.front.final2025;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import es.ufv.dis.front.final2025.services.UsuarioService;
import es.ufv.dis.front.final2025.views.MainContents;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service
     *            The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired UsuarioService service) {

        // Use TextField for standard text input
        TextField textField = new TextField("Your name");
        textField.addClassName("bordered");

        // Button click listeners can be defined as lambda expressions


        // Theme variants give you predefined extra styles for components.
        // Example: Primary button has a more prominent look.

        // You can specify keyboard shortcuts for buttons.
        // Example: Pressing enter in this view clicks the Button.

        // Use custom CSS classes to apply styling. This is defined in
        // styles.css.
        addClassName("centered-content");


        MainContents mainContents = new MainContents(service);
        add(mainContents);
    }
}
