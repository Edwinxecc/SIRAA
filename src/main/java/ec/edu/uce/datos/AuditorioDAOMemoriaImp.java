package ec.edu.uce.datos;

import ec.edu.uce.dominio.Auditorio;
import java.util.Arrays;

public class AuditorioDAOMemoriaImp implements AuditorioDAO {
    private static Auditorio[] auditorios;
    private static int cantAuditorios;

    static {
        auditorios = new Auditorio[10];
        cantAuditorios = 0;
    }

    @Override
    public void crear(Auditorio auditorio) {
        if (auditorio == null || buscarPorCodigo(auditorio.getCodigoAuditorio()) != null) {
            return;
        }
        if (cantAuditorios == auditorios.length) {
            auditorios = Arrays.copyOf(auditorios, auditorios.length * 2);
        }
        auditorios[cantAuditorios] = auditorio;
        cantAuditorios++;
    }

    @Override
    public void editar(Auditorio auditorio) {
        if (auditorio == null) return;
        for (int i = 0; i < cantAuditorios; i++) {
            if (auditorios[i] != null && auditorios[i].getCodigoAuditorio().equals(auditorio.getCodigoAuditorio())) {
                auditorios[i] = auditorio;
                return;
            }
        }
    }

    @Override
    public void eliminar(String codigo) {
        int indiceAEliminar = -1;
        for (int i = 0; i < cantAuditorios; i++) {
            if (auditorios[i] != null && auditorios[i].getCodigoAuditorio().equals(codigo)) {
                indiceAEliminar = i;
                break;
            }
        }
        if (indiceAEliminar != -1) {
            System.arraycopy(auditorios, indiceAEliminar + 1, auditorios, indiceAEliminar, cantAuditorios - 1 - indiceAEliminar);
            auditorios[--cantAuditorios] = null;
        }
    }

    @Override
    public Auditorio buscarPorCodigo(String codigo) {
        for (int i = 0; i < cantAuditorios; i++) {
            if (auditorios[i] != null && auditorios[i].getCodigoAuditorio().equals(codigo)) {
                return auditorios[i];
            }
        }
        return null;
    }

    @Override
    public Auditorio[] consultarAuditorios() {
        return Arrays.copyOf(auditorios, cantAuditorios);
    }
}