import java.io.*;
import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class sequentialFilter {
    // sequential median filter implementation

    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Unfiltered Data");
        String data = keyboard.nextLine().replace(",","");
        data = data.substring(2);
        System.out.println("Enter filter size");
        int fSize = keyboard.nextInt();
        int dataSize = data.length();
        String filteredData;
        String medianSet ="";
        int low = 0;
        int high = fSize ;
        int counter = 0;
        int lineCount = 0;
        Scanner read = null;

        // calculating median index
        try {
            read = new Scanner(new File("sampleInput100.txt"));
            lineCount = read.nextInt();

            File file = new File("/home/elisih/CSC2002/Assignment1CSC2002S/filtered.txt");
            FileWriter writer = new FileWriter(file, true);


        }
        catch (Exception e){

        }


        String sortedWindow = "";
        int medianPos = 0;
        medianPos = tool.medianIndex(fSize);

        while( !(high > dataSize )){

            sortedWindow = tool.sort(data.substring(low, high));

            medianSet += tool.median(medianPos, sortedWindow); // adding one median per iteration to the medianSet
            low++;
            high++;

        }
        String completeSet = tool.filteredSetCompletion(medianSet, data,dataSize);
        System.out.print(completeSet);
    }

}
