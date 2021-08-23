import java.io.*;
import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class sequentialFilter {
    // sequential median filter implementation
    static long startTime = 0;
    public static void main(String[] args){

        String[] cmdInput = args[0].split(" "); // command line input
        int fSize = Integer.parseInt(cmdInput[1]);


        try {
            Scanner read = new Scanner(new File(cmdInput[0]));
            int lineCount = Integer.parseInt(read.nextLine());

            FileWriter writer = new FileWriter(cmdInput[2], true);
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

            float duration = 0;

            sequentialFilter.tick(); //start timer
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
                bufferedWriter.write(i + " " + filteredData + "\n");
                System.out.println(i + " " + filteredData);
                low = 0;
                high = fSize;
                medianSet = "";
            }
            duration = sequentialFilter.tock(lineCount);

            System.out.println("data items:" + lineCount);
            System.out.println("FilterSize-duration");
            System.out.println(fSize + " " + duration);



        }
        catch (Exception e){
            System.out.println("ran into exception");
        }
    }
    public static void tick(){
        startTime = System.nanoTime();
    }

    public static float tock(int noDataItems) {
        return (System.nanoTime() - startTime) / noDataItems;
    }
}

