/**
 * @author joan on 24/4/17.
 * @project Connecta4
 * @package PACKAGE_NAME
 */
public class Player implements Jugador {

    /** Propietats */
    private String nom = "El puto amo";

    /** Constructor **/
    public Player() {

    }
    /** Jugador */
    @Override
    public int moviment(Tauler t, int color) {
        return 0;
    }

    @Override
    public String nom() {
        return this.nom;
    }

    /** Privats */
}
