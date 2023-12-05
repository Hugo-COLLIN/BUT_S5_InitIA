package ia.algo.jeux;


import ia.framework.common.Action;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

/**
 * Définit un joueur MinMax
 *
 */

public class MinMaxPlayer extends Player {

    /**
     * Crée un joueur MinMax
     * @param g l'instance du jeux
     * @param p1 vrai si joueur 1
     */
    public MinMaxPlayer(Game g, boolean p1){
        super(g, p1);
        name = "minmax";
    }
    
    
    
    /**
     * {@inheritDoc}
     * <p>Retourn un coup suivant MixMax</p>
     */
    public Action getMove(GameState state){
        return game.getMinMaxMove(state);
    }



}
