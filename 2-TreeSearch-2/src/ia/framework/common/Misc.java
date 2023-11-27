package ia.framework.common;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Quelques trucs pour ce faciliter la vie
 */

public class Misc {

    /**
     * Affiche une Collection de quelconque
     * @param c Une collection
     * @param sep Un séparateur
     */
    
    public static void printCollection(Collection<?> c, char sep){
        System.out.println(collection2string(c, sep));
    }

    /** 
     * Convertir une collection en String avec un séparateur
     * @param c Une collection
     * @param sep Un séparateur
     */
    
    public static String collection2string(Collection<?> c, char sep){
         return new String(c.stream()
                       .map(Object::toString)
                       .collect(Collectors.joining(" "+sep+" ")));
    }
}
