import java.io.*;
import java.util.*;

public class Perceptron {

    public static double[] appPerceptron(double[] perceptron, double [] exemple,
                                         double sortie, double pas) {
	/*
	   mise a jour des poids du perceptron
	   suivant la regle d'apprentissage
	   du perceptron
	*/
        double output = predict(perceptron, exemple);

        /* poids */
        System.out.println(perceptron.length);
        for (int i = 0; i < perceptron.length; i++) {
            perceptron[i] += pas * (sortie - output) * exemple[i];
            affichePoids(perceptron);
        }


        /* seuil */
        return perceptron;
    }

    public static double[] hebb(double[] perceptron, double[] exemple, double pas) {
    /*
	  mise a jour des poids du perceptron
	  suivant la loi de hebb
	*/
        double output = predict(perceptron, exemple);

        /* poids */
        System.out.println(perceptron.length);
        for (int i = 0; i < perceptron.length; i++) {
            perceptron[i] += pas * (exemple[i] - output) * exemple[i];
        }

        affichePoids(perceptron);

        /* seuil */
        return perceptron;
    }

    public static void affichePoids(double[] perceptron) {
        int i;

        for (i=0; i< perceptron.length -1 ; i++)
            System.out.printf("\t w%d =  %2.2f", i, perceptron[i]);
        System.out.printf("\t seuil =  %2.2f\n", perceptron[perceptron.length -1]);

    }


    public static void learn(double[][] exemples, String loi, double pas) {

        /* Deux poids sur les entrees [0][1], un seuil [2] */
        double[] perceptron = {Math.random(), Math.random(), Math.random()};

        boolean[] appris = {false, false, false,false};
        /* vrai si tous les exemples passent sans erreur */
        boolean apprentissage = false;

        /* Apprentissage */
        var iter = 0;
        while (!apprentissage && iter<10) {
            apprentissage = true;
            iter++;
            for (double[] exemple : exemples) {
                switch (loi) {
                    case "app" -> appPerceptron(perceptron, exemple, exemple[2], pas);
                    case "hebb" -> hebb(perceptron, exemple, pas);
                }
                if (predict(perceptron, exemple) != exemple[2]) {
                    apprentissage = false;
                }
            }
        }

        affichePoids(perceptron);
    }

    public static double predict(double[] perceptron, double[] exemple) {
        double sum = 0.0;
        for (int i = 0; i < perceptron.length; i++) {
            sum += perceptron[i] * exemple[i];
        }
        return sum >= 0 ? 1.0 : 0.0; // fonction d'activation de type seuil
    }



    public static void main(String [] args) throws IOException {
        double[][] table = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 0, 0},
                {1, 1, 1}
        };
        double pas = .005;

        System.out.println("... table ...");
//        learn(table, /* à compléter */, pas);
        learn(table, "app", pas);
//        affichePoids(table[0]);
//        affichePoids(table[1]);
//        affichePoids(table[2]);
//        affichePoids(table[3]);


    }
}