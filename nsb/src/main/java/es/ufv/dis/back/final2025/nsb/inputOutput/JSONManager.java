package es.ufv.dis.back.final2025.nsb.inputOutput;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import es.ufv.dis.back.final2025.nsb.model.Usuario;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONManager {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //ArrayList to JSON in file
    public void writeJSONToFile(ArrayList<Usuario> Usuario, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            gson.toJson(Usuario, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Usuario> readJSONFromFile(String path) {
        if (!Files.exists(Paths.get(path))) {
            System.out.println("File does not exist: " + path);
            return null;
        }
        try (FileReader reader = new FileReader(path)) {
            Type listType = new TypeToken<ArrayList<Usuario>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
