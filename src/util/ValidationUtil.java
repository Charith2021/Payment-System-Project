package util;

import java.time.LocalDate;

public class ValidationUtil {

         public static boolean isInteger(String input){      //static methoda hadanna SFC eke idan kelinma call krnna
             if(input.startsWith("+") || input.startsWith("-")){
                 return false;
             }
             try {
                 Long.parseLong(input);
                 return true;
             }catch (NumberFormatException e){
                 return false;
             }
         }

         public static boolean isValid(String input,boolean checkSpaces,boolean checkDigits,char... symbols){
         char[] chars=input.toCharArray();
         loop1:    //its a label
         for(char aChar:chars){
             if((checkDigits && Character.isDigit(aChar))|| (checkSpaces && Character.isSpaceChar(aChar))){
                 continue;
             }

             for(char symbol : symbols){
                 if(aChar == symbol){
                     continue loop1;        //continue wenna ona me for loop eken nowe loop1 kiyana for loop eken
                 }
             }

             if(!Character.isAlphabetic(aChar)){
                 return false;
             }
         }
         return  true;
         }

    public static boolean isValidDate(String input) {
        try {
            LocalDate.parse(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidEmail(String input) {

        // abc_adfad.afdaf@ijse.edu.lk
        // firstPart = abc_adfad.afdaf = split[0]
        // split[1] = ijse
        // secondPart =
        String[] split = input.split("@");       //@ kiyana tanin mail eka delata kda ganna

        if (split.length != 2) {
            return false;                 //kali dekak nathnm enter krpu eke false return krnna
        }

        String firstPart = split[0];            //palaweni kalle index eka 0
        int lastIndex = split[1].lastIndexOf('.');             //dewani kalle index eka 1

        if (lastIndex == -1) {    //dewani kalle dot ekak nathi unoth waradiy -1 denne .com .lk wage tiyenna ona. dewani kalla kadenne me anthima dot eka lagin
            return false;
        }
        String secondPart = split[1].substring(0, lastIndex);
        String thirdPart = split[1].substring(lastIndex + 1);   //ekak ekathu kare ntnm third part eka com newi .com kyala enne

        // @ijse.lk
        if (!(secondPart.length() >= 2 && thirdPart.length() >= 2)) {        //second part ekath dot eken kadunama kali 2 or more enna ona   .edu.lk
            return false;
        }

        if (!isValid(firstPart, false, true, '.', '_') || (firstPart.startsWith(".") || firstPart.endsWith(".") || firstPart.startsWith("_") || firstPart.endsWith("_"))) {
            return false;
        }

        if (!(isValid(secondPart, false, true, '.') && !secondPart.startsWith(".") && !secondPart.endsWith("."))) {
            return false;
        }

        if (!isValid(thirdPart, false, true)) {
            return false;
        }
        return true;
    }

}




