import java.util.*;

public class SecondBiggest {

    /**
     * Encuentra el segundo número más grande en una lista de enteros.
     * Si la lista tiene menos de dos elementos o si no hay un segundo número más grande único, lanza una excepción.
     * 
     * @param numbers Lista de enteros.
     * @return El segundo número más grande.
     * @throws IllegalArgumentException si la lista tiene menos de dos elementos o no hay un segundo número más grande único.
     */
    public static Integer findSecondBiggest(List<Integer> numbers) {
        if (numbers == null || numbers.size() < 2) {
            throw new IllegalArgumentException("La lista debe tener al menos dos elementos.");
        }

        Integer max = null;
        Integer secondMax = null;

        for (Integer num : numbers) {
            if (max == null || num > max) {
                secondMax = max;
                max = num;
            } else if ((secondMax == null || num > secondMax) && !num.equals(max)) {
                secondMax = num;
            }
        }

        if (secondMax == null) {
            throw new IllegalArgumentException("No se puede determinar un segundo número más grande único.");
        }

        return secondMax;
    }

    public static void main(String[] args) {
        List<List<Integer>> pruebas = Arrays.asList(
            Arrays.asList(4, 1, 7, 3, 9), 
            Arrays.asList(10, 20, 20, 10), 
            Arrays.asList(1, 2),
            Arrays.asList(5, 5, 5),
            Arrays.asList(99, 100, 98, 97)
        );

        for (List<Integer> prueba : pruebas) {
            try {
                System.out.println("Entrada: " + prueba + " -> Segundo mayor: " + findSecondBiggest(prueba));
            } catch (IllegalArgumentException e) {
                System.out.println("Entrada: " + prueba + " -> Error: " + e.getMessage());
            }
        }
    }
}
