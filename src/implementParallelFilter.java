import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class implementParallelFilter {
    static long startTime = 0;

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        String[] fileInput;
        try {
            Scanner read = new Scanner(new File(args[0]));
            int lineCount = Integer.parseInt(read.nextLine());
            fileInput = new String[lineCount + 1];
            int i = 0;
            String unfiltered;
            int beginSlice;
            float duration = 0; // time taken to run parallel algorithm
            while(read.hasNext()){
                unfiltered = read.nextLine();
                beginSlice = unfiltered.indexOf(" ") + 1;
                fileInput[i] = unfiltered.substring(beginSlice).replace(",", "");
                i++;
            }

            ArrayList<String> filtered = new ArrayList<>();
            int filterSize = Integer.parseInt(args[1]);
            tick(); //start timer
            parallelFilter parallelFilter = new parallelFilter(fileInput, 0, lineCount-1, filterSize, filtered);
            pool.invoke(parallelFilter);
            duration = tock(lineCount);
//
            FileWriter fileWriter = new FileWriter(args[2], true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(lineCount + "\n");
            int b = 1;
            for(String c: filtered){
                System.out.println(b + " " + c);
                b++;
              bufferedWriter.write(b + " " + c);
            }

            System.out.println("data items:" + lineCount);
            System.out.println("FilterSize-duration");
            System.out.println(filterSize + " " + duration);


        }
        catch (FileNotFoundException e){
            System.out.println("ran into exception!");
        }
        catch (IOException e){
            System.out.println("ran into IO exception!");
        }


    }
    public static void tick(){
        startTime = System.nanoTime();
    }

    public static float tock(int noDataItems){
        return (System.nanoTime() - startTime)/noDataItems;
    }

}
