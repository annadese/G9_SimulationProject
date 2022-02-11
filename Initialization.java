import java.util.*;

public class Initialization {

    int exponent;
    String mantissa;
    String whole;
    String fraction;
    boolean onlyZero;   // if whole part is 0 only
    boolean bFraction = false;
    int[] ans = new int[64];

    Initialization (String sMantissa, String sExponent, int base) {
        this.mantissa = sMantissa;
        this.exponent = Integer.parseInt(sExponent);
        this.onlyZero = false;
        boolean check = false;
        char c;
        
        for(int i = 0; i < mantissa.length(); i++) {
            c = mantissa.charAt(i);

            if (c == '.') {
                check = true;
            }
        }

        mantissa = Double.toString(Double.parseDouble(mantissa));

        if (check) {
            String[] splitMantissa = String.valueOf(mantissa).split("\\.");
            this.whole = splitMantissa[0];
            this.fraction = splitMantissa[1];
        } else {
            this.whole = mantissa;
        }

        if (fraction.equals("0")) {
            this.whole = mantissa;
            this.fraction = null;
        }

        if (base == 10) {
            mantissa =  decimalToBinary(mantissa);
        }

        boolean norm = isNormalized();
        if (!norm) {
            ans = normalize();
        }
    }


    boolean isNormalized () {
        // if num has only 1 digit and is 1
        if (whole.length() == 1 && whole.charAt(0) == '1') 
            return true;
        
        // if num has only 1 digit and is 0
        if (whole.length() == 1 && whole.charAt(0) == '0') {
            onlyZero = true;
            return false;
        }

        // if num has more than 1 digit
        else if (whole.length() > 1) 
            return false;
        
        return true;
    }

    int[] normalize () {
        int[] array = new int[64];
        int x = 0;
        int decimalPlace = 0;
        boolean found = false;
        
        // if whole part is 0
        if (onlyZero == true) {
            for (int i = 0; i < fraction.length(); i++) {
                char c = fraction.charAt(i);
    
                if (c == '1' && !found) {
                    decimalPlace = i + 1;
                    found = true;
                    
                    this.exponent = exponent - decimalPlace;
                } 
                array[i] = Integer.parseInt(String.valueOf(c));
            }

        } else {
            for (int j = 0; j < mantissa.length(); j++) {
                char c = mantissa.charAt(j);
    
                if (c == '.' && !found) {
                    decimalPlace = j - 1;
                    found = true;
                    this.exponent = exponent + decimalPlace;
                } else {
                    array[x] = Integer.parseInt(String.valueOf(c));
                    x++;
                }
            }
            
        }

        return array;
    }

    public String decimalToBinary(String str){
        String binaryFinal = "";
        int decimalPlace = -1;
        int i = 0, x = 0;

        //get the place where the decimal/period is placed
        for(i = 0; i < str.length(); i++){
            if(str.charAt(i) == '.')
                decimalPlace = i;
        }

        //the whole number and decimal part is divided into 2 different Strings
        String strWholeNum = "";
        String strDecimalNum = "";

        if(decimalPlace >= 0){ // executes if given has a decimal
            //puts the whole number (the left side of the decimal) in a string
            for(i = 0; i < decimalPlace; i++){
                if(str.charAt(i) != '.')
                    strWholeNum = strWholeNum + str.charAt(i);
            }

            //puts the decimal number (the right side of the decimal) in a string
            for(i = decimalPlace+1; i < str.length(); i++){
                if(str.charAt(i) != '.')
                    strDecimalNum = strDecimalNum + str.charAt(i);
            }

            //converts from decimal to binary
            int nDecimal = Integer.parseInt(strDecimalNum);
            strDecimalNum = getBinaryFractionPart(nDecimal);
            if(decimalPlace != 0) { //executes if a whole number exists
                int nWhole = Integer.parseInt(strWholeNum);
                strWholeNum = getBinaryWholeNum(nWhole);
            }

            binaryFinal = strWholeNum + "." + strDecimalNum;
        }

        else{ //executes if given is a whole number
            strWholeNum = str;
            int nWhole = Integer.parseInt(strWholeNum);
            strWholeNum = getBinaryWholeNum(nWhole);
            binaryFinal = strWholeNum;
        }

        return binaryFinal;
    }

    public String getBinaryWholeNum(int nWhole){
        String strTemp = "";
        String strBinary = "";

        //computes for the binary (but in reverse)
        while(nWhole > 0){
            strTemp = strTemp + (nWhole % 2);
            nWhole /= 2;
        }

        //switches the places of the bits since the arrangement of the binary is in reverse
        for(int i = strTemp.length()-1; i >= 0; i--){
            strBinary = strBinary + strTemp.charAt(i);
        }

        return strBinary;
    }

    public String getBinaryFractionPart(int nDecimal){
        String strBinary = "";
        String strFinal = "";

        //gets the length of the decimal
        String strTemp = nDecimal + "";
        int nLength = strTemp.length();

        //gets the number of decimal places and turns it into base 10; to be used in the solving later
        Double numOfDecPlaces = Math.pow(10, nLength);
        Double dDecimal = nDecimal / numOfDecPlaces;

        //decimal is converted to binary
        int nBinary = 0;

        //decimal is converted to binary
        int i = 0;
        while(dDecimal > 0 && i < 20){
            dDecimal *= 2.0;
            nBinary = dDecimal.intValue();
            strBinary = strBinary + nBinary;
            dDecimal -= nBinary;
            i++;
        }

        return strFinal;
    }

    public int[] getAns() {
        return this.ans;
    }

    public int getExp() {
        return this.exponent;
    }
}