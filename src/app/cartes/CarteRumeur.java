package app.cartes;

import app.Jeu;
import app.cartes.condition.Condition;
import app.cartes.models.*;
import app.joueur.JoueurControlleur;
import app.model.IColorable;

import java.util.Objects;
import java.util.Set;

public abstract class CarteRumeur implements IColorable {

    /**
     * Joueur qui possède la carte
     */
    protected JoueurControlleur joueur;

    /**
     * Texte qui permet de décrire les effets witch et hunt de la carte
     */
    private final String descriptionWitch;
    private final String descriptionHunt;
    /**
     * Booléen qui indique si la carte a déjà été jouéé
     */
    private boolean estJouee;
    /**
     * Nom de la carte
     */
    protected String nom;
    /**
     * Conditions à remplir pour activer les effets witch/hunt de la carte
     * <p>
     * La variable est à null lorsque nous n'avons aucune condition sur l'activation des effets
     */
    private Condition conditionHunt, conditionWitch;

    protected CarteRumeur(String nom, String descriptionHunt, String descriptionWitch) {
        this.descriptionHunt = descriptionHunt;
        this.descriptionWitch = descriptionWitch;
        this.estJouee = false;
        this.nom = nom;
    }

    protected void ajouterConditionHunt(Condition condition) {
        this.conditionHunt = condition;
    }

    protected void ajouterConditionWitch(Condition condition) {
        this.conditionWitch = condition;
    }

    /**
     * Permet d'obtenir toutes les cartes obtenables dans le jeu
     *
     * @return un ensemble contenant toutes ces cartes
     */
    public static Set<CarteRumeur> obtenirToutesLesCartes() {
        return Set.of(
                new AngryMob(),
                new BlackCat(),
                new Broomstick(),
                new Cauldron(),
                new DuckingStool(),
                new EvilEye(),
                new HookedNose(),
                new PetNewt(),
                new PointedHat(),
                new TheInquisition(),
                new Toad(),
                new Wart()
        );
    }

    public void setJoueur(JoueurControlleur joueur) {
        this.joueur = joueur;
    }

    /**
     * Indique si l'effet Witch est jouable pour l'utilisateur actuel
     * Certaines cartes ont des conditions pour l'activer
     *
     * @return un booléen qui indique si l'effet est jouable
     */
    public boolean effetWitchJouable() {
        if (Objects.nonNull(this.conditionWitch))
            return this.conditionWitch.estActivable(this.joueur.getModel());
        return true;
    }

    /**
     * Indique si l'effet Hunt est jouable pour l'utilisateur actuel
     * Certaines cartes ont des conditions pour l'activer
     *
     * @return un booléen qui indique si l'effet est jouable
     */
    public boolean effetHuntJouable() {
        if (Objects.nonNull(this.conditionHunt))
            return this.conditionHunt.estActivable(this.joueur.getModel());
        return true;
    }

    public String getDescriptionHunt() {
        if (Objects.nonNull(this.conditionHunt))
            return "" + this.descriptionHunt + "\n" + this.conditionHunt;
        return this.descriptionHunt;
    }

    public String getDescriptionWitch() {
        if (Objects.nonNull(this.conditionWitch))
            return "" + this.descriptionWitch + "\n" + this.descriptionWitch;
        return this.descriptionWitch;
    }

    /**
     * Permet d'activer l'effet hunt de la carte
     *
     * @throws CarteDejaJoueeException  si la carte a déjà été jouée
     * @throws EffetNonJouableException si les conditions pour jouer l'effet ne sont pas remplies
     */
    public void activerEffetHunt() throws CarteDejaJoueeException, EffetNonJouableException {
        verifieJouee();
        if (!this.effetHuntJouable())
            throw new EffetNonJouableException();
        this.pActiverEffetHunt();
        this.setEstJouee(true);
        // je pense plutot implémenter dans l'interface toutes les méthodes correspondants aux effets utiles.
    }

    /**
     * Permet d'activer l'effet hunt de la carte
     *
     * @throws CarteDejaJoueeException  si la carte a déjà été jouée
     * @throws EffetNonJouableException si les conditions pour jouer l'effet ne sont pas remplies
     */
    public void activerEffetWitch() throws CarteDejaJoueeException, EffetNonJouableException {
        verifieJouee();
        if (!this.effetWitchJouable())
            throw new EffetNonJouableException();
        this.pActiverEffetWitch();
        this.setEstJouee(true);
        // TODO: 13/11/2021 activer l'effet
    }

    /**
     * Partie à imlémenter pour les cartes
     * Permet l'activation réelle de l'effet witch
     * @throws EffetNonJouableException
     */
    protected abstract void pActiverEffetWitch() throws EffetNonJouableException;

    /**
     * Partie à implémenter pour les cartes
     * Permet l'activation réelle de l'effet hunt
     * @throws EffetNonJouableException
     */
    protected abstract void pActiverEffetHunt() throws EffetNonJouableException;

    /**
     * Vérifie si la carte a déjà été joué
     * Si telle est le cas, une exception est envoyée
     *
     * @throws CarteDejaJoueeException quand la carte a déjà été jouée
     */
    private void verifieJouee() throws CarteDejaJoueeException {
        if (this.estJouee)
            throw new CarteDejaJoueeException();
    }

    /**
     * Indique si la carte a déjà été révélée ou non
     *
     * @return un booléen qui est à vrai si elle a été révélée
     */
    public boolean estRevelee() {
        return this.estJouee;
    }

    /**
     * Indique le nom de la carte
     *
     * @return un string représentant le nom de cette dite carte
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Permet d'indiquer si la carte a été jouée ou non cad si elle est défaussée
     *
     * @param estJouee le booléen qui est à true est elle vient d'être joué
     */
    public void setEstJouee(boolean estJouee) {
        this.estJouee = estJouee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarteRumeur that)) return false;
        return descriptionWitch.equals(that.descriptionWitch) && descriptionHunt.equals(that.descriptionHunt) && Objects.equals(conditionHunt, that.conditionHunt) && Objects.equals(conditionWitch, that.conditionWitch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptionWitch, descriptionHunt, conditionHunt, conditionWitch);
    }

    @Override
    public String toString() {
        return this.nom;
    }

    /**
     * Permet de changer le propriétaire d'une carte
     * Effectue également le changement dans la main du joueur
     *
     * @param joueur le nouveau propriétaire
     */
    public void changerProprietaire(JoueurControlleur joueur) {
        this.joueur.getModel().retirerCarte(this);
        this.joueur = joueur;
        joueur.getModel().ajouterCarteRumeur(this);
    }

    /**
     * Permet de demander au joueur le joueur qu'il souhaite voir jouer le prochain tour
     *
     * @return le controlleur du prochain joueur qui va jouer
     */
    protected JoueurControlleur choixProchainJoueur() {
        JoueurControlleur cible = joueur.getJoueurVue().demanderProchainJoueur();
        joueur.getJoueurVue().afficherProchainJoueurTour(cible.getModel());
        Jeu.getInstance().setProchainJoueur(cible);
        return cible;
    }
}
