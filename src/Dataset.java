import processing.data.Table;
import processing.data.TableRow;

public class Dataset implements Searchable, Sortable {
    private final Record[] records;
    private final NormalRecord[] normalRecords;

    public Dataset() {
        DataVisualizationApp app = DataVisualizationApp.getApp();
        Table table = app.loadTable("data/heart_2.csv", "header");
        records = new Record[table.getRowCount()];
        for (int i = 0; i < records.length; i++) {
            TableRow row = table.getRow(i);
            int number = row.getInt("number");
            int age = row.getInt("age");
            int sex = row.getInt("sex");
            int chol = row.getInt("chol");
            int maxHeartRate = row.getInt("thalach");
            int exang = row.getInt("exang");
            int thal = row.getInt("thal");
            int target = row.getInt("target");
            records[i] = new Record(number, age, sex, chol, maxHeartRate, exang, thal, target);
        }
        normalRecords = new NormalRecord[10];
        for (int i=0; i<normalRecords.length; i++){
            normalRecords[i] = new NormalRecord(i);
        }
    }

    public Record[] getRecords() {
        return records;
    }

    public NormalRecord[] getNormalRecords() {
        return normalRecords;
    }

    @Override
    public void sort() {
        SortingMethods.sort(records);
    }

    @Override
    public int find(int cholesterol, int startingPoint) {
        return SearchingMethods.search(records, cholesterol, startingPoint);
    }
}
