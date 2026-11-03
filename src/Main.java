/*
 * Entrega: Arreglos y Listas Enlazadas en Java
 * Integrantes: (escriban aquí los nombres completos, máximo 3)
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("=========:=================:================:");
        System.out.println("             ARREGLOS              ");
        System.out.println("=========:=================:================:\n");

        int[] arreglo = {2, 4, 6, 8, 10};
        System.out.println("Indice del valor 2: " + search(arreglo, 2));
        System.out.println("Indice del valor 1: " + search(arreglo, 1));   // no existe -> -1
        System.out.println("Indice del valor 10: " + search(arreglo, 10));

        System.out.println("Suma total de los elementos: " + suma(arreglo));

        reverse(arreglo); // modifica el mismo arreglo (in situ)
        System.out.print("Arreglo invertido: ");
        // Recorre el arreglo de la posición 0 a la última para imprimirlo
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i] + " ");
        }
        System.out.println();

        int[] arreglocon0 = {0, 1, 0, 3, 12};
        move0end(arreglocon0);
        System.out.print("Arreglo con ceros movidos al final: ");
        for (int i = 0; i < arreglocon0.length; i++) {
            System.out.print(arreglocon0[i] + " ");
        }
        System.out.println();

        int[] arregloIncompleto = {1, 2, 4, 5};
        int N = 5; // el arreglo debería contener los números de 1 a N
        int resultado = encontrarNum(arregloIncompleto, N);
        System.out.println("El número faltante es: " + resultado); // Imprime 3

        // ---------- LISTAS ENLAZADAS ----------
        System.out.println("=========:=================:================:");
        System.out.println("             LISTAS ENLAZADAS              ");
        System.out.println("=========:=================:================:");

        System.out.println();
        ListaEnlazada lista = new ListaEnlazada();
        lista.insertAtEnd(1);
        lista.insertAtEnd(1);
        lista.insertAtEnd(2);
        lista.insertAtEnd(3);
        lista.insertAtEnd(3);
        System.out.print("Lista original: ");
        lista.imprimir();

        lista.eliminarDuplicados();
        System.out.print("Lista sin duplicados: ");
        lista.imprimir();

        Nodo n = lista.nodoDesdeElFinal(2);
        System.out.println("Nodo 2 desde el final: " + (n != null ? n.dato : "no existe"));

        lista.invertir();
        System.out.print("Lista invertida: ");
        lista.imprimir();

        ListaEnlazada lista2 = new ListaEnlazada();
        lista2.insertAtEnd(1);
        lista2.insertAtEnd(2);
        lista2.insertAtEnd(3);
        System.out.println("Tiene bucle (sin ciclo): " + lista2.detectarBucle());

        // El último nodo apunta de nuevo a la cabeza: se forma un ciclo
        lista2.cabeza.siguiente.siguiente.siguiente = lista2.cabeza;
        System.out.println("Tiene bucle (con ciclo): " + lista2.detectarBucle());
    }

    /*
     * Búsqueda lineal: recorre el arreglo posición por posición.
     * Si encuentra el valor devuelve su índice; si termina el ciclo sin
     * encontrarlo devuelve -1. Complejidad: O(n).
     */
    public static int search(int[] arreglo, int valor) {
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Suma total: el ciclo visita cada elemento una sola vez y lo
     * acumula en la variable "suma". Complejidad: O(n).
     */
    public static int suma(int[] arreglo) {
        int suma = 0;
        for (int i = 0; i < arreglo.length; i++) {
            suma += arreglo[i];
        }
        return suma;
    }

    /*
     * Invertir in situ con dos índices: "inicio" parte del primer elemento
     * y "fin" del último. En cada vuelta del while se intercambian ambos
     * valores (usando "temp") y los índices avanzan hacia el centro.
     * Se detiene cuando se cruzan. No usa un arreglo extra: O(n) tiempo, O(1) memoria.
     */
    public static int[] reverse(int[] arreglo) {
        int inicio = 0;
        int fin = arreglo.length - 1;
        while (inicio < fin) {
            int temp = arreglo[inicio];
            arreglo[inicio] = arreglo[fin];
            arreglo[fin] = temp;
            inicio++;
            fin--;
        }
        return arreglo;
    }

    /*
     * Mover ceros al final, ejemplo: (0,1,0,3,12) -> (1,3,12,0,0).
     * Primer ciclo: copia cada elemento distinto de cero a la posición
     * "idxNoCero" (que solo avanza al copiar), conservando el orden.
     * Segundo ciclo: rellena con ceros las posiciones restantes. O(n).
     */
    public static int[] move0end(int[] arreglo) {
        int idxNoCero = 0;
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] != 0) {
                arreglo[idxNoCero++] = arreglo[i];
            }
        }
        while (idxNoCero < arreglo.length) {
            arreglo[idxNoCero++] = 0;
        }
        return arreglo;
    }

    /*
     * Número faltante entre 1 y N: la suma ideal de 1..N es N(N+1)/2
     * (fórmula de Gauss). El ciclo suma lo que realmente hay en el arreglo;
     * la diferencia entre ambas sumas es el número que falta. Se usa long
     * para evitar desbordamiento con valores grandes. O(n).
     */
    public static int encontrarNum(int[] arr, int N) {
        long sumaIdeal = (long) N * (N + 1) / 2;
        long sumaReal = 0;
        for (int num : arr) {
            sumaReal += num;
        }

        return (int) (sumaIdeal - sumaReal);
    }


    /* ==========================================
      LISTAS ENLAZADAS
      ========================================== */

    /* Nodo: guarda un dato y un puntero (referencia) al siguiente nodo. */
    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
        }
    }

    static class ListaEnlazada {
        Nodo cabeza; // primer nodo de la lista (null si está vacía)

        /*
         * RETO 1: InsertAtEnd (Insertar al Final).
         * Si la lista está vacía, el nuevo nodo pasa a ser la cabeza.
         * Si no, el while avanza "actual" hasta el último nodo (el que tiene
         * siguiente == null) y se reasigna su puntero para apuntar al nuevo nodo.
         */
        public void insertAtEnd(int dato) {
            Nodo nuevoNodo = new Nodo(dato);

            if (cabeza == null) {
                cabeza = nuevoNodo;
                return;
            }

            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }

        /*
         * RETO 2: Invertir Lista.
         * Se usan tres punteros: "prev" (nodo ya invertido), "actual" (nodo
         * que se procesa) y "sig" (guarda el resto de la lista).
         * En cada vuelta:
         *   1) sig = actual.siguiente  -> se guarda el siguiente antes de perderlo
         *   2) actual.siguiente = prev -> se invierte el puntero hacia atrás
         *   3) prev = actual; actual = sig -> se avanza un nodo
         * Al terminar, "prev" es el último nodo original: la nueva cabeza. O(n).
         */
        public void invertir() {
            Nodo prev = null;
            Nodo actual = cabeza;
            Nodo sig = null;

            while (actual != null) {
                sig = actual.siguiente;
                actual.siguiente = prev;
                prev = actual;
                actual = sig;
            }
            cabeza = prev;
        }

        /*
         * RETO 3: Nodo N desde el Final (dos punteros con separación de N).
         * El for adelanta "rapido" N nodos (si la lista es más corta, devuelve null).
         * Luego el while mueve ambos punteros a la vez hasta que "rapido" llega
         * al final; como llevan N nodos de diferencia, "lento" queda justo en
         * el nodo N desde el final. Una sola pasada: O(n).
         */
        public Nodo nodoDesdeElFinal(int n) {
            Nodo rapido = cabeza;
            Nodo lento = cabeza;

            for (int i = 0; i < n; i++) {
                if (rapido == null) return null;
                rapido = rapido.siguiente;
            }

            while (rapido != null) {
                rapido = rapido.siguiente;
                lento = lento.siguiente;
            }
            return lento;
        }

        /*
         * RETO 4: Eliminar Duplicados (Lista Ordenada).
         * Como está ordenada, los duplicados quedan juntos. Si el nodo actual
         * y el siguiente tienen el mismo dato, se reasigna el puntero
         * actual.siguiente para saltarse el repetido (queda sin referencias y
         * Java lo elimina). Solo se avanza cuando no hubo duplicado, por si
         * hay más de dos repetidos seguidos. O(n).
         */
        public void eliminarDuplicados() {
            Nodo actual = cabeza;

            while (actual != null && actual.siguiente != null) {
                if (actual.dato == actual.siguiente.dato) {
                    actual.siguiente = actual.siguiente.siguiente;
                } else {
                    actual = actual.siguiente;
                }
            }
        }

        /* Método auxiliar: recorre la lista de la cabeza hasta null imprimiendo cada dato. */
        public void imprimir() {
            Nodo actual = cabeza;
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("null");
        }

        /*
         * RETO 5: Detectar Bucle (Liebre y Tortuga, algoritmo de Floyd).
         * "lento" avanza 1 nodo y "rapido" avanza 2 por vuelta.
         * - Sin ciclo: "rapido" llega a null y el while termina -> false.
         * - Con ciclo: "rapido" termina alcanzando a "lento" dentro del
         *   ciclo (lento == rapido) -> true.
         * O(n) tiempo y O(1) memoria.
         */
        public boolean detectarBucle() {
            Nodo lento = cabeza;
            Nodo rapido = cabeza;

            while (rapido != null && rapido.siguiente != null) {
                lento = lento.siguiente;
                rapido = rapido.siguiente.siguiente;

                if (lento == rapido) {
                    return true;
                }
            }
            return false;
        }
    }
}