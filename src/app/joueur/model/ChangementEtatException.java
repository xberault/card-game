package app.joueur.model;

public class ChangementEtatException extends Exception {
    public ChangementEtatException(String msgErreur) {
        super(msgErreur);
    }
}
