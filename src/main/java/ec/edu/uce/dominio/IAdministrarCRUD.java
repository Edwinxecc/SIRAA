package ec.edu.uce.dominio;
/**
 Interfaz genérica para operaciones CRUD. */ public interface IAdministrarCRUD {
    /**
     Método que permite crear un nuevo Objeto de cualquier clase.

     @param obj Es el nuevo Objeto que se va a crear
     @return Un mensaje para alertar al usuario */ String nuevo(Object obj);
    /**
     Método que permite modificar el estado de un Objeto

     @param obj El Objeto a ser actualizado
     @return Un mensaje para alertar al usuario */ String editar(Object obj);
    /**
     Método que permite eliminar un Objeto

     @param obj El Objeto a ser eliminado
     @return Un mensaje para alertar al usuario */ String borrar(Object obj);
    /**
     Método que permite buscar un Objeto

     @param id El id del Objeto que se está buscando
     @return El Objeto encontrado, si el objeto no existe, retorna null */ Object buscarPorId(Integer id);
    /**
     Método que permite listar un conjunto de Objetos

     @return La lista de Objetos almacenados */ String listar(); }