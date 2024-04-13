package co.edu.uniquindio.citas.PatronColas;



public class Cola<T> {

    //--------------------------atributos----------------------
    private Nodo<T> nodoFrente;
    private Nodo<T> nodoUltimo;
    private int tamanio;

    //------------------constructor--------------------------------------
    public Cola() {
        this.nodoFrente = null;
        this.nodoUltimo = null;
        this.tamanio = 0;
    }

    //--------------------metodos-------------------------


    //Agregar al final de la cola
    public void agregarfinal(T valorNodo) {
        Nodo<T> nodo = new Nodo  (valorNodo);

        if (estaVacia()) {
            nodoFrente = nodoUltimo = nodo;
        } else {
            nodoUltimo.setSiguienteNodo(nodo);
            nodoUltimo = nodo;
        }
        tamanio++;
    }

    //Verificar si la cola esta vacia
    public boolean estaVacia() {
        return (nodoFrente == null) ? true : false;
    }


    public boolean verificarIgualdadColas(Cola<T> cola1, Cola<T> cola2) {

        Nodo<T> auxiliarCola1 = cola1.getNodoFrente();

        Nodo<T> auxiliarCola2 = cola2.getNodoFrente();

        boolean bandera = true;
        while (auxiliarCola1 != null || auxiliarCola2 != null) {
            if (!(auxiliarCola1.getValorNodo().equals(auxiliarCola2.getValorNodo()))) {
                bandera = false;
            }
            auxiliarCola1 = auxiliarCola1.getSiguienteNodo();
            auxiliarCola2 = auxiliarCola2.getSiguienteNodo();
        }
        return bandera;
    }
    public T obtenerValorNodo(int indice) {
        Nodo<T> nodoTemporal = null;
        int contador = 0;
        if(indiceValido(indice)){
            nodoTemporal = nodoFrente;
            while (contador < indice) {
                nodoTemporal = nodoTemporal.getSiguienteNodo();
                contador++;
            }
        }if(nodoTemporal != null)
            return nodoTemporal.getValorNodo();
        else
            return null;
    }
    private boolean indiceValido(int indice) {
        if( indice >= 0 && indice < tamanio ) {
            return true;
        }
        throw new IndexOutOfBoundsException("Indice no valido");
    }


    //Elimina el primer nodo de la cola
    public T eliminarPrimero() {

        if (!estaVacia()) {
            Nodo<T> n = nodoFrente;
            T valor = n.getValorNodo();
            nodoFrente = n.getSiguienteNodo();

            if (nodoFrente == null) {
//				nodoUltimo = null;
            }

            tamanio--;
            return valor;
        }

        throw new RuntimeException("Lista vac�a");
    }

    //obtener nodo primero
    public Nodo<T> obtenetNodoFrente() {
        return nodoFrente;
    }


    public void imprimirLista() {

        Nodo<T> aux = nodoFrente;

        while (aux != null) {
            System.out.print(aux.getValorNodo() + "\t");
            aux = aux.getSiguienteNodo();
        }

        System.out.println();
    }


    //--------------------getter y setter-------------------
    public Nodo<T> getNodoFrente() {
        return nodoFrente;
    }

    public void setNodoFrente(Nodo<T> nodoFrente) {
        this.nodoFrente = nodoFrente;
    }

    public Nodo<T> getNodoUltimo() {
        return nodoUltimo;
    }

    public void setNodoUltimo(Nodo<T> nodoUltimo) {
        this.nodoUltimo = nodoUltimo;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
}
