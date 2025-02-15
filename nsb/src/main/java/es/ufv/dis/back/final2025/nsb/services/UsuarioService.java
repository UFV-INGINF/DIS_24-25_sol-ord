package es.ufv.dis.back.final2025.nsb.services;

import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import es.ufv.dis.back.final2025.nsb.inputOutput.JSONManager;
import es.ufv.dis.back.final2025.nsb.model.Usuario;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;

@Service
public class UsuarioService {

    public ArrayList<Usuario> getUsuarios() {
        JSONManager jsonManager = new JSONManager();
        return jsonManager.readJSONFromFile("usuarios.json");
    }

    public Usuario getUsuario(String id) {
        JSONManager jsonManager = new JSONManager();
        ArrayList<Usuario> usuarios = jsonManager.readJSONFromFile("usuarios.json");
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario addUsuario(Usuario usuario) {
        JSONManager jsonManager = new JSONManager();
        usuario.setId(java.util.UUID.randomUUID().toString());
        ArrayList<Usuario> usuarios = jsonManager.readJSONFromFile("usuarios.json");
        usuarios.add(usuario);
        jsonManager.writeJSONToFile(usuarios, "usuarios.json");
        return usuario;
    }

    public Usuario updateUsuario(String id, Usuario usuario) {
        JSONManager jsonManager = new JSONManager();
        ArrayList<Usuario> usuarios = jsonManager.readJSONFromFile("usuarios.json");
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                usuario.setId(id);
                usuarios.set(i, usuario);
                jsonManager.writeJSONToFile(usuarios, "usuarios.json");
                return usuario;
            }
        }
        return null;
    }

    public boolean createPDF () {
        try {

            JSONManager jsonManager = new JSONManager();
            ArrayList<Usuario> usuarios = jsonManager.readJSONFromFile("usuarios.json");
            Document doc = new Document(PageSize.A4, 50, 50, 100, 72);

            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("pdf-files/usuarios.pdf"));

            doc.open();

            for (Usuario usuario : usuarios) {
                doc.add(new Paragraph("ID: " + usuario.getId()));
                doc.add(new Paragraph("Nombre: " + usuario.getNombre()));
                doc.add(new Paragraph("Apellidos: " + usuario.getApellidos()));
                doc.add(new Paragraph("NIF: " + usuario.getNif()));
                doc.add(new Paragraph("Email: " + usuario.getEmail()));
                doc.add(new Paragraph("Dirección: " + usuario.getDireccion().toString()));
                doc.add(new Paragraph("Método de pago: " + usuario.getMetodoPago().toString()));
                doc.add(new Paragraph(" "));
            }
            doc.close();
            return true;
        }
        catch ( Exception e ) {
            e.printStackTrace();
            return false;
        }

    }

}
