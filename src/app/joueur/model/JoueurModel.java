package app.joueur.model;

import app.cartes.CarteRumeur;
import app.cartes.effet.IEffet;
import app.cartes.effet.NePeutPasEtreAccuseException;
import app.joueur.model.etat.EtatAttente;
import app.joueur.model.etat.EtatChoixIdentite;
import app.joueur.model.etat.IEtat;
import app.model.Role;
import app.model.action.Action;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class JoueurModel {


    /**
     * Désigne le nom du joueur
     */
    private final String nom;

    /**
     * Représente le nombre de points possédés par le joueur
     */
    private int points;

    /**
     * Représente le rôle du joueur pour la partie actuelle
     * la valeur est à role.INDEFINI lorsqu'il n'en a pas encore
     */
    private Role role;

    /**
     * Représente l'état actuel du joueur au cours de la partie
     * Par défaut il est en train d'attendre son tour
     */
    private IEtat etatActuel;

    /**
     * contient "l'id" de la propriété du changement d'état d'un joueur
     */
    public static String PCHANGEMENTETAT = "changementEtat";

    /**
     * Remplace l'implémentation de Observable (qui est maintenant deprecated)
     */
    private final PropertyChangeSupport pcs;

    /**
     * Les cartes possédées pour la partie
     */
    private final List<CarteRumeur> lesCartes;

    /**
     * Indique si le joueur est revele ou non
     */
    private boolean identiteRevele;

    /**
     * Tous les effets à vérifier à chaque changement d'état
     * ceux-ci sont ajoutés par l'activation des cartes
     */
    List<IEffet> effetsChangementEtat;

    protected JoueurModel(String nom) {
        this.points = 0;
        this.nom = nom;
        this.role = Role.INDEFINI;
        this.etatActuel = new EtatAttente(this);
        this.lesCartes = new ArrayList<>();
        this.effetsChangementEtat = new ArrayList<>();

        this.identiteRevele = false;
        this.pcs = new PropertyChangeSupport(this);
    }

    /**
     * L'observeur ajouté sera notifié lorsque l'etat du joueur change
     *
     * @param l l'observeur en question
     */
    public void ajouterObserverEtat(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(PCHANGEMENTETAT, l);
    }

    /**
     * Permet de changer l'état actuel du joueur
     * Réveille ensuite tous les threads en attentes
     * <p>
     * Si l'état n'est
     *
     * @param etat le nouvel etat à mettre
     * @param args tous les paramètres éventuels permettant le déclenchement des effets
     * @throws ChangementEtatException quand l'etat d'accusation n'est pas atteignable
     */
    public void changerEtat(IEtat etat, Object... args) throws ChangementEtatException {
        IEtat etatAvant = this.etatActuel;
        try {
            this.triggerEffets(etatAvant, etat, args);
        } catch (NePeutPasEtreAccuseException e) {
            throw new ChangementEtatException("L'état d'accusation n'est pas atteignable pour le joueur1");
        }
        this.etatActuel = etat;
        this.pcs.firePropertyChange(PCHANGEMENTETAT, etatAvant, etatActuel);
    }

    /**
     * Active tous les effets possédés par le joueur si les conditions sont remplies
     *
     * @param etatAvant   l'ancien état du joueur
     * @param etatNouveau le nouvel etat du joueur
     * @param args        tous les paramètres éventuels permettant le déclenchement des effets
     */
    private void triggerEffets(IEtat etatAvant, IEtat etatNouveau, Object... args) throws NePeutPasEtreAccuseException {
        for (IEffet effet : this.effetsChangementEtat) {
            if (effet.estActivable(etatAvant, etatNouveau)) {
                this.effetsChangementEtat.remove(effet);
                effet.activer(args);
            }
        }
    }

    /**
     * change le role actuel du joueur
     *
     * @param role le nouveau role au joueur
     */
    public void changerRole(Role role) {
        // vérification que le joueur ait la possibilité de changer de role
        if (this.etatActuel instanceof EtatChoixIdentite)
            this.role = role;
    }

    /**
     * Le joueur révèle son identité aux autres joueurs
     */
    public void seRevele() {
        // TODO: 13/11/2021 Peut-être mettre un observer sur ce booléen; à voir
        this.identiteRevele = true;
    }

    /**
     * @return un tableau contenant toutes les actions actuellement disponibles pour le joueur
     */
    public Action[] getActionsDisponibles() {
        return this.etatActuel.getActionsDisponibles();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JoueurModel that)) return false;
        return points == that.points && Objects.equals(nom, that.nom) && role == that.role && Objects.equals(pcs, that.pcs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, points, role, pcs);
    }

    public Role getRole() {
        return this.role;
    }


    public int getPoints() {
        return this.points;
    }


    @Override
    public String toString() {
        return "JoueurModel{" +
                "nom='" + nom + '\'' +
                ", points=" + points +
                ", role=" + role +
                ", identiteRevele=" + identiteRevele +
                '}';
    }

    /**
     * Permet d'obtenir toutes les cartes encore dans la main du joueur
     *
     * @return un tableau contenant toutes ces cartes
     */
    public CarteRumeur[] getCartesMain() {
        List<CarteRumeur> lesCartesMain = new ArrayList<>(this.lesCartes); // on fait une copie
        lesCartesMain.removeAll(List.of(this.getCartesRumeursRevelees()));
        return lesCartesMain.toArray(new CarteRumeur[0]);
    }


    /**
     * Ajoute une carte rumeur dans la main du joueur
     *
     * @param carteRumeur la carte à ajouter
     */
    public void ajouterCarteRumeur(CarteRumeur carteRumeur) {
        this.lesCartes.add(carteRumeur);
    }

    public String getNom() {
        return this.nom;
    }

    /**
     * Permet d'obtenir toutes les cartes dans la défausse du joueur
     *
     * @return un tableau contenant toutes ces cartes
     */
    public CarteRumeur[] getCartesRumeursRevelees() {
        List<CarteRumeur> cartesRevelees = new ArrayList<>();
        for (CarteRumeur carte : this.lesCartes)
            if (carte.estRevelee())
                cartesRevelees.add(carte);
        return cartesRevelees.toArray(new CarteRumeur[0]);
    }


    public boolean estRevele() {
        return this.identiteRevele;
    }

    /**
     * Permet de mettre une carte dans la défausse du joueur
     *
     * @param carte la carte à défausser
     */
    public void defausserCarte(CarteRumeur carte) {
        carte.setEstJouee(true);
    }

    /**
     * Permet de retirer une carte spécifique à la fois de la défausse du joueur et de sa main
     *
     * @param carteRumeur la carte à retirer
     */
    public void retirerCarte(CarteRumeur carteRumeur) {
        this.lesCartes.remove(carteRumeur);
    }

    /**
     * Ajoute un effet
     *
     * @param effet l'effet à ajouter au joueur
     */
    public void ajouterEffetChangementEtat(IEffet effet) {
        this.effetsChangementEtat.add(effet);
    }

    /**
     * Ajoute le nombre de points au joueur
     * Si ce nombre est négatif, lui retire
     *
     * @param nbPoints le nombre de point à ajouter
     */
    public void ajouterPoints(int nbPoints) {
        this.points += nbPoints;
    }

    /**
     * Passe le joueur dans son prochain etat de jeu
     */
    public void prochainEtat() {
        try {
            this.changerEtat(this.etatActuel.getProchainEtat());
        } catch (ChangementEtatException e) {
            e.printStackTrace();
        }
    }
}
