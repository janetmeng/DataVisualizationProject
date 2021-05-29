public class SearchingMethods {

    public SearchingMethods(){
    }

    public static int search(Record[] records, int cholesterol, int startingPoint){
        return SearchingMethods.linearSearch(records, cholesterol, startingPoint);
        //return SearchingMethods.binarySearch(records, 0, records.length-1, cholesterol);
    }

    private static int linearSearch(Record[] records, int cholesterol, int startingPoint){
        for (int i=startingPoint; i<records.length; i++) {
            if (records[i].getChol() == cholesterol) {
                return i;
            }
        }
        return -1;
    }

    private static int binarySearch(Record[] records, int low, int high, int cholesterol) {
        if (low <= high) {
            int mid = (low + high)/2;
            if (records[mid].getChol() == cholesterol){
                return mid;
            } else if (records[mid].getChol() < cholesterol){
                return binarySearch(records, mid+1, high, cholesterol);
            } else{
                return binarySearch(records, low, mid-1, cholesterol);
            }
        }
        return -1;
    }
}
