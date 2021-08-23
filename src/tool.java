import java.util.Arrays;

public class tool {

    long startTime;

//    public static void strip(String rawData){}

    /**
     * This method calculates the position index of the median value in set
     * @param fSize :filter
     * @return median index  or the index of the value adjacent to the median
     * in the case of filters with an even size (value just after median)
     * */
    public static int medianIndex(int fSize){
        double posInFil = fSize/2; // position in filter
        int midIdx = (int)(posInFil); // the decimal will be truncated
        return midIdx;
    }
    /**
     *@param midInd : the position of the median in the filter window
     * @param dataSet : unfiltered set
     * @return the median in the filter window
     * */
    public static String median(int midInd, String dataSet){
        String median;
        median = dataSet.substring(midInd, midInd+1);
        return median;
    }
    /**
     * @param dataSet: unfiltered set and unsorted
     * @return sorted set
     * */
    public static String sort(String  dataSet){

        String[] StringSet = dataSet.split("");
        Arrays.sort(StringSet);
        String sorted = "";
        for(String e : StringSet)
            sorted += e;
        return sorted;

    }
    public static String filteredSetCompletion(String medianSet, String  unfilteredData, int setSize){

        int mSetSize = medianSet.length();
        int Bounds = (setSize - mSetSize)/2;
        String upperBoundDigits = "";
        String lowerBoundDigits = "";
        for(int i=0; i<Bounds; i++){
            lowerBoundDigits += unfilteredData.substring(i, i+1);
            upperBoundDigits += unfilteredData.charAt(Bounds + mSetSize + i);
        }
        return  lowerBoundDigits + medianSet + upperBoundDigits;

    }
}
