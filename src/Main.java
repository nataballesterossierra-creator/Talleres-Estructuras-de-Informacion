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
        for(int i=0; i<arreglo.length; i++){
            System.out.print(arreglo[i]+" ");
        }
        System.out.println();

         int[]arreglocon0 ={0,1,0,3,12};
         move0end(arreglocon0);
        System.out.println("Arreglo con ceros movidos al final: ");

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

    /*mover ceros al final (0,10,0,3,12) -> (1,3,12,0,0*)*/
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
}