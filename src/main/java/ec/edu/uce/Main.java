package ec.edu.uce;

import ec.edu.uce.consola.Menu;
import ec.edu.uce.dominio.Facultad;
import ec.edu.uce.dominio.Universidad;

public class Main {
    public static void main(String[] args) {
        // Inicializar la universidad (Singleton)
        Universidad universidad = Universidad.getInstancia();
        
        // Obtener la primera facultad para el menú (o crear una si no existe)
        Facultad facultadPrincipal = null;
        if (!universidad.getFacultades().isEmpty()) {
            facultadPrincipal = universidad.getFacultades().get(0);
        } else {
            facultadPrincipal = new Facultad("Facultad de Ingeniería", 3);
            universidad.crearFacultad(facultadPrincipal);
        }

        // Crear y mostrar el menú principal
        Menu menuPrincipal = new Menu(facultadPrincipal);
        menuPrincipal.menuDeInicio();
        /*
        implementar los metodos en menu, inicializar los objetos
         */
    }
}