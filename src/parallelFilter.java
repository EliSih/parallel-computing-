
import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class parallelFilter  extends RecursiveAction {

    int low; // arguments
    int high;
    int filterSize;
    String[] fileInput;
    ArrayList<String> filtered;
    static final int SEQUENTIAL_CUTOFF = 100;

    parallelFilter(){}
 
    parallelFilter(String [] fileInput, int low, int high, int filterSize, ArrayList<String >filtered){
        this.low = low;
        this.high = high; // line count or array size
        this.filterSize = filterSize;
        this.fileInput = fileInput;
        this.filtered = filtered;
    }

    protected void compute(){
        int lo = 0;
        int hi = filterSize;
        String data;
        String sortedWindow;
        String medianSet = "";
        String fDataItem; //filtered dataItem;
        int medianPos = tool.medianIndex(filterSize);
        if((high - low) < SEQUENTIAL_CUTOFF){
            for(int i = low; i<high;i++){
                data = fileInput[i];
                while( !(hi > data.length() )){
                    sortedWindow = tool.sort(data.substring(lo, hi));
                    medianSet += tool.median(medianPos, sortedWindow); // adding one median per iteration to the medianSet
                    lo++;
                    hi++;
                }
                fDataItem = tool.filteredSetCompletion(medianSet, data,data.length());
                filtered.add(fDataItem);
                lo = 0;
                hi = filterSize;
                medianSet = "";
            }
            return;
        }
        else{
            parallelFilter left = new parallelFilter(fileInput, low, (high+low)/2, filterSize, filtered);
            parallelFilter right = new parallelFilter(fileInput, (high+low)/2,high,filterSize, filtered);
            left.fork();
            right.compute();
            left.join();
        }

    }

}

