package service;

import com.google.gson.Gson;
import model.Participante;
import model.Reto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GestorDatos {
    private final String RUTA_DATOS = "data/datos.json";
    private Gson gson;

    public GestorDatos() {
        this.gson = new Gson();
        crearArchivoSiNoExiste();
    }
    public static class DatosApp {
        List<Participante> participantes = new ArrayList<>();
        List<Reto> retos = new ArrayList<>();
        int ultimoIdReto = 0;
    }

    public DatosApp cargarDatos() {
        try {
            String contenido = Files.readString(Paths.get(RUTA_DATOS));

            if (contenido.isBlank()) {
                return new DatosApp();
            }

            return gson.fromJson(contenido, DatosApp.class);

        } catch (Exception e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
            return new DatosApp();
        }
    }

    public void guardarDatos(DatosApp datos) {
        try (FileWriter writer = new FileWriter(RUTA_DATOS)) {
            gson.toJson(datos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    private void crearArchivoSiNoExiste() {
        try {
            File archivo = new File(RUTA_DATOS);

            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs();
                archivo.createNewFile();

                guardarDatos(new DatosApp());

                System.out.println("Archivo datos.json creado");
            }
        } catch (IOException e) {
            System.out.println("Error creando archivo: " + e.getMessage());
        }
    }
}
