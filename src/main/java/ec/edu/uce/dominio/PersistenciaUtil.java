package ec.edu.uce.dominio;

import java.io.*;

public class PersistenciaUtil {
    // Guarda un objeto serializable en un archivo
    public static void guardarObjeto(Object obj, String archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            System.err.println("[Persistencia] Error al guardar: " + archivo + " - " + e.getMessage());
        }
    }

    // Carga un objeto serializable desde un archivo
    public static Object cargarObjeto(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return ois.readObject();
        } catch (FileNotFoundException e) {
            // No existe el archivo, es normal la primera vez
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("[Persistencia] Error al cargar: " + archivo + " - " + e.getMessage());
            return null;
        }
    }
} 