package app.model;

public class ActionNonJouableException extends Exception {
    public ActionNonJouableException() {
        super("L'action effectuée n'est pas réalisable pour le joueur");
    }
}
