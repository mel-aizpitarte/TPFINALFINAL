import Excepciones.AccionInvalidaEx;
import Prisoners.Prisionero;
import Guards.Guardia;

import java.util.HashMap;

public class Manager <T>{
    private HashMap<String, T> lista;

    public Manager() {
        this.lista = new HashMap<>();
    }

    public HashMap<String, T> getLista() {
        return lista;
    }

    public void agregar (String clave, T elemento){
        if(this.lista.containsKey(clave)){
            System.out.println("Ya existe un elemento con la clave: " + clave);
        } else {
            this.lista.put(clave,elemento);
            System.out.println("Elemento agregado correctamente");
        }
    }

    public void eliminar (String clave){
        if(!this.lista.containsKey(clave)){
            System.out.println("No existe un elemento con la clave: " + clave);
        } else{
            this.lista.remove(clave);
            System.out.println("Elemento eliminado correctamente");
        }

    }

    public boolean existe (String clave) {
        return this.lista.containsKey(clave);
    }

    public T obtener (String clave) {
        return this.lista.get(clave);
    }

    public void mostrar(){
        if (lista.isEmpty()){
            System.out.println("No hay elementos cargados");
        } else {
            for (T elem :  this.lista.values()){
                System.out.println(elem);
            }
        }
    }

    @Override
    public String toString() {
        return "Manager{" +
                "lista=" + lista +
                '}';
    }
}
