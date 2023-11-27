package ia.framework.common;

import java.util.Arrays;

import ia.framework.common.*;
import ia.framework.recherche.*;
import ia.problemes.*;
import ia.algo.recherche.*;

/**
 * Quelques méthodes rudimentaires pour lire la ligne de commande
 * et lancer le Schmilblick
 *
 */

public class ArgParse {

    /**
     * Pour afficher plus de chose  
     */
    public static boolean DEBUG = false;

    /**
     * Affiche un message d'utilisation 
     */
    public static void usage(){
        
        System.err.println
            ("Utilisation :\n\n"
             + "java MainClassName [-prob problem] [-algo algoname] [-v] [-h]\n"
             + "-prob : Le nom du problem {dum, map, vac, puz}. Par défautl vac\n"
             + "-algo : L'agorithme {rnd, bfs, dfs, ucs, gfs, astar}. Par défault rnd\n"
             + "-v    : Rendre bavard (mettre à la fin)\n"
             + "-h    : afficher ceci (mettre à la fin)"
             );
    }

    /** 
     * Retourne la valeur d'un champ demandé
     * @param args Le tableau de la ligne de commande
     * @param arg Le paramètre qui nous intéresse
     * @return La valeur du paramètre
     */
    public static String getArgFromCmd(String[] args, String arg){
        if (args.length > 0){
            int idx = Arrays.asList(args).indexOf(arg);
            if (idx%2 == 0 && idx < args.length-1)
                return args[idx+1];
            if (idx <0)
                return null;
            usage();
        }    
        return null;
            
    }
    /** 
     * Pour vérifier l'existence d'une option donnée
     * @param args Le tableau de la ligne de commande
     * @param arg L'option qui nous intéresse
     * @return vrai si l'option existe
     */
    public static boolean getFlagFromCmd(String[] args, String arg){
        int idx = Arrays.asList(args).indexOf(arg);
        if (idx >=0)
            return true;
        return false;
            
    }

    /** 
     * Retourne le nom problème choisi
     * @param args Le tableau de la ligne de commande
     * @return le nom du problème ou null 
     */
    public static String getProblemFromCmd(String[] args){
        handleFlags(args);
        return getArgFromCmd(args, "-prob");
    }
    
    /** 
     * Retourne le nom l'algorithme choisi
     * @param args Le tableau de la ligne de commande
     * @return le nom de l'algorithme ou null 
     */
    public static String getAlgoFromCmd(String[] args){
        handleFlags(args);
            
        return getArgFromCmd(args, "-algo");
    }

    /** 
     * Traitement des options -v, -h
     * @param args Le tableau de la ligne de commande
     */
     public static void handleFlags(String[] args){
         DEBUG = getFlagFromCmd(args, "-v");
         if (getFlagFromCmd(args, "-h")) {
             usage();
             System.exit(0);
         }
     }
             
    
    /** 
     * Factory qui retourne une instance du problème choisie ou celui
     * par défaut
     * @param prob Le nom du problème ou null 
     * @return Une instance du problème
     */
    
    public static SearchProblem makeProblem(String prob){
        if (prob==null)
            prob = "vac";
        switch (prob) {
        case "dum":
            return new Dummy();
        case "map":
            return new RomaniaMap();
        case "vac":
            return new Vacuum();
        case "puz":
            return new EightPuzzle();
        default :
            System.out.println("Problème inconnu");
            usage();
            System.exit(1); 
        }

        return null; // inatteignable, faire plaisir a javac
    }

     /** 
     * Factory qui retourne une instance de l'algorithme choisi ou
     * celui par défaut
     * @param algo Le nom de l'algorithme ou null
     * @param p Le problème a résoudre
     * @param s L'état initial 
     * @return Une instance de l'algorithme
     */
    public static TreeSearch makeAlgo(String algo, SearchProblem p,
                                      State s){
        if (algo==null)
            algo = "rnd";
        switch (algo) {
        case "rnd":
            return new RandomSearch(p,s);

        // Ce sera a vous de les faire :)
        case "bfs":
            return new BFS(p,s);
        case "dfs":
            return new DFS(p,s);
//        case "ucs":
//            return new UCS(p,s);
//        case "gfs":
//            return new GFS(p,s);
//        case "astar":
//        return new AStar(p,s);
            
        default :
            System.out.println("Algorithme inconnu");
            usage();
            System.exit(1); 
            
        }
        return null;  // inatteignable, faire plaisir a javac
    }
    
    /** 
     * Factory qui retourne une instance de l'état initial du problème
     * choisi
     * @param prob Le nom du problème ou null 
     * @return  L'état initial qui peut être fixé ou généré
     * aléatoirement
     * 
     */
    public static State makeInitialState(String prob){
        if (prob==null)
            prob = "vac";
        switch (prob) {
        case "dum":
            return new DummyState();
        case "map":
            return RomaniaMap.ARAD;
        case "vac":
        default:
            return new VacuumState();
        case "puz":
            return new EightPuzzleState();
        }
    }
}


