import java.util.Random;

/**
 * @author joan on 24/4/17.
 * @project Connecta4
 * @package src
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
        Double millorHeuristica = InfinitNegatiu;
        int millorColumna = 0;
        for (int i = 0; i < t.getMida(); i++) {
            if (t.movpossible(i)) {
                Tauler seguentTauler = new Tauler(t);
                seguentTauler.afegeix(i, color);
                Double heuristicaColumna = -alfaBeta(seguentTauler, oponent(color), i, InfinitNegatiu, InfinitPositiu, profunditat);
                System.out.println(heuristicaColumna);
                if (heuristicaColumna > millorHeuristica) {
                    millorColumna = i;
                    millorHeuristica = heuristicaColumna;
                }
            }
        }
        System.out.println();
        return millorColumna;
    }

    /** Privats */
    private Double alfaBeta(Tauler t, int jugador, int columnaAColocar, Double alpha, Double beta, int profunditat) {
        Boolean solucioTrobada  = t.solucio(columnaAColocar, jugador);
        Boolean profunditatFeta = profunditat != 0;
        Boolean taulerNoComplet = t.espotmoure();

        if (!solucioTrobada && !profunditatFeta && !taulerNoComplet) {
            for (int i = 0; i < t.getMida(); i++) {
                Tauler taulerOponent = new Tauler(t);
                if (taulerOponent.movpossible(i)) {
                    taulerOponent.afegeix(i, jugador);
                    alpha = Math.max(alpha, -this.alfaBeta(t, oponent(jugador), i, -beta, -alpha, profunditat-1));
                    if (beta <= alpha) {
                        return alpha;
                    }
                }
            }
        } else {
            alpha = heuristica(t, jugador);
        }

        return alpha;
    }

    private int oponent(int jugador) {
        return -jugador;
    }

    private Double heuristica(Tauler t, int jugador) {
        Double base = 128.0;
        Double suma = 0.0;
        int mida = t.getMida();
        for (int i = 0; i < mida; i++) {
            for (int j = 0; j < mida; j++) {
                if (t.getColor(i, j) == 0) continue;
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
