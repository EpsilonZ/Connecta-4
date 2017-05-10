import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;

/**
 * @author joan on 24/4/17.
 * @project Connecta4
 * @package src
 */
public class Player implements Jugador {

    /**
     * Propietats
     */
    private Integer InfinitPositiu = Integer.MAX_VALUE;
    private Integer InfinitNegatiu = -InfinitPositiu;
    private String nom = "El puto amo";
    private int profunditat = 5; // ha de ser par, sino empezar sin la 1 llamada sin el negativo

    /**
     * Constructor
     **/
    public Player() {
    }

    /**
     * Jugador
     */

    @Override
    public String nom() {
        return this.nom;
    }

    @Override
    public int moviment(Tauler t, int color) {
        int eval_act, eval_ant = InfinitNegatiu, millor_mov = 0;
        for(int i=0; i<8; ++i){
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

    private int min_value(Tauler t, int alfa, int beta, int nivell, int color){
        if(nivell==0 || es_guanyador(t)[0]==1 || !t.espotmoure()) return evaluarTauler(t, color);
        for(int i=0;i<8;i++){
            if(t.movpossible(i)){
                Tauler aux = new Tauler (t);
                aux.afegeix(i, -color);
                beta = Math.min(beta, max_value(aux,alfa,beta,nivell-1, color));
                if(beta <= alfa) return beta;
            }
        }
        return beta;
    }

    private int max_value(Tauler t, int alfa, int beta, int nivell, int color){
        if(nivell==0 || es_guanyador(t)[0]==1 || !t.espotmoure()) return evaluarTauler(t,color);
        for(int i=0;i<8;i++){
            if(t.movpossible(i)){
                Tauler aux = new Tauler (t);
                aux.afegeix(i, color);
                alfa = Math.max(alfa, min_value(aux, alfa, beta, nivell-1, color));
                if(alfa >= beta) return alfa;
            }
        }
        return alfa;
    }



    private float alphaBeta(Tauler t, int color, int prof, float alfa, float beta) {
        if (prof == 0 || !t.espotmoure() || es_guanyador(t)[0] == 1){
            return evaluarTauler(t, color);
        }
        for (int i = 0; i < t.getMida(); ++i) {
            if (t.movpossible(i)) {
                Tauler aux = new Tauler(t);
                aux.afegeix(i, color);
                alfa = Math.max(alfa, -alphaBeta(aux, oponent(color), prof - 1, -beta, -alfa));
                if (beta <= alfa) {
                    return alfa;
                }
            } else {
                alfa = evaluarTauler(t, color);
            }
        }
        return alfa;
    }

    private int oponent(int jugador) {
        return -jugador;
    }

    private int evaluarTauler(Tauler t, int jugador) {
        int[] guanyador = es_guanyador(t);
        if (guanyador[0] == 1) return (guanyador[1] * InfinitPositiu);

        int aiScore = 1;
        int score = 0;
        int blanks = 0;
        int k;
        int moreMoves = 0;
        for (int i = t.getMida()-1; i >= 0; i--) {
            for (int j = 0; j < t.getMida(); j++) {

                if (t.getColor(i, j) == 0 || t.getColor(i, j) == jugador) continue;
                if (j <= 3) {
                    for (k = 1; k < 4; ++k) {
                        if (t.getColor(i, j+k) == oponent(jugador)) aiScore++;
                        else if(t.getColor(i, j+k) == jugador) {
                            aiScore = 0;
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

                    if (moreMoves != 0) score += calculateScore(aiScore, moreMoves);
                    aiScore = 1;
                    blanks = 0;
                }

                if (i >= 3) {
                    for (k = 1; k < 4; ++k) {
                        if (t.getColor(i - k, j) == oponent(jugador)) aiScore++;
                        else if (t.getColor(i - k, j) == jugador) {
                            aiScore = 0;
                            break;
                        }
                    }
                    moreMoves = 0;

                    if (aiScore > 0) {
                        int column = j;
                        for (int m = i-k+1; m <= i-1 ; m++) {
                            if (t.getColor(m, column) == 0) moreMoves++;
                            else break;
                        }
                    }
                }
                if (moreMoves != 0) score += calculateScore(aiScore, moreMoves);
                aiScore = 1;
                blanks = 0;
                if (j >= 3) {
                    for (k = 1; k < 4; ++k) {
                        if (t.getColor(i, j-k) == oponent(jugador)) aiScore++;
                        else if (t.getColor(i, j - k) == jugador) {
                            aiScore = 0;
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
                    if (moreMoves != 0) score += calculateScore(aiScore, moreMoves);
                    aiScore = 1;
                    blanks = 0;
                }

                if (j <= 3 && i >= 3) {
                    for (k = 1; k < 4; ++k) {
                        if (t.getColor(i-k, j+k) == oponent(jugador)) aiScore++;
                        else if (t.getColor(i-k, j+k) == jugador) {
                            aiScore = 0;
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
                        if (moreMoves != 0) score += calculateScore(aiScore, moreMoves);
                        aiScore = 1;
                        blanks = 0;
                    }
                }

                if (i >= 3 && j >= 3) {
                    for (k = 1; k < 4; ++k) {
                        if (t.getColor(i-k, j-k) == oponent(jugador)) aiScore++;
                        else if (t.getColor(i-k, j-k) == jugador) {
                            aiScore = 0;
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
                        if (moreMoves != 0) score += calculateScore(aiScore, moreMoves);
                        aiScore = 1;
                        blanks = 0;
                    }
                }
            }
        }
        return score;
    }

    int calculateScore(int aiScore, int moreMoves){
        int moveScore = 4 - moreMoves;
        if(aiScore==0) return 0;
        else if(aiScore==1) return 1*moveScore;
        else if(aiScore==2) return 10*moveScore;
        else if(aiScore==3) return 100*moveScore;
        else return 1000;
    }


    /*private boolean es_guanyador(Tauler t){

        for (int j = 0; j<t.getMida()-3 ; j++ ){
            for (int i = 0; i<t.getMida(); i++){
                if (t.getColor(i,j) == 1 && t.getColor(i,j+1) == 1 && t.getColor(i,j+2) == 1 && t.getColor(i,j+3) == 1){
                    return true;
                }
                else if (t.getColor(i,j) == -1 && t.getColor(i,j+1) == -1 && t.getColor(i,j+2) == -1 && t.getColor(i,j+3) == -1){
                    return true;
                }
            }
        }
        for (int i = 0; i<t.getMida()-3 ; i++ ){
            for (int j = 0; j<t.getMida(); j++){
                if (t.getColor(i,j) == 1 && t.getColor(i+1,j) == 1 && t.getColor(i+2,j) == 1 && t.getColor(i+3,j) == 1){
                    return true;
                }
                else if (t.getColor(i,j) == -1 && t.getColor(i+1,j) == -1 && t.getColor(i+2,j) == -1 && t.getColor(i+3,j) == -1){
                    return true;
                }
            }
        }
        for (int i=3; i<t.getMida(); i++){
            for (int j=0; j<t.getMida()-3; j++){
                if (t.getColor(i,j) == 1 && t.getColor(i-1,j+1) == 1 && t.getColor(i-2,j+2) == 1 && t.getColor(i-3, j+3) == 1) {
                    return true;
                }
                else if (t.getColor(i,j) == -1 && t.getColor(i-1,j+1) == -1 && t.getColor(i-2,j+2) == -1 && t.getColor(i-3, j+3) == -1) {
                    return true;
                }

            }
        }
        for (int i=3; i<t.getMida(); i++){
            for (int j=3; j<t.getMida(); j++) {
                if (t.getColor(i, j) == 1 && t.getColor(i - 1, j - 1) == 1 && t.getColor(i - 2, j - 2) == 1 && t.getColor(i - 3, j - 3)==1){
                    return true;
                }
                else if (t.getColor(i, j) == -1 && t.getColor(i - 1, j - 1) == -1 && t.getColor(i - 2, j - 2) == -1 && t.getColor(i - 3, j - 3) == -1){
                    return true;
                }
            }
        }
        return false;
    }*/
    private int[] es_guanyador(Tauler t){
        int[] res = {0,0};
        boolean win=false;
        int act=0;
        //Comprova lineas Horitzontals
        for(int fil=0;fil<8 && !win;fil++){
            int cont=1, ant=0;
            for(int col=0; col<8 && !win;col++){
                act = t.getColor(fil, col);
                if(ant==act && act != 0) {
                    cont++;
                    if(cont==4) win=true;
                }
                else cont=1;
                ant=act;
            }
        }
        //Comprova lineas Verticals
        for(int col=0;col<8 && !win;col++){
            int cont=1, ant=0;
            for(int fil=0; fil<8 && !win;fil++){
                act = t.getColor(fil, col);
                if(ant==act && act != 0) {
                    cont++;
                    if(cont==4) win=true;
                }
                else cont=1;
                ant=act;
            }
        }
        //Compara diagonals amunt
        for(int fil=0;fil<5 && !win;fil++){
            int cont=1, ant=0;
            for(int col=0; col<(8-fil) && !win;col++){
                act = t.getColor(fil+col, col);
                if(ant==act && act != 0) {
                    cont++;
                    if(cont==4) win=true;
                }
                else cont=1;
                ant=act;
            }
        }
        for(int col=1;col<5 && !win;col++){
            int cont=1, ant=0;
            for(int fil=0; fil<(8-col) && !win;fil++){
                act = t.getColor(fil, col+fil);
                if(ant==act && act != 0) {
                    cont++;
                    if(cont==4) win=true;
                }
                else cont=1;
                ant=act;
            }
        }
        //Compara diagonals aball
        for(int fil=7;fil>2 && !win;fil--){
            int cont=1, ant=0;
            for(int col=0; col<=fil && !win;col++){
                act = t.getColor(fil-col, col);
                if(ant==act && act != 0) {
                    cont++;
                    if(cont==4) win=true;
                }
                else cont=1;
                ant=act;
            }
        }
        for(int col=4;col>0 && !win;col--){
            int cont=1, ant=0, n=0;
            for(int fil=7; fil>=col && !win;fil--){
                act = t.getColor(fil,n+col);
                n++;
                if(ant==act && act != 0) {
                    cont++;
                    if(cont==4) win=true;
                }
                else cont=1;
                ant=act;
            }
        }
        if(win==true){
            res[0]=1;
            res[1] = act;
        }
        return res;
    }

}