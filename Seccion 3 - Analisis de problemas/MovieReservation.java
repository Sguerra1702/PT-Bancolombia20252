public class MovieReservation {

    /**
     * Verifica si es posible realizar una reserva en la matriz de asientos.
     * Una reserva es posible si hay al menos dos asientos consecutivos disponibles
     * (representados por dos ceros consecutivos en una fila).
     *
     * @param asientos Una matriz bidimensional que representa los asientos del cine.
     *                 Cada fila de la matriz representa una fila de asientos.
     * @return {@code true} si es posible realizar una reserva (hay dos asientos
     *         consecutivos disponibles), de lo contrario {@code false}.
     */
    public static boolean canMakeReservation(int[][] asientos) {
        for (int[] fila : asientos) {
            for (int i = 0; i < fila.length - 1; i++) {
                if (fila[i] == 0 && fila[i + 1] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matriz1 = {
            {1, 0, 0, 1},
            {0, 1, 1, 0},
            {0, 0, 0, 0}
        };

        int[][] matriz2 = {
            {1, 1, 1},
            {0, 1, 0},
            {1, 0, 1}
        };

        System.out.println("Resultado matriz 1: " + canMakeReservation(matriz1)); // true
        System.out.println("Resultado matriz 2: " + canMakeReservation(matriz2)); // false
    }
}
