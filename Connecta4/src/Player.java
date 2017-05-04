/**
 * @author joan on 24/4/17.
 * @project Connecta4
 * @package src
 */
public class Player implements Jugador {
    private class Pair<F, S> {
        public final F first;
        public final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }

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
    public Player() {
        /*Tauler t = new Tauler(8);
        System.out.println(t.solucio(0, 1));
        t.afegeix(0, 1);
        System.out.println(t.solucio(0, 1));
        t.afegeix(0, 1);
        System.out.println(t.solucio(0, 1));
        t.afegeix(0, 1);
        System.out.println(t.solucio(0, 1));
        t.afegeix(0, 1);
        System.out.println(t.solucio(0, 1));*/
    }

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
        if (beta <= alpha) {
            if (jugador == 1) return InfinitPositiu;
            return InfinitNegatiu;
        }

        if (t.solucio(columnaAColocar, jugador) || profunditat == 0 || !t.espotmoure()) {
            return heuristica(t, jugador);
        }

        Double bestValue;

        if (jugador == 1) {
            bestValue = alpha;
            for (int i = 0; i < t.getMida(); i++) {
                if (t.movpossible(i)) {
                    Tauler t1 = new Tauler(t);
                    t1.afegeix(i, jugador);
                    Double foo = alfaBeta(t1, oponent(jugador), i, bestValue, beta, profunditat - 1);
                    bestValue = Math.max(bestValue, foo);
                    if (beta <= bestValue) break;
                }
            }
        } else {
            bestValue = beta;
            for (int i = 0; i < t.getMida(); i++) {
                if (t.movpossible(i)) {
                    Tauler t1 = new Tauler(t);
                    t1.afegeix(i, jugador); 
                    Double foo = alfaBeta(t1, oponent(jugador), i, alpha, bestValue, profunditat - 1);
                    bestValue = Math.min(bestValue, foo);
                    if (bestValue <= alpha) break;
                }
            }
        }

        return bestValue;
    }

    private boolean acabat(Tauler t, int jugador, int columnaAColocar, int profunditat) {
        Boolean solucioTrobada  = t.solucio(columnaAColocar, jugador);
        Boolean profunditatValida = profunditat > 0;
        Boolean taulerComplet = !t.espotmoure();
        return solucioTrobada || !profunditatValida || taulerComplet;
    }


    private int oponent(int jugador) {
        return -jugador;
    }

/*    private Double heuristica(Tauler t, int jugador) {
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
    }*/

    private Double getPuntuacioPosicio (Tauler t, int fila, int columna, int movx, int movy, int jugador){
        Double puntsJugador = 0.0;
        for (int i = 0; i < 4; ++i){
            if (t.getColor(fila, columna) == 0) continue;
            if(t.getColor(fila,columna) == jugador){
                puntsJugador += evaluacions[fila][columna];
            } else {
                puntsJugador -= evaluacions[fila][columna];
            }
            fila += movx;
            columna += movy;
        }
        return puntsJugador;
    }

    private Double heuristica (Tauler t, int jugador){

        Double punts = 0.0;

        for (int fila = 0; fila < t.getMida() - 3; ++fila) {
            for (int columna = 0; columna < t.getMida(); ++columna) {
                Double puntuacio_vertical = getPuntuacioPosicio(t, fila, columna, 1, 0, jugador);
                punts += puntuacio_vertical;
            }
        }

        for (int fila = 0; fila < t.getMida(); ++fila) {
            for (int columna = 0; columna < t.getMida() - 3; ++columna) {
                Double puntuacio_horitzontal = getPuntuacioPosicio(t, fila, columna, 0, 1, jugador);
                punts += puntuacio_horitzontal;
            }
        }

        for (int fila = 0; fila < t.getMida() - 3; ++fila) {
            for (int columna = 0; columna < t.getMida() - 3; ++columna) {
                Double puntuacio_diagonal_izq = getPuntuacioPosicio(t, fila, columna, 1, 1,jugador);
                punts += puntuacio_diagonal_izq;
            }
        }
        for (int fila = 3; fila < t.getMida(); ++fila) {
            for (int columna = 0; columna <= t.getMida() - 4; columna++) {
                Double puntuacio_diagonal_dreta = getPuntuacioPosicio(t, fila, columna, -1, 1, jugador);
                punts += puntuacio_diagonal_dreta;
            }
        }
        return punts;
    }
}
