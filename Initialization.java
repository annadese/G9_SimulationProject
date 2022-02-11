import java.util.*;

public class Initialization {

    int exponent;
    String mantissa;
    String whole;
    String fraction;
    boolean onlyZero;   // if whole part is 0 only
    boolean bFraction = false;
    int[] ans;
    boolean signBit = true;

    Initialization (String sMantissa, String sExponent, int base) {
        this.mantissa = sMantissa;
        this.exponent = Integer.parseInt(sExponent);
        this.onlyZero = false;
        boolean check = false;
        char c;
        
        if (mantissa.charAt(0) == '-') {
            String temp = mantissa;
            mantissa = "";
            signBit = false;

            for (int i = 1; i < temp.length(); i++) {
                mantissa += temp.charAt(i);
            }
        }

        mantissa = Double.toString(Double.parseDouble(mantissa));

        if (base == 10) {
            mantissa =  decimalToBinary(mantissa);
        }

        for(int i = 0; i < mantissa.length(); i++) {
            c = mantissa.charAt(i);

            if (c == '.') {
                check = true;
            }
        }

        if (check) {
            String[] splitMantissa = String.valueOf(mantissa).split("\\.");
            this.whole = splitMantissa[0];
            this.fraction = splitMantissa[1];
        } else {
            this.whole = mantissa;
        }

        if ("0".equals(fraction)) {
            this.whole = mantissa;
            this.fraction = null;
        }

        mantissa = Double.toString(Double.parseDouble(mantissa));

        boolean norm = isNormalized();
        if (!norm) {
            ans = normalize();
        } else {
            ans = toIntArray();
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
        int[] array = new int[40];
        int x = 0;
        int decimalPlace = 0;
        boolean found = false;
        int i = 0;
        
        // if whole part is 0
        if (onlyZero == true) {
            for (i = 0; i < fraction.length(); i++) {
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
                    if (c == '1' || c == '0') {
                        array[x] = Integer.parseInt(String.valueOf(c));
                        x++;
                    }
                }
            }
        }

        int[] arrayFinal;
        // removes trailing 0's
        if(array[array.length-1] == 0) {
            i = array.length - 1;

            while (array[i] == 0) {
                i--;
            }

            arrayFinal = new int[i+1];
            int w = 0;
            while (w <= i) {
                arrayFinal[w] = array[w];
                w++;
            }
        } else {
            arrayFinal = new int[array.length - 1];
            arrayFinal = array;
        }
        
        return arrayFinal;
    }

    int[] toIntArray() {
        int[] array = new int[40];
        int i = 0;
        int z = 0;

        for (int j = 0; j < mantissa.length(); j++) {
            char c = mantissa.charAt(j);

            if(c != '.') {
                if (c == '1' || c == '0') {
                    array[z] = Integer.parseInt(String.valueOf(c));
                    z++;
                }
            }
            
        }

        int[] arrayFinal;
        // removes trailing 0's
        if(array[array.length-1] == 0) {
            //strFinal = "";
            i = array.length - 1;

            while (array[i] == 0) {
                i--;
            }

            arrayFinal = new int[i+1];
            int w = 0;
            while (w <= i) {
                arrayFinal[w] = array[w];
                w++;
            }
        } else {
            arrayFinal = new int[array.length - 1];
            arrayFinal = array;
        }
        
        return arrayFinal;
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
            if(nDecimal > 0) {
                strDecimalNum = getBinaryFractionPart(nDecimal);
            }
            if(decimalPlace != 0) { //executes if a whole number exists
                int nWhole = Integer.parseInt(strWholeNum);
                strWholeNum = getBinaryWholeNum(nWhole);
            }

            //checks if decimal is not equal to 0
            if(strDecimalNum.charAt(strDecimalNum.length()-1) != '0') {
                binaryFinal = strWholeNum + "." + strDecimalNum;
            }
            else{
                binaryFinal = strWholeNum;
            }
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
        while(dDecimal > 0 && i < 52){
            dDecimal *= 2.0;
            nBinary = dDecimal.intValue();
            strBinary = strBinary + nBinary;
            dDecimal -= nBinary;
            i++;
        }

        strFinal = strBinary;

        // removes trailing 0's
        if(strBinary.charAt(strBinary.length()-1) == '0') {
            strFinal = "";
            i = strBinary.length() - 1;

            while (strBinary.charAt(i) == '0') {
                i--;
            }

            int x = 0;
            while (x <= i) {
                strFinal += strBinary.charAt(x);
                x++;
            }
        }

        return strFinal;
    }

    public int[] getAns() {
        return ans;
    }

    public int getExp() {
        return exponent;
    }

    public boolean getSign() {
        return signBit;
    }
}