package ia.algo.recherche;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

public class UCS extends TreeSearch {
    /**
     * Crée un algorithme de recherche
     *
     * @param problem Le problème à résoudre
     * @param initialState L'état initial
     */
    public UCS(SearchProblem problem, State initialState) {
        super(problem, initialState);
        Comparator<SearchNode> comparator = Comparator.comparing(SearchNode::getCost);
        this.frontier = new PriorityQueue<>(comparator);
    }

    @Override
    public boolean solve() {
        // Utiliser une file de priorité pour les noeuds à visiter
        this.frontier = new PriorityQueue<>();

        // Ajouter le noeud initial à la file d'attente
        SearchNode node = SearchNode.makeRootSearchNode(intial_state);
        this.frontier.add(node);

        // Tant qu'il y a des noeuds à visiter
        while (!this.frontier.isEmpty()) {
            // Retirer le noeud ayant le coût le plus bas
            node = this.frontier.poll();
            State state = node.getState();

            // Si cet état est l'état but, terminer
            if (problem.isGoalState(state)) {
                end_node = node;
                return true;
            }

            // Sinon, ajouter tous les noeuds enfants à la file d'attente
            ArrayList<Action> actions = problem.getActions(state);
            for (Action a : actions) {
                SearchNode childNode = SearchNode.makeChildSearchNode(problem, node, a);
                this.frontier.add(childNode);
            }
        }

        // Si on arrive ici, aucun chemin n'a été trouvé
        return false;
    }

//    @Override
//    public boolean solve() {
//        SearchNode initialNode = SearchNode.makeRootSearchNode(this.intial_state);
//        this.frontier.add(initialNode);
//
//        while (!this.frontier.isEmpty()) {
//            SearchNode currentNode = this.frontier.poll();
//            if (this.problem.isGoalState(currentNode.getState())) {
//                this.end_node = currentNode;
//                return true;
//            }
//            this.explored.add(currentNode.getState());
//            for (Action action : this.problem.getActions(currentNode.getState())) {
//                if (!this.explored.contains(this.problem.doAction(currentNode.getState(), action))) {
//                    SearchNode childNode = SearchNode.makeChildSearchNode(this.problem, currentNode, action);
//                    this.frontier.add(childNode);
//                }
//            }
//        }
//        return false;
//
//        //simplifier le code
////        this.frontier.add(SearchNode.makeRootSearchNode(this.intial_state));
//
//    }









//    @Override
//    public boolean solve() {
//        // TODO: Implement UCS algorithm
//        // Commencez par ajouter le nœud initial à la frontière.
//        this.frontier.add(SearchNode.makeRootSearchNode(this.intial_state));
//
//        // Ensuite, dans une boucle,
//        for (SearchNode node : this.frontier) {
//            // retirez le nœud avec le coût le plus bas de la frontière,
//
//
//            // vérifiez si c'est un nœud cible
//            if (this.problem.isGoalState(node.getState())) {
//                // si ce n'est pas le cas, ajoutez tous ses nœuds enfants à la frontière.
//                // Continuez jusqu'à ce que la frontière soit vide ou que vous ayez trouvé un nœud cible.
//                this.end_node = node;
//                return true;
//            }
//        }
//
//        // retirez le nœud avec le coût le plus bas de la frontière,
//        // vérifiez si c'est un nœud cible
//        // si ce n'est pas le cas, ajoutez tous ses nœuds enfants à la frontière.
//        // Continuez jusqu'à ce que la frontière soit vide ou que vous ayez trouvé un nœud cible.
//
//    }
}
