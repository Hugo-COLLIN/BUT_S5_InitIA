package ia.problemes;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.Problem;

/**
 * Représente le problème de notre robot aspirateur
 * 
 */
public class Vacuum extends Problem {

    public Vacuum() {
  
        // L'ensemble des états
        
        STATES = new State[] {
            new VacuumState(0,new boolean[]{true,true}),   //0
            new VacuumState(1,new boolean[]{true,true}),   //1 
            new VacuumState(0,new boolean[]{false,true}),  //2
            new VacuumState(1,new boolean[]{false,true}),  //3 
            new VacuumState(0,new boolean[]{true, false}), //4 
            new VacuumState(1,new boolean[]{true, false}), //5 
            new VacuumState(0,new boolean[]{false, false}),//6 
            new VacuumState(1,new boolean[]{false, false}) //7
        };
        
        // L'ensemble des actions
        
        Action GO_LEFT = new Action("Go left");
        Action GO_RIGHT = new Action("Go right");
        Action SUCK = new Action("Suck");

        ACTIONS = new Action[] { GO_LEFT, GO_RIGHT, SUCK };

        // Les transitions possibles 
        TRANSITIONS.addTransition(STATES[0], GO_LEFT,  STATES[0], 1);
        TRANSITIONS.addTransition(STATES[0], GO_RIGHT, STATES[1], 1);
        TRANSITIONS.addTransition(STATES[0], SUCK,     STATES[2], 1);
        TRANSITIONS.addTransition(STATES[1], GO_LEFT,  STATES[0], 1);
        TRANSITIONS.addTransition(STATES[1], GO_RIGHT, STATES[1], 1);
        TRANSITIONS.addTransition(STATES[1], SUCK,     STATES[5], 1);
        TRANSITIONS.addTransition(STATES[2], GO_LEFT,  STATES[2], 1);
        TRANSITIONS.addTransition(STATES[2], GO_RIGHT, STATES[3], 1);
        TRANSITIONS.addTransition(STATES[2], SUCK,     STATES[2], 1);
        TRANSITIONS.addTransition(STATES[3], GO_LEFT,  STATES[2], 1);
        TRANSITIONS.addTransition(STATES[3], GO_RIGHT, STATES[3], 1);
        TRANSITIONS.addTransition(STATES[3], SUCK,     STATES[7], 1);
        TRANSITIONS.addTransition(STATES[4], GO_LEFT,  STATES[4], 1);
        TRANSITIONS.addTransition(STATES[4], GO_RIGHT, STATES[5], 1);
        TRANSITIONS.addTransition(STATES[4], SUCK,     STATES[6], 1);
        TRANSITIONS.addTransition(STATES[5], GO_LEFT,  STATES[4], 1);
        TRANSITIONS.addTransition(STATES[5], GO_RIGHT, STATES[5], 1);
        TRANSITIONS.addTransition(STATES[5], SUCK,     STATES[6], 1);
        TRANSITIONS.addTransition(STATES[6], GO_LEFT,  STATES[6], 1);
        TRANSITIONS.addTransition(STATES[6], GO_RIGHT, STATES[7], 1);
        TRANSITIONS.addTransition(STATES[6], SUCK,     STATES[6], 1);
        TRANSITIONS.addTransition(STATES[7], GO_LEFT,  STATES[6], 1);
        TRANSITIONS.addTransition(STATES[7], GO_RIGHT, STATES[7], 1);
        TRANSITIONS.addTransition(STATES[7], SUCK,     STATES[7], 1);
    }

    public boolean isGoalState(State s){
        return s.equals(STATES[6]) || s.equals(STATES[7]) ;
    }

}
