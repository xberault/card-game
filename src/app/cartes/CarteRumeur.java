package app.cartes;

import app.cartes.condition.Condition;
import app.cartes.models.AngryMob;
import app.cartes.models.BlackCat;
import app.cartes.models.Broomstick;
import app.cartes.models.Cauldron;
import app.cartes.models.DuckingStool;
import app.cartes.models.EvilEye;
import app.cartes.models.HookedNose;
import app.cartes.models.PetNewt;
import app.cartes.models.PointedHate;
import app.cartes.models.TheInquisition;
import app.cartes.models.Toad;
import app.cartes.models.Wart;
import app.joueur.JoueurControlleur;

import java.util.Objects;
import java.util.Set;

public abstract class CarteRumeur {

    // TODO: 13/11/2021 ajouter les conditions préalables des fois / effets connexes des cartes
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
    private final boolean estJouee;
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
                new PointedHate(),
                new TheInquisition(),
                new Toad(),
                new Wart()
        );
    }
    // TODO: 12/11/2021 check s'il faut utiliser le controlleur ou le modèle pour les cartes

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
            return this.conditionWitch.estActivable();
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
            return this.conditionHunt.estActivable();
        return true;
    }

    public String getDescriptionHunt() {
        if (Objects.nonNull(this.conditionHunt))
            return "" + this.conditionHunt + this.descriptionHunt;
        return this.descriptionHunt;
    }

    public String getDescriptionWitch() {
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
        if (!this.conditionHunt.estActivable())
            throw new EffetNonJouableException();
        // TODO: 13/11/2021 activer l'effet 
    }

    /**
     * Permet d'activer l'effet hunt de la carte
     *
     * @throws CarteDejaJoueeException  si la carte a déjà été jouée
     * @throws EffetNonJouableException si les conditions pour jouer l'effet ne sont pas remplies
     */
    public void activerEffetWitch() throws CarteDejaJoueeException, EffetNonJouableException {
        verifieJouee();
        if (!this.conditionWitch.estActivable())
            throw new EffetNonJouableException();
        // TODO: 13/11/2021 activer l'effet
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarteRumeur)) return false;
        CarteRumeur that = (CarteRumeur) o;
        return descriptionWitch.equals(that.descriptionWitch) && descriptionHunt.equals(that.descriptionHunt) && Objects.equals(conditionHunt, that.conditionHunt) && Objects.equals(conditionWitch, that.conditionWitch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptionWitch, descriptionHunt, conditionHunt, conditionWitch);
    }

    @Override
    public String toString() {
        return "CarteRumeur{" +
                " nom='" + this.getNom() + '\'' +
                ", descriptionWitch='" + descriptionWitch + '\'' +
                ", descriptionHunt='" + descriptionHunt + '\'' +
                ", conditionHunt=" + conditionHunt +
                ", conditionWitch=" + conditionWitch +
                ", estJouee=" + estJouee +
                '}';
    }
}
