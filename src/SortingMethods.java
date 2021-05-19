public class SortingMethods {

    public SortingMethods() {
    }

    public static void sort(Record[] records){
        //SortingMethods.selectionSort(records);
        SortingMethods.insertionSort(records);
        //SortingMethods.mergeSort(records, 0, records.length-1);
    }

    private static void selectionSort(Record[] records) {
        for (int i=0; i<records.length; i++) {
            int smallestValIndex = i;
            for (int j = i + 1; j < records.length; j++) {
                if (records[j].getChol() < records[smallestValIndex].getChol()) {
                    smallestValIndex = j;
                }
            }
            Record temp = records[i];
            records[i] = records[smallestValIndex];
            records[smallestValIndex] = temp;
        }
    }

    private static void insertionSort(Record[] records) {
        for (int i=1; i<records.length; i++){
            Record currRecord = records[i];
            int key = records[i].getChol();
            int j=i-1;
            while (j >= 0 && records[j].getChol() > key){
                records[j+1] = records[j];
                j = j-1;
            }
            records[j+1] = currRecord;
        }
    }

    private static void mergeSort(Record[] records, int start, int end) {
        int mid = (start+end)/2;
        if (end - start < 2){
            return;
        }
        mergeSort(records, start, mid);
        mergeSort(records, mid, end);
        merge(records, start, mid, end);
    }

    private static void merge(Record[] records, int start, int mid, int end){
        Record[] leftArray = new Record[mid-start+1];
        Record[] rightArray = new Record[end-mid];

        for (int i=0; i<leftArray.length; i++){
            leftArray[i] = records[start+i];
        }
        for (int i=0; i<rightArray.length; i++){
            rightArray[i] = records[mid+i+1];
        }

        int leftIndex = 0;
        int rightIndex = 0;

        for (int i=start; i<end+1; i++){
            if (leftIndex < leftArray.length && rightIndex < rightArray.length){
                if(leftArray[leftIndex].getChol() < rightArray[rightIndex].getChol()) {
                    records[i] = leftArray[leftIndex];
                    leftIndex++;
                } else{
                    records[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length){
                records[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length){
                records[i] = rightArray[rightIndex];
                rightIndex++;
            }

        }
    }
}
