package ec.edu.uce.dominio;


public class ParametroYPoliticas {
    private int id;
    private String politica;
    private String valor;

    public ParametroYPoliticas(int id, String politica, String valor) {
        this.id = id;
        this.politica = politica;
        this.valor = valor;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPolitica() { return politica; }
    public void setPolitica(String politica) { this.politica = politica; }

    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }

    // Métodos CRUD simulados
    public void crear() {
        System.out.println("Parámetro creado: " + politica);
    }

    public void leer() {
        System.out.println("Política: " + politica + " - Valor: " + valor);
    }

    public void actualizar(String nuevaPolitica, String nuevoValor) {
        this.politica = nuevaPolitica;
        this.valor = nuevoValor;
        System.out.println("Parámetro actualizado.");
    }

    public void eliminar() {
        System.out.println("Parámetro eliminado: " + politica);
    }
}

