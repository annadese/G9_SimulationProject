import java.io.FileWriter;   
import java.io.IOException;  

public class WriteToFile {

    private String out1;
    private String out2;


    public WriteToFile(
        String out1,
        String out2            
    ) {

        this.out1 = out1;
        this.out2 = out2;

        try {
        FileWriter myWriter = new FileWriter("bin64converter.txt");
        myWriter.write(out1);
        myWriter.write("\n");
        myWriter.write(out2);
        myWriter.close();
        System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }
}