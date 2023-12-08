package ia.algo.recherche;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFS extends TreeSearch {
    /**
     * Crée un algorithme de recherche
     *
     * @param p Le problème à résoudre
     * @param s L'état initial
     */
    public DFS(SearchProblem p, State s) {
        super(p, s);
    }

    @Override
    public boolean solve() {
        // Créer une file d'attente pour les nœuds à visiter
        Stack<SearchNode> frontiere = new Stack<>();

        // Ajouter le nœud initial à la file d'attente
        SearchNode node = SearchNode.makeRootSearchNode(intial_state);
        frontiere.add(node);

        // Tant qu'il y a des nœuds à visiter
        while (!frontiere.isEmpty()) {
            // Retirer le prochain nœud de la file d'attente
            node = frontiere.pop();
            State state = node.getState();

            // Si cet état est l'état but, terminer
            if (problem.isGoalState(state)) {
                end_node = node;
                return true;
            }

            // Sinon, ajouter tous les nœuds enfants à la file d'attente
            ArrayList<Action> actions = problem.getActions(state);
            for (Action a : actions) {
                SearchNode childNode = SearchNode.makeChildSearchNode(problem, node, a);
                frontiere.add(childNode);
            }
        }

        // Si on arrive ici, aucun chemin n'a été trouvé
        return false;
    }
}
