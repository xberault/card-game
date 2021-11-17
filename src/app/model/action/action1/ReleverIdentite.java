package app.model.action.action1;

import app.joueur.model.JoueurModel;
import app.model.action.Action1;

public class ReleverIdentite extends Action1 {

    public ReleverIdentite(JoueurModel joueur) {
        super(joueur, "Reveler son identité");
    }

    @Override
    public void executerAction() {
        this.joueur.seRevele();
    }
}
