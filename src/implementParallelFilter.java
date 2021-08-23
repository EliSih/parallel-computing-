import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class implementParallelFilter {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        String[] fileInput;
        try {
            Scanner read = new Scanner(new File("/home/elisih/CSC2002/Assignment1/sampleInput100.txt"));
            int lineCount = Integer.parseInt(read.nextLine());
            fileInput = new String[lineCount + 1];
            int i = 0;
            String unfiltered;
            int beginSlice;
            while(read.hasNext()){
                unfiltered = read.nextLine();
                beginSlice = unfiltered.indexOf(" ") + 1;
                fileInput[i] = unfiltered.substring(beginSlice).replace(",", "");
                i++;
            }

            ArrayList<String> filtered = new ArrayList<>();
            parallelFilter parallelFilter = new parallelFilter(fileInput, 0, lineCount-1, 5, filtered);
            pool.invoke(parallelFilter);
//
            FileWriter fileWriter = new FileWriter("filtered.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(lineCount + "\n");
            int b = 1;
            for(String c: filtered){
                System.out.println(b + " " + c);
                b++;
//              bufferedWriter.write(b + " " + c);
            }

        }
        catch (FileNotFoundException e){
            System.out.println("ran into exception!");
        }
        catch (IOException e){
            System.out.println("ran into IO exception!");
        }
    }
}
