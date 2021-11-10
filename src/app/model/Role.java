package app.model;

public enum Role {
    INDEFINI,
    VILLAGEOIS,
    SORCIERE;

    /**
     * Permet d'obtenir tous les roles jouables par un joueur
     *
     * @return un tableau contenant tous les rôles que le joueur peut incarner
     */
    public static Role[] getRolesJouables() {
        return new Role[]{
                VILLAGEOIS, SORCIERE
        };
    }
}
