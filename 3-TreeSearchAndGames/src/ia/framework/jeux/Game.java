package ia.framework.jeux;

import java.util.ArrayList;
import java.util.Random;

import ia.framework.common.BaseProblem;
import ia.framework.common.Action;
import ia.framework.common.Pair;
import ia.framework.common.State;

/**
 * Définie un problem de type jeux
 *
 * est un BaseProblem sans tableau d'état ni transitions
 * on les genère dans l'état du jeux comme pour SearchProblem
 */

public abstract class Game extends BaseProblem {

    // pour générer des coups aléatoires
    private Random rng = new Random(); 


    /**
     * Crée l'état initial du jeux
     *
     * @return un état du jeux vide
     */
    public abstract GameState init();

    
    
    /**
     *retourne vrai si on arrive en fin de partie
     * 
     * @param s l'état du jeux
     * @return vrai si fin du jeux
     */
    
    public abstract boolean endOfGame(GameState s);

    /**
     * Demander à l'utilisateur son coup (dépend du jeux)
     *
     * @param s l'état du jeux
     * @return l'action choisie par l'utilisateur
     */ 
    public abstract Action getHumanMove(GameState s);


    /**
     * Générer un coup  aléatoire
     *
     * @param s l'état du jeux
     * @return l'action générée
     */ 
    public Action getRandomMove(GameState s){
        ArrayList<Action> possible = getActions((State) s);
        if( possible.size()>0 )
            return possible.get(rng.nextInt(possible.size()));
        return null; 
    }


    public Action getMinMaxMove(GameState state) {
        if (endOfGame(state)) {
            return null;
        }
        Pair<Integer, Action> result = max(state);
        return result.second;
    }

    private Pair<Integer, Action> max(GameState state) {
        if (endOfGame(state)) {
            return new Pair<>((int)state.getGameValue(), null);
        }
        int bestValue = Integer.MIN_VALUE;
        Action bestAction = null;
        for (Action action : getActions(state)) {
            GameState nextState = (GameState) doAction(state, action);
            int value = min(nextState).first;
            if (value > bestValue) {
                bestValue = value;
                bestAction = action;
            }
        }
        return new Pair<>(bestValue, bestAction);
    }

    private Pair<Integer, Action> min(GameState state) {
        if (endOfGame(state)) {
            return new Pair<>((int)state.getGameValue(), null);
        }
        int bestValue = Integer.MAX_VALUE;
        Action bestAction = null;
        for (Action action : getActions(state)) {
            GameState nextState = (GameState) doAction(state, action);
            int value = max(nextState).first;
            if (value < bestValue) {
                bestValue = value;
                bestAction = action;
            }
        }
        return new Pair<>(bestValue, bestAction);
    }


}
