package ec.edu.uce.datos;

import ec.edu.uce.dominio.Facultad;
import java.util.Arrays;

public class FacultadDAOMemoriaImp implements FacultadDAO {
    private static Facultad[] facultades;
    private static int cantFacultades;

    static {
        facultades = new Facultad[5];
        cantFacultades = 0;
    }

    @Override
    public void crear(Facultad facultad) {
        if (facultad == null || buscarPorCodigo(facultad.getCodigoFacultad()) != null) {
            return;
        }
        if (cantFacultades == facultades.length) {
            facultades = Arrays.copyOf(facultades, facultades.length * 2);
        }
        facultades[cantFacultades] = facultad;
        cantFacultades++;
    }

    @Override
    public void editar(Facultad facultad) {
        if (facultad == null) return;
        for (int i = 0; i < cantFacultades; i++) {
            if (facultades[i] != null && facultades[i].getCodigoFacultad().equals(facultad.getCodigoFacultad())) {
                facultades[i] = facultad;
                return;
            }
        }
    }

    @Override
    public void eliminar(String codigo) {
        int indiceAEliminar = -1;
        for (int i = 0; i < cantFacultades; i++) {
            if (facultades[i] != null && facultades[i].getCodigoFacultad().equals(codigo)) {
                indiceAEliminar = i;
                break;
            }
        }
        if (indiceAEliminar != -1) {
            System.arraycopy(facultades, indiceAEliminar + 1, facultades, indiceAEliminar, cantFacultades - 1 - indiceAEliminar);
            facultades[--cantFacultades] = null;
        }
    }

    @Override
    public Facultad buscarPorCodigo(String codigo) {
        for (int i = 0; i < cantFacultades; i++) {
            if (facultades[i] != null && facultades[i].getCodigoFacultad().equals(codigo)) {
                return facultades[i];
            }
        }
        return null;
    }

    @Override
    public Facultad[] consultarFacultades() {
        return Arrays.copyOf(facultades, cantFacultades);
    }
}