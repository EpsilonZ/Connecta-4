/**
 * @author joan on 24/4/17.
 * @project Connecta4
 * @package PACKAGE_NAME
 */
public class Player implements Jugador {

    private static int[][] evaluacions = {
            {3,4,5,7,7,5,4,3},
            {4,6,8,10,10,8,6,4},
            {5,8,11,13,13,11,8,5},
            {6,10,14,16,16,14,10,6},
            {6,10,14,16,16,14,10,6},
            {5,8,11,13,13,11,8,5},
            {4,6,8,10,10,8,6,4},
            {3,4,5,7,7,5,4,3},
    };


    /** Propietats */
    private Double InfinitPositiu = Double.POSITIVE_INFINITY;
    private Double InfinitNegatiu = -InfinitPositiu;
    private String nom = "El puto amo";
    private int profunditat = 6;


    /** Constructor **/
    public Player() {}

    /** Jugador */

    @Override
    public String nom() {
        return this.nom;
    }

    @Override
    public int moviment(Tauler t, int color) {
        int millorColumna = 0;
        int millorHeuristica = 0;
        for (int i = 0; i < t.getMida(); i++) {
            if (t.movpossible(i)) {
                Tauler seguentTauler = new Tauler(t);
                seguentTauler.afegeix(i, color);
                int heuristicaColumna = alfaBeta(seguentTauler, oponent(color), 0.0, 0.0, profunditat);
                if (heuristicaColumna > millorHeuristica) {
                    millorColumna = i;
                    millorHeuristica = heuristicaColumna;
                }
            }
        }
        return millorColumna;
    }

    private int alfaBeta(int jug, Tauler t, Double alpha, Double beta, int profunditat) {
        return 0;
    }

    private int oponent(int jugador) {
        return -jugador;
    }
    private int heuristica(Tauler t, int jugador) {
        int base = 128;
        int suma = 0;
        int mida = t.getMida();
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                if (t.getColor(i, j) == jugador) {
                    suma += evaluacions[i][j];
                } else {
                    suma -= evaluacions[i][j];
                }
            }
        }
        return base + suma;
    }

}
