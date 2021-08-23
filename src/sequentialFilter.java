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
        System.out.println("Enter filter size");
        int fSize = keyboard.nextInt();

        // calculating median index
        try {
            Scanner read = new Scanner(new File("/home/elisih/CSC2002/Assignment1/sampleInput100.txt"));
            int lineCount = Integer.parseInt(read.nextLine());

            FileWriter writer = new FileWriter("filtered.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            String unfiltered = "";
            String sortedWindow = "";
            int medianPos = 0;
            String  filteredData;
            int sliceFrom;

            int low = 0;
            int high = fSize ;
            String medianSet ="";

            medianPos = tool.medianIndex(fSize);
            for(int i = 0; i<lineCount;i++) {
                unfiltered = read.nextLine().replace(",","");
                sliceFrom = unfiltered.indexOf(" ") + 1;
                unfiltered = unfiltered.substring(sliceFrom);

                while (!(high > unfiltered.length())) {

                    sortedWindow = tool.sort(unfiltered.substring(low, high));

                    medianSet += tool.median(medianPos, sortedWindow); // adding one median per iteration to the medianSet
                    low++;
                    high++;

                }

                filteredData = tool.filteredSetCompletion(medianSet, unfiltered,unfiltered.length());
//                bufferedWriter.write(i + " " + filteredData + "\n");
                System.out.println(i + " " + filteredData);
                low = 0;
                high = fSize;
                medianSet = "";
            }



        }
        catch (Exception e){
            System.out.println("ran into exception");
        }




    }



}

