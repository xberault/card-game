package app.model.action;

import app.cartes.EffetNonJouableException;

/**
 * Représente une action utilisateur
 * Ce système obligé à des créer des objets qui seront inutilisés par l'utilisateur
 * En temre d'optimisations il y a meilleur
 */
public interface Action {
    void executerAction() throws EffetNonJouableException;
}
