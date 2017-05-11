/**
 * @author joan on 24/4/17.
 * @project Connecta4
 * @package src
 */
public class Player implements Jugador {
    private class Resultat {
        public boolean esGuanyador;
        public int jugadorGuanyador;
        public int valor = 0;

        public Resultat(boolean esTaulerGuanyador, int jugadorGuanyador, int valorTauler) {
            this.esGuanyador = esTaulerGuanyador;
            this.jugadorGuanyador = jugadorGuanyador;
            this.valor = valorTauler;
        }

        @Override
        public String toString() {
            return "Resultat{" +
                    "esGuanyador=" + esGuanyador +
                    ", jugadorGuanyador=" + jugadorGuanyador +
                    '}';
        }
    }

    /**
     * Propietats
     */
    private Integer InfinitPositiu = Integer.MAX_VALUE;
    private Integer InfinitNegatiu = -InfinitPositiu;
    private String nom = "The boss";
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
        int valorActual, millorValor = InfinitNegatiu, millor_columna = 0;
        for(int i=0; i < t.getMida(); i++){
            if(t.movpossible(i)){
                Tauler aux = new Tauler(t);
                aux.afegeix(i, color);
                valorActual = minimitzar(aux, InfinitNegatiu, InfinitPositiu, profunditat, color);
                System.out.println("accio -> "+ i +" valor accio = "+valorActual);
                if(valorActual > millorValor){
                    millor_columna = i;
                    millorValor = valorActual;
                }
            }
        }
        System.out.println();
        return millor_columna;
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
     * @param alfa
     * @param beta
     * @param profunditat
     * @param jugador
     * @return
     */
    private int minimitzar(Tauler t, int alfa, int beta, int profunditat, int jugador){
        if(profunditat == 0 || esTaulerGuanyador(t).esGuanyador || !t.espotmoure()) return evaluarTauler(t, jugador);
        for(int i = 0; i < t.getMida(); i++){
            if(t.movpossible(i)){
                Tauler aux = new Tauler(t);
                aux.afegeix(i, oponent(jugador));
                beta = Math.min(beta, maximitzar(aux, alfa, beta, profunditat-1, jugador));
                if(beta <= alfa) return beta;
            }
        }
        return beta;
    }

    /**
     * @param t
     * @param alfa
     * @param beta
     * @param profunditat
     * @param jugador
     * @return
     */
    private int maximitzar(Tauler t, int alfa, int beta, int profunditat, int jugador){
        if(profunditat == 0 || esTaulerGuanyador(t).esGuanyador || !t.espotmoure()) return evaluarTauler(t,jugador);
        for(int i = 0 ;i < t.getMida(); i++){
            if(t.movpossible(i)){
                Tauler aux = new Tauler (t);
                aux.afegeix(i, jugador);
                alfa = Math.max(alfa, minimitzar(aux, alfa, beta, profunditat-1, jugador));
                if(alfa >= beta) return alfa;
            }
        }
        return alfa;
    }

    /**
     * @param t
     * @return
     */
    private Resultat esTaulerGuanyador(Tauler t) {
        Resultat resultat = new Resultat(false, 0, 0);

        for (int i = 0; i < t.getMida(); i++) {
            for (int j = 0; j < t.getMida(); j++) {
                if (t.getColor(i, j) == 0) continue;
                resultat = evaluarCasella(t, i, j);
                if (resultat.esGuanyador) return resultat;
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
        Resultat r = esTaulerGuanyador(t);
        boolean guanyador = r.esGuanyador;
        if (guanyador) return r.jugadorGuanyador * InfinitPositiu;

        int heuristica = 0;
        for (int i = 0; i < t.getMida(); i++) {
            for (int j = 0; j < t.getMida(); j++) {
                heuristica += puntuarCasella(t, i, j, jugador).valor;
            }
        }

        return heuristica;
    }

    /**
     * @param t
     * @param fil
     * @param col
     * @return
     */
    private Resultat evaluarCasella(Tauler t, int fil, int col) {
        int pecesPerGuanyar = 4;
        int maximMenor = -1;
        int maximMajor = t.getMida();

        int jugador = t.getColor(fil, col);
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
        return new Resultat(false, 0, 0);
    }

    /**
     * @param t
     * @param fil
     * @param col
     * @param jug
     * @return
     */
    private Resultat puntuarCasella(Tauler t, int fil, int col, int jug) {
        int maximMenor = -1;
        int maximMajor = t.getMida();

        int jugador = t.getColor(fil, col);
        int j;
        int contador, contadorBlancs, contadorPonderat;
        int heuristicaCasella = 0;

        //Mirar adalt -> Tests OK
        contador = 1;
        contadorBlancs = 0;
        for (int i = fil+1; i < maximMajor && i <= fil+3; i++) {
            if (t.getColor(i, col) == jugador) contador++;
            else if (t.getColor(i, col) == 0) contadorBlancs++;
        }

        heuristicaCasella += jugador == jug ? calcularPuntuacio(contador, contadorBlancs) : -calcularPuntuacio(contador, contadorBlancs);

        //Mirar diagonal adalt dreta -> Tests OK
        contador = 1;
        contadorBlancs = 0;
        j = col+1;
        for (int i = fil+1; i < maximMajor && j < maximMajor && i <= fil+3; i++) {
            if (t.getColor(i, j++) == jugador) contador++;
            else if (t.getColor(i, col) == 0) contadorBlancs++;
        }
        heuristicaCasella += jugador == jug ? calcularPuntuacio(contador, contadorBlancs) : -calcularPuntuacio(contador, contadorBlancs);

        //Mirar dreta -> Tests OK
        contador = 1;
        contadorBlancs = 0;
        for (int i = col+1; i < maximMajor && i <= col+3; i++) {
            if (t.getColor(fil, i) == jugador) contador++;
            else if (t.getColor(i, col) == 0) contadorBlancs++;
        }

        heuristicaCasella += jugador == jug ? calcularPuntuacio(contador, contadorBlancs) : -calcularPuntuacio(contador, contadorBlancs);

        //Mirar diagonal adalt esquerra -> Tests OK
        contador = 1;
        contadorBlancs = 0;
        j = col-1;
        for (int i = fil+1; i < maximMajor && j > maximMenor && i <= fil+3; i++) {
            if (t.getColor(i, j--) == jugador) contador++;
            else if (t.getColor(i, col) == 0) contadorBlancs++;
        }

        heuristicaCasella += jugador == jug ? calcularPuntuacio(contador, contadorBlancs) : -calcularPuntuacio(contador, contadorBlancs);

        return new Resultat(false, jugador, heuristicaCasella);
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