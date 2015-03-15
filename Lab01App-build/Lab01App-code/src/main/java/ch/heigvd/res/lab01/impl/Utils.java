package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

   private static final Logger LOG = Logger.getLogger(Utils.class.getName());

   /**
    * This method looks for the next new line separators (\r, \n, \r\n) to extract
    * the next line in the string passed in arguments.
    *
    * @param lines a string that may contain 0, 1 or more lines
    * @return an array with 2 elements; the first element is the next line with the
    * line separator, the second element is the remaining text. If the argument does
    * not contain any line separator, then the first element is an empty string.
    */
   public static String[] getNextLine(String lines) {
      int[] firstSeparator = new int[2];
      String[] result = new String[2];
      
      firstSeparator[0] = lines.indexOf("\r");
      firstSeparator[1] = lines.indexOf("\n");
     
      // Si \n est trouvé avant \r, on retient l'emplacement de \n
      if(firstSeparator[1] != -1 && firstSeparator[1] < firstSeparator[0]){
         firstSeparator[0] = firstSeparator[1];
         // Si \r est suivit de \n, on retient la position de \n
      } else if(firstSeparator[0] != -1 && firstSeparator[0] == firstSeparator[1] - 1){
         ++firstSeparator[0];
         // Si seul un \n est trouvé, on retient la position du \n
      } else if(firstSeparator[0] == -1 && firstSeparator[1] != -1){
         firstSeparator[0] = firstSeparator[1];
      }
      
      if(firstSeparator[0] == -1){
         result[0] = "";
         result[1] = lines;
      } else {
         result[0] = lines.substring(0, firstSeparator[0] + 1);
         result[1] = lines.substring(firstSeparator[0] + 1);
      }
      
      return result;
      
      //throw new UnsupportedOperationException("The student has not implemented this method yet.");
   }

}
