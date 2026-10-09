public class ListaReproduccion {

    private Nodo inicio;
    private Nodo fin;
    private Nodo actual;
    private int tamaño;

    public ListaReproduccion() {
        this.inicio = null;
        this.fin = null;
        this.actual = null;
        this.tamaño = 0;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public void agregarAlInicio(Cancion cancion) {
        Nodo nuevo = new Nodo(cancion);
        if (estaVacia()) {
            inicializarListaUnNodo(nuevo);
        } else {
            nuevo.siguiente = inicio;
            nuevo.anterior = fin;
            inicio.anterior = nuevo;
            fin.siguiente = nuevo;
            inicio = nuevo;
            tamaño++;
        }
    }

    public void agregarAlFinal(Cancion cancion) {
        Nodo nuevo = new Nodo(cancion);
        if (estaVacia()) {
            inicializarListaUnNodo(nuevo);
        } else {
            nuevo.anterior = fin;
            nuevo.siguiente = inicio;
            fin.siguiente = nuevo;
            inicio.anterior = nuevo;
            fin = nuevo;
            tamaño++;
        }
    }

    private void inicializarListaUnNodo(Nodo nodo) {
        inicio = fin = actual = nodo;
        nodo.siguiente = nodo;
        nodo.anterior = nodo;
        tamaño = 1;
    }

    public Nodo buscarPorId(int id) {
        if (estaVacia()) return null;
        Nodo temp = inicio;
        do {
            if (temp.cancion.getId() == id) return temp;
            temp = temp.siguiente;
        } while (temp != inicio);
        return null;
    }

    public void seleccionarPorId(int id) {
        Nodo nodo = buscarPorId(id);
        if (nodo != null) {
            actual = nodo;
            System.out.println("Canción actual actualizada a: " + actual.cancion);
        } else {
            System.out.println("Error: Canción con ID " + id + " no encontrada.");
        }
    }

    public void eliminarActual() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        System.out.println("Eliminando: " + actual.cancion);

        if (tamaño == 1) { // Único elemento
            inicio = fin = actual = null;
        } else {
            Nodo ant = actual.anterior;
            Nodo sig = actual.siguiente;

            ant.siguiente = sig;
            sig.anterior = ant;

            if (actual == inicio) inicio = sig;
            if (actual == fin) fin = ant;

            actual = sig; // La nueva actual es la siguiente
        }
        tamaño--;
    }

    public void eliminarPorId(int id) {
        Nodo nodo = buscarPorId(id);
        if (nodo == null) {
            System.out.println("Error: No se puede eliminar. ID " + id + " no encontrado.");
            return;
        }

        if (nodo == actual) {
            eliminarActual();
        } else {
            Nodo ant = nodo.anterior;
            Nodo sig = nodo.siguiente;
            ant.siguiente = sig;
            sig.anterior = ant;

            if (nodo == inicio) inicio = sig;
            if (nodo == fin) fin = ant;

            tamaño--;
            System.out.println("Se eliminó la canción: " + nodo.cancion.getTitulo());
        }
    }

    public void mostrarActual() {
        if (estaVacia()) System.out.println("No hay canción actual. Lista vacía.");
        else System.out.println("Reproduciendo actual: " + actual.cancion);
    }

    public void avanzar() {
        if (!estaVacia()) {
            actual = actual.siguiente;
            System.out.println("Avanzó a: " + actual.cancion);
        }
    }

    public void retroceder() {
        if (!estaVacia()) {
            actual = actual.anterior;
            System.out.println("Retrocedió a: " + actual.cancion);
        }
    }

    public void mostrarLista() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        System.out.println("--- Orden Normal (Hacia adelante) ---");
        Nodo temp = inicio;
        do {
            System.out.println((temp == actual ? "-> " : "   ") + temp.cancion);
            temp = temp.siguiente;
        } while (temp != inicio);

        System.out.println("--- Orden Inverso (Hacia atrás) ---");
        temp = fin;
        do {
            System.out.println((temp == actual ? "-> " : "   ") + temp.cancion);
            temp = temp.anterior;
        } while (temp != fin);
    }

    public void simularReproduccion(int k) {
        if (estaVacia() || k <= 0) return;
        System.out.println("Simulando " + k + " reproducciones...");
        for (int i = 0; i < k; i++) {
            System.out.println("Sonando: " + actual.cancion);
            actual = actual.siguiente;
        }
        System.out.println("La reproducción se detuvo en: " + actual.cancion);
    }

    public int getTamaño() { return tamaño; }
}