public class Converter{
    
    private static String hexOutput = "";
    private static String binaryOutput = "";

    public Converter(int[] mantissa, int expInput, Boolean signMantissa){
        convertToBinary64(mantissa, expInput, signMantissa);
    }

    public void convertToBinary64(int[] mantissa, int expInput, Boolean signMantissa){
        int[] binaryOutput = new int[28];
        int[] midBinary = new int[11];
        int i = 0, x = 0;

        midBinary = solveMidBinary(expInput); // solves the binary in the middle

        // sets the value of the first bit here
        if(signMantissa)
            binaryOutput[0] = 0;
        else
            binaryOutput[0] = 1;

        // sets the binary value of the middle part
        for(i = 1; i <= 11; i++){
            binaryOutput[i] = midBinary[x];
            x++;
        }

        // sets the binary value of the decimal/last part
        x = 1;
        while(x < mantissa.length){
            binaryOutput[i] = mantissa[x];
            i++;
            x++;
        }

        // ensures that the binary has a total of 28 bits
        while(i < 28){
            binaryOutput[i] = 0;
            i++;
        }

        convertToHex(binaryOutput);
        binaryToString(binaryOutput);
    }

    public int[] solveMidBinary(int expInput){
        int[] tempBinary = new int[11]; // temporarily stores the binary
        int[] midBinary = new int[11]; // final binary is stored here
        int e1 = 0;
        int temp = 0;
        int i = 0;
        int x = 0;

        // decimal value is computed here
        e1 = expInput + 1023;
        temp = e1;

        // gets the length of the binary
        while(temp > 0){
            temp /= 2;
            i++;
        }

        // converts the decimal to binary
        while(e1 > 0 && x < 11){
            tempBinary[x] = e1 % 2;
            e1 /= 2;
            x++;
        }

        //makes sure that the binary has 11 bits
        x = 10;
        for(i = 0; i < 11; i++){
            midBinary[i] = tempBinary[x];
            x--;
        }

        return midBinary;
    }

    public void binaryToString(int[] intBinary){
        String strBinary = "";
        int i = 0;

        while(i < intBinary.length){
            if(i == 1)
                strBinary += " ";
            if(i == 12)
                strBinary += " ";

            strBinary += intBinary[i];
            i++;
        }

        setBinaryOutput(strBinary);
    }

    public void convertToHex(int[] convertedBin){
        String finalHexVal = "";
        int binLength;
        int pos;
        int count = 0;
        int decVal = 0;

        binLength = convertedBin.length;


        for (pos = 0; pos < binLength; pos++){
            int temp;

            temp = convertedBin[pos];

            switch (count) {
                case 0: {
                    if (temp == 1)
                        decVal += 8;
                    count++;
                    break;
                }
                case 1: {
                    if (temp == 1)
                        decVal += 4;
                    count++;
                    break;
                }
                case 2: {
                    if (temp == 1)
                        decVal += 2;
                    count++;
                    break;
                }
                case 3: {
                    if (temp == 1)
                        decVal += 1;
                    if (decVal > 9)
                        finalHexVal = finalHexVal.concat("" + getHexVal(decVal));
                    else
                        finalHexVal = finalHexVal + decVal;
                    count = 0;
                    decVal = 0;
                    break;
                }
            }
        }
		
		// sets the trailing 0's to reach 16 bits
		for(int i = 0; i < 16; i++){
            if(i >= finalHexVal.length()){
                finalHexVal = finalHexVal + "0";
            }
        }

        setHexOutput(finalHexVal);
    }

    public char getHexVal(int decVal){
        switch (decVal) {
            case 10: return 'A';
            case 11: return 'B';
            case 12: return 'C';
            case 13: return 'D';
            case 14: return 'E';
            case 15: return 'F';
            default: return ' ';
        }
    }

    public String getHexOutput(){ return this.hexOutput; }
    public void setHexOutput(String hexVal){ this.hexOutput = hexVal; }

    public String getBinaryOutput(){ return this.binaryOutput; }
    public void setBinaryOutput(String binaryVal){ this.binaryOutput = binaryVal; }

}