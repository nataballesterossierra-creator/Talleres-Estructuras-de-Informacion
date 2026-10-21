//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] arreglo={2,4,6,8,10};
        System.out.println("Indice del valor 2: "+search(arreglo,2));
        System.out.println("Indice del valor 1: "+search(arreglo,1));
        System.out.println("Indice del valor 10: "+search(arreglo,10));

        System.out.println("Suma total de los elementos: "+suma(arreglo));

        reverse(arreglo);
        System.out.print("Arreglo invertido: ");

        for (int j : arreglo) {
            System.out.print(j + " ");
        }
    }

    /*buscar y devolver indice*/
    public static int search(int[] arreglo,int valor){
        for (int i=0; i<arreglo.length; i++){
            if(arreglo[i]==valor){
                return i;
            }
        }
        return -1;
    }

    /*suma total de los elementos*/
    public static int suma(int[] arreglo){
        int suma=0;
        for (int i=0; i<arreglo.length; i++){
            suma+=arreglo[i];
        }
        return suma;
    }

    /*invertir el arreglo in situ (reverse)*/
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

    /*mover ceros al final*/


}
 /* ==========================================
       LISTAS ENLASADAS
       ========================================== */
 class Nodo {
     int dato;
     Nodo siguiente;

     Nodo(int dato) {
         this.dato = dato;
     }
 }

class ListaEnlazada {
    Nodo cabeza;

    /* ==========================================
       RETO 1: InsertAtEnd (Insertar al Final)
       Agrega un nodo al final de la cola.
       ========================================== */
    public void insertAtEnd(int dato) {
        Nodo nuevoNodo = new Nodo(dato);

        // Paso 1: si la cabeza es null, el nuevo nodo es la cabeza
        if (cabeza == null) {
            cabeza = nuevoNodo;
            return;
        }

        // Paso 2: recorrer hasta que actual.siguiente == null
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }

        // Paso 3: enlazar el nuevo nodo al final
        actual.siguiente = nuevoNodo;
    }

    /* ==========================================
       RETO 2: Invertir Lista (Pregunta de entrevista)
       A->B->null se vuelve B->A->null.
       ========================================== */
    public void invertir() {
        // Paso 1: tres punteros
        Nodo prev = null;
        Nodo actual = cabeza;
        Nodo sig = null;

        while (actual != null) {
            sig = actual.siguiente;      // Paso 2: guardar el siguiente
            actual.siguiente = prev;     // Paso 3: invertir la flecha
            prev = actual;               // Paso 4: avanzar punteros
            actual = sig;
        }
        cabeza = prev; // la nueva cabeza es el último nodo original
    }

    /* ==========================================
       RETO 3: Devolver el Nodo N desde el Final
       Se resuelve sin conocer el tamaño total.
       ========================================== */
    public Nodo nodoDesdeElFinal(int n) {
        // Paso 1: dos punteros (rápido y lento) en la cabeza
        Nodo rapido = cabeza;
        Nodo lento = cabeza;

        // Paso 2: avanzar el rápido N pasos primero
        for (int i = 0; i < n; i++) {
            if (rapido == null) return null; // n es mayor que el tamaño
            rapido = rapido.siguiente;
        }

        // Paso 3: mover ambos hasta que el rápido sea null
        while (rapido != null) {
            rapido = rapido.siguiente;
            lento = lento.siguiente;
        }
        return lento; // el lento es la respuesta
    }

    /* ==========================================
       RETO 4: Eliminar Duplicados (Lista Ordenada)
       1->1->2->3->3 se vuelve 1->2->3.
       ========================================== */
    public void eliminarDuplicados() {
        // Paso 1: empezar en la cabeza
        Nodo actual = cabeza;

        while (actual != null && actual.siguiente != null) {
            if (actual.dato == actual.siguiente.dato) {
                // Paso 2: saltar el nodo duplicado
                actual.siguiente = actual.siguiente.siguiente;
            } else {
                // Paso 3: si no hay duplicado, avanzar
                actual = actual.siguiente;
            }
        }
    }

    /* Método auxiliar para imprimir la lista */
    public void imprimir() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }
}