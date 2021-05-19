import processing.core.PApplet;

public class DataVisualizationApp extends PApplet {
    private static DataVisualizationApp app;
    private Dataset dataset;
    private int foundAt;

    public static void main(String[] args){
        app = new DataVisualizationApp();
        app.runSketch();
    }

    public DataVisualizationApp(){
        foundAt = -1;
    }

    public void settings(){
        size(1000, 1000);
    }

    public void setup(){
        dataset = new Dataset();
        fill(0);
    }

    public void draw(){
        background(255);
        displayRecords();
    }

    public void keyPressed(){
        if (key == 'e'){
            foundAt = dataset.find(233);
            System.out.println(foundAt);
        } else if (key == 'o'){
            dataset.sort();
        }
    }

    public static DataVisualizationApp getApp(){
        return app;
    }

    private void displayRecords(){
        Record[] records = dataset.getRecords();
        text("NUMBER", 100, 25);
        text("AGE", 200, 25);
        text("SEX", 300, 25);
        text("CHOL", 400, 25);
        text("MAX HEART RATE", 500, 25);
        text("EXERCISED INDUCED ANGINA", 620, 25);
        text("THALASSEMIA", 800, 25);
        text("TARGET", 900, 25);


        int y = 75;
        for (int i = 0; i < records.length; i++){
            Record record = records[i];

            if (foundAt == i){
                fill(255, 0, 0);
            }
            text(record.getNumber(), 50, y);

            fill(0);
            text(record.getAge(), 200, y);
            text(record.getSex(), 300, y);
            text(record.getChol(), 400, y);
            text(record.getMaxHeartRate(), 500, y);
            text(record.getExang(), 620, y);
            text(record.getThal(), 800, y);
            text(record.getTarget(), 900, y);
            y += 50;
        }
    }
}
