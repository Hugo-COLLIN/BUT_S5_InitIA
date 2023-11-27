import ia.framework.common.ArgParse;
import ia.framework.common.State;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import ia.problemes.*; 

/**
 * Lance un algorithme de recherche  
 * sur un problème donné et affiche le resultat
 */
public class LancerRecherche {

    public static void main(String[] args){

        // récupérer les option de la ligne de commande
        String prob_name = ArgParse.getProblemFromCmd(args);
        String algo_name = ArgParse.getAlgoFromCmd(args);

        // créer un problem, un état intial et un  algo
        SearchProblem p = ArgParse.makeProblem(prob_name);
        State s = ArgParse.makeInitialState(prob_name);
        TreeSearch algo = ArgParse.makeAlgo(algo_name, p, s);
        
        // resoudre 
        if( algo.solve() )
            algo.printSolution();
    }
}
