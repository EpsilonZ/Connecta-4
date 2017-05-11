/**
 * @author joan on 24/4/17.
 * @project Connecta4
 * @package src
 */
public class Player implements Jugador {
    private class Resultat {
        public boolean esTaulerGuanyador;
        public int jugadorGuanyador;
        public int valorTauler = 0;

        public Resultat(boolean esTaulerGuanyador, int jugadorGuanyador) {
            this.esTaulerGuanyador = esTaulerGuanyador;
            this.jugadorGuanyador = jugadorGuanyador;
        }

        public Resultat(boolean esTaulerGuanyador, int jugadorGuanyador, int valorTauler) {
            this.esTaulerGuanyador = esTaulerGuanyador;
            this.jugadorGuanyador = jugadorGuanyador;
            this.valorTauler = valorTauler;
        }

        @Override
        public String toString() {
            return "Resultat{" +
                    "esTaulerGuanyador=" + esTaulerGuanyador +
                    ", jugadorGuanyador=" + jugadorGuanyador +
                    '}';
        }
    }

    /**
     * Propietats
     */
    private Integer InfinitPositiu = Integer.MAX_VALUE;
    private Integer InfinitNegatiu = -InfinitPositiu;
    private String nom = "El puto amo";
    private int profunditat = 6;

    /**
     * Constructor
     **/
    public Player() {
//        testTaulers();
    }

    private void testTaulers() {
        Tauler t = new Tauler(8);
        t.afegeix(0, -1);
        t.afegeix(0, -1);
        t.afegeix(0, -1);
        t.afegeix(0, -1);
        t.afegeix(1, 1);
        t.afegeix(1, 1);
        t.afegeix(2, 1);
        t.afegeix(2, -1);
        t.afegeix(3, -1);
        t.afegeix(3, 1);
        t.afegeix(3, 1);
        t.afegeix(3, 1);
        t.afegeix(3, -1);
        t.pintaTauler();
        System.out.println(evaluarTauler(t, -1));
        /*        Tauler t1 = new Tauler(8);
        t1.afegeix(0, 1);
        t1.afegeix(1, 1);
        t1.afegeix(2, 1);
        t1.afegeix(3, 1);
        t1.pintaTauler();
        System.out.println(tauler_guanyador(t1));

        Tauler t1a = new Tauler(8);
        t1a.afegeix(0, -1);
        t1a.afegeix(1, -1);
        t1a.afegeix(2, 1);
        t1a.afegeix(3, -1);
        t1a.afegeix(4, 1);
        t1a.afegeix(5, 1);
        t1a.afegeix(6, 1);
        t1a.afegeix(7, 1);
        t1a.pintaTauler();
        System.out.println(tauler_guanyador(t1a));

        Tauler t2 = new Tauler(8);
        t2.afegeix(0, 1);
        t2.afegeix(1, -1);
        t2.afegeix(1, 1);
        t2.afegeix(2, -1);
        t2.afegeix(2, 1);
        t2.afegeix(2, 1);
        t2.afegeix(3, -1);
        t2.afegeix(3, -1);
        t2.afegeix(3, -1);
        t2.afegeix(3, 1);
        t2.pintaTauler();
        System.out.println(tauler_guanyador(t2));

        Tauler t2a = new Tauler(8);
        t2a.afegeix(0, 1);
        t2a.afegeix(0, 1);
        t2a.afegeix(0, -1);
        t2a.afegeix(0, -1);
        t2a.afegeix(0, 1);

        t2a.afegeix(1, 1);
        t2a.afegeix(1, 1);
        t2a.afegeix(1, -1);
        t2a.afegeix(1, -1);
        t2a.afegeix(1, 1);
        t2a.afegeix(1, 1);

        t2a.afegeix(2, -1);
        t2a.afegeix(2, -1);
        t2a.afegeix(2, 1);
        t2a.afegeix(2, 1);
        t2a.afegeix(2, -1);
        t2a.afegeix(2, -1);
        t2a.afegeix(2, 1);

        t2a.afegeix(3, 1);
        t2a.afegeix(3, 1);
        t2a.afegeix(3, -1);
        t2a.afegeix(3, -1);
        t2a.afegeix(3, 1);
        t2a.afegeix(3, 1);
        t2a.afegeix(3, 1);
        t2a.afegeix(3, 1);
        t2a.pintaTauler();
        System.out.println(tauler_guanyador(t2a));

        Tauler t2b = new Tauler(8);
        t2b.afegeix(4, 1);
        t2b.afegeix(4, 1);
        t2b.afegeix(4, -1);
        t2b.afegeix(4, -1);
        t2b.afegeix(4, 1);

        t2b.afegeix(5, 1);
        t2b.afegeix(5, 1);
        t2b.afegeix(5, -1);
        t2b.afegeix(5, -1);
        t2b.afegeix(5, 1);
        t2b.afegeix(5, 1);

        t2b.afegeix(6, -1);
        t2b.afegeix(6, -1);
        t2b.afegeix(6, 1);
        t2b.afegeix(6, 1);
        t2b.afegeix(6, -1);
        t2b.afegeix(6, -1);
        t2b.afegeix(6, 1);

        t2b.afegeix(7, 1);
        t2b.afegeix(7, 1);
        t2b.afegeix(7, -1);
        t2b.afegeix(7, -1);
        t2b.afegeix(7, -1);
        t2b.afegeix(7, 1);
        t2b.afegeix(7, 1);
        t2b.afegeix(7, 1);
        t2b.pintaTauler();
        System.out.println(tauler_guanyador(t2b));

        Tauler tde = new Tauler(8);
        tde.afegeix(7, 1);
        tde.afegeix(7, 1);
        tde.afegeix(7, -1);
        tde.afegeix(7, -1);
        tde.afegeix(7, 1);

        tde.afegeix(6, 1);
        tde.afegeix(6, 1);
        tde.afegeix(6, -1);
        tde.afegeix(6, -1);
        tde.afegeix(6, 1);
        tde.afegeix(6, 1);

        tde.afegeix(5, -1);
        tde.afegeix(5, -1);
        tde.afegeix(5, 1);
        tde.afegeix(5, 1);
        tde.afegeix(5, -1);
        tde.afegeix(5, -1);
        tde.afegeix(5, 1);

        tde.afegeix(4, 1);
        tde.afegeix(4, 1);
        tde.afegeix(4, -1);
        tde.afegeix(4, -1);
        tde.afegeix(4, -1);
        tde.afegeix(4, 1);
        tde.afegeix(4, 1);
        tde.afegeix(4, 1);
        tde.pintaTauler();
        System.out.println(tauler_guanyador(tde));*/

/*        Tauler t3 = new Tauler(8);
        t3.afegeix(0, -1);
        t3.afegeix(0, -1);
        t3.afegeix(0, 1);
        t3.afegeix(0, -1);
        t3.afegeix(0, 1);
        t3.afegeix(0, 1);
        t3.afegeix(0, 1);
        t3.afegeix(0, 1);
        t3.pintaTauler();
        System.out.println(tauler_guanyador(t3));*/





    }
    /**
     * @return
     */
    @Override
    public String nom() {
        return this.nom;
    }

    /**
     * @param t     Tauler actual de joc
     * @param color Color de la peça que possarà
     * @return
     */
    @Override
    public int moviment(Tauler t, int color) {
        int eval_act, eval_ant = InfinitNegatiu, millor_mov = 0;
        for(int i=0; i < t.getMida(); ++i){
            if(t.movpossible(i)){
                Tauler aux = new Tauler(t);
                aux.afegeix(i, color);
                eval_act = min_value(aux, InfinitNegatiu, InfinitPositiu, profunditat, color);
                System.out.println("accio -> "+i+" valor accio = "+eval_act);
                if(eval_act > eval_ant){
                    millor_mov = i;
                    eval_ant = eval_act;
                }
            }
        }
        System.out.println();
        return millor_mov;
    }

    /**
     * @param t
     * @param alfa
     * @param beta
     * @param nivell
     * @param color
     * @return
     */
    private int min_value(Tauler t, int alfa, int beta, int nivell, int color){
        if(nivell==0 || tauler_guanyador(t).esTaulerGuanyador || !t.espotmoure()) return evaluarTauler(t, color);
        for(int i=0;i<t.getMida();i++){
            if(t.movpossible(i)){
                Tauler aux = new Tauler (t);
                aux.afegeix(i, -color);
                beta = Math.min(beta, max_value(aux,alfa,beta,nivell-1, color));
                if(beta <= alfa) return beta;
            }
        }
        return beta;
    }

    /**
     * @param t
     * @param alfa
     * @param beta
     * @param nivell
     * @param color
     * @return
     */
    private int max_value(Tauler t, int alfa, int beta, int nivell, int color){
        if(nivell==0 || tauler_guanyador(t).esTaulerGuanyador || !t.espotmoure()) return evaluarTauler(t,color);
        for(int i=0;i<t.getMida();i++){
            if(t.movpossible(i)){
                Tauler aux = new Tauler (t);
                aux.afegeix(i, color);
                alfa = Math.max(alfa, min_value(aux, alfa, beta, nivell-1, color));
                if(alfa >= beta) return alfa;
            }
        }
        return alfa;
    }

    /**
     * @param jugador
     * @return
     */
    private int oponent(int jugador) {
        return -jugador;
    }

    /**
     * @param t
     * @param fil
     * @param col
     * @return
     */
    private Resultat casellaGuanyadora(Tauler t, int fil, int col) {
        int maximMenor = -1;
        int maximMajor = t.getMida();
        int jugador = t.getColor(fil, col);
        int pecesPerGuanyar = 4;
        int j;
        int contador;

        //Mirar adalt -> Tests OK
        contador = 1;
        for (int i = fil+1; i < maximMajor && i <= fil+3; i++) {
            if (t.getColor(i, col) == jugador) contador++;
        }

        if (contador == pecesPerGuanyar) {
            return new Resultat(true, jugador, jugador * InfinitPositiu);
        }

        //Mirar diagonal adalt dreta -> Tests OK
        contador = 1;
        j = col+1;
        for (int i = fil+1; i < maximMajor && j < maximMajor && i <= fil+3; i++) {
            if (t.getColor(i, j++) == jugador) contador++;
        }

        if (contador == pecesPerGuanyar) {
            return new Resultat(true, jugador, jugador * InfinitPositiu);
        }

        //Mirar dreta -> Tests OK
        contador = 1;
        for (int i = col+1; i < maximMajor && i <= col+3; i++) {
            if (t.getColor(fil, i) == jugador) contador++;
        }

        if (contador == pecesPerGuanyar) {
            return new Resultat(true, jugador, jugador * InfinitPositiu);
        }

        //Mirar diagonal adalt esquerra -> Tests OK
        contador = 1;
        j = col-1;
        for (int i = fil+1; i < maximMajor && j > maximMenor && i <= fil+3; i++) {
            if (t.getColor(i, j--) == jugador) contador++;
        }

        if (contador == pecesPerGuanyar) {
            return new Resultat(true, jugador, jugador * InfinitPositiu);
        }
        return new Resultat(false, 0);
    }

    /**
     * @param t
     * @return
     */
    private Resultat tauler_guanyador(Tauler t) {
        Resultat resultat = new Resultat(false, 0);
        for (int i = 0; i < t.getMida(); i++) {
            for (int j = 0; j < t.getMida(); j++) {
                if (t.getColor(i, j) == 0) continue;
                resultat = casellaGuanyadora(t, i, j);
                if (resultat.esTaulerGuanyador) return resultat;
            }
        }

        return resultat;
    }

    /**
     * @param t
     * @param jugador
     * @return
     */
    private int evaluarTauler(Tauler t, int jugador) {
        Resultat r = tauler_guanyador(t);
        boolean guanyador = r.esTaulerGuanyador;

        if (guanyador) {
            return r.valorTauler;
        }

        int puntuacioOponent = 1;
        int score = 0;
        int blanks = 0;
        int k;
        int moreMoves = 0;
        for (int i = t.getMida()-1; i >= 0; i--) {
            for (int j = 0; j < t.getMida(); j++) {

                if (t.getColor(i, j) == 0 || t.getColor(i, j) == jugador) continue;

                if (j <= 3) {
                    for (k = 1; k < 4; ++k) {
                        if (t.getColor(i, j+k) == oponent(jugador)) puntuacioOponent++;
                        else if(t.getColor(i, j+k) == jugador) {
                            puntuacioOponent = 0;
                            blanks = 0;
                            break;
                        } else blanks++;
                    }

                    moreMoves = 0;
                    if (blanks > 0) {
                        for (int c = 1; c < 4; ++c) {
                            int column = j+c;
                            for (int m = i; m <= 5; m++) {
                                if (t.getColor(m, column) == 0) moreMoves++;
                                else break;
                            }
                        }
                    }

                    if (moreMoves != 0) score += calcularPuntuacio(puntuacioOponent, moreMoves);
                    puntuacioOponent = 1;
                    blanks = 0;
                }

                if (i >= 3) {
                    for (k = 1; k < 4; ++k) {
                        if (t.getColor(i - k, j) == oponent(jugador)) puntuacioOponent++;
                        else if (t.getColor(i - k, j) == jugador) {
                            puntuacioOponent = 0;
                            break;
                        }
                    }
                    moreMoves = 0;

                    if (puntuacioOponent > 0) {
                        int column = j;
                        for (int m = i-k+1; m <= i-1 ; m++) {
                            if (t.getColor(m, column) == 0) moreMoves++;
                            else break;
                        }
                    }
                }
                if (moreMoves != 0) score += calcularPuntuacio(puntuacioOponent, moreMoves);
                puntuacioOponent = 1;
                blanks = 0;
                if (j >= 3) {
                    for (k = 1; k < 4; ++k) {
                        if (t.getColor(i, j-k) == oponent(jugador)) puntuacioOponent++;
                        else if (t.getColor(i, j - k) == jugador) {
                            puntuacioOponent = 0;
                            blanks = 0;
                            break;
                        } else blanks++;
                    }
                    moreMoves = 0;
                    if (blanks > 0) {
                        for (int c = 1; c < 4; c++) {
                            int column = j - c;
                            for (int m = i; m <= 5; m++) {
                                if (t.getColor(m, column) == 0) moreMoves++;
                                else break;
                            }
                        }
                    }
                    if (moreMoves != 0) score += calcularPuntuacio(puntuacioOponent, moreMoves);
                    puntuacioOponent = 1;
                    blanks = 0;
                }

                if (j <= 3 && i >= 3) {
                    for (k = 1; k < 4; ++k) {
                        if (t.getColor(i-k, j+k) == oponent(jugador)) puntuacioOponent++;
                        else if (t.getColor(i-k, j+k) == jugador) {
                            puntuacioOponent = 0;
                            blanks = 0;
                            break;
                        } else blanks++;
                    }
                    moreMoves = 0;
                    if (blanks > 0) {
                        for (int c = 1; c < 4; ++c) {
                            int column = j+c, row = i-c;
                            for (int m = row; m <= 5; ++m) {
                                if (t.getColor(m, column) == 0) moreMoves++;
                                else if(t.getColor(m, column) == -1);
                                else break;
                            }
                        }
                        if (moreMoves != 0) score += calcularPuntuacio(puntuacioOponent, moreMoves);
                        puntuacioOponent = 1;
                        blanks = 0;
                    }
                }

                if (i >= 3 && j >= 3) {
                    for (k = 1; k < 4; ++k) {
                        if (t.getColor(i-k, j-k) == oponent(jugador)) puntuacioOponent++;
                        else if (t.getColor(i-k, j-k) == jugador) {
                            puntuacioOponent = 0;
                            blanks = 0;
                            break;
                        } else blanks++;
                    }

                    moreMoves = 0;
                    if (blanks > 0) {
                        for (int c = 1; c < 4; ++c) {
                            int column = j-c, row = i-c;
                            for (int m = row; m <= 5 ; ++m) {
                                if (t.getColor(m, column) == 0) moreMoves++;
                                else if(t.getColor(m, column) == oponent(jugador));
                                else  break;
                            }
                        }
                        if (moreMoves != 0) score += calcularPuntuacio(puntuacioOponent, moreMoves);
                        puntuacioOponent = 1;
                        blanks = 0;
                    }
                }
            }
        }
        return score;
    }

    private int evaluarTaulerWIP(Tauler t, int jugador) {
        Resultat r = tauler_guanyador(t);
        boolean guanyador = r.esTaulerGuanyador;
        if (guanyador) return r.jugadorGuanyador * InfinitPositiu;

        for (int i = 0; i < t.getMida(); i++) {
            for (int j = 0; j < t.getMida() ; j++) {
                if (t.getColor(i, j) == 0) continue;

            }
        }
        return 0;
    }
    /**
     * @param puntuacio
     * @param moviments
     * @return
     */
    int calcularPuntuacio(int puntuacio, int moviments){
        int puntuacioMoviments = 4 - moviments;
        if(puntuacio==0) return 0;
        else if(puntuacio==1) return 1*puntuacioMoviments;
        else if(puntuacio==2) return 10*puntuacioMoviments;
        else if(puntuacio==3) return 100*puntuacioMoviments;
        else return 1000;
    }
}