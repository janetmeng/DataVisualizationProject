import processing.core.PApplet;

public class DataVisualizationApp extends PApplet {
    private static DataVisualizationApp app;
    private Dataset dataset;
    private int foundAt;
    private String searched = "";
    private boolean animationMode;
    private int counter;
    private int minChol;
    private int minMHR;
    private int maxChol;
    private int maxMHR;

    public static void main(String[] args){
        app = new DataVisualizationApp();
        app.runSketch();
    }

    public DataVisualizationApp(){
        foundAt = -1;
        animationMode = false;
        counter = 0;
    }

    public void settings(){
        size(1500, 800);
    }

    public void setup(){
        dataset = new Dataset();
        Record[] records = dataset.getRecords();
        minChol = getMin(records);
        minMHR = getMinMHR(records);
        maxChol = getMax(records);
        maxMHR = getMaxMHR(records);
    }

    public void draw(){
        background(255);
        strokeWeight(0);
        fill(0);
        text("What cholesterol are you searching for within the people with heart disease? " + searched, 1000, 200);
        if (animationMode){
            animation();
        } else{
            displayRecords();
        }
    }

    public void keyPressed(){
        if (key == 'e'){ //search
            foundAt = dataset.find(Integer.parseInt(searched), foundAt+1);
        } else if (key == 'o'){ //sort
            dataset.sort();
        } else if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0'){
            searched = searched + key;
        } else if (key == 'd'){
            if(searched.length() > 0) {
                searched = searched.substring(0, searched.length() - 1);
            }
        } else if (key == 'a') {
            animationMode = true;
        }
    }

    public static DataVisualizationApp getApp(){
        return app;
    }

    private void displayRecords(){ //displays all records
        Record[] records = dataset.getRecords();
        for (int i=0; i<records.length; i++){
            // people with heart disease's data visualization
            int chol = records[i].getChol();
            float newX = map(chol, minChol, maxChol, 0, width); // the map function takes our value and maps it to a value that fits on our canvas
            int maxHeartRate = records[i].getMaxHeartRate();
            float newY = map(maxHeartRate, minMHR, maxMHR, 0, height);

            if (i==foundAt){
                strokeWeight(5);
                stroke(255,255,0); //yellow
            } else{
                strokeWeight(0);
                stroke(0);
            }

            //exercise induced angina - boldface
            if (records[i].getExang() == 1){
                strokeWeight(3);
            }
            smooth();
            // determines color & hue of heart
            if (records[i].getSex() == 1) { //male
                if (records[i].getThal() == 0){
                    fill(133, 235, 255, 255); //regular
                } else if (records[i].getThal() == 1){
                    fill(133, 235, 255, 200);
                } else if (records[i].getThal() == 2){
                    fill(133, 235, 255, 130);
                } else if (records[i].getThal() == 3){
                    fill(133, 235, 255, 60);
                }
            } else{ //female
                if(records[i].getThal() == 0){
                    fill(255, 153, 204,255);
                }else if (records[i].getThal() == 1){
                    fill(255, 153, 204, 200);
                } else if (records[i].getThal() == 2){
                    fill(255, 153, 204, 130);
                } else if (records[i].getThal() == 3){
                    fill(255, 153, 204, 60);
                }

            }
            int age = records[i].getAge();
            pushMatrix();
            translate(newX - 50, newY - 15);
            //start of drawing heart
            beginShape();
            if (records[i].getExang() == 1){
                strokeWeight(3);
            }
            vertex(50, 25);
            bezierVertex(50, -5, 90+age, 5, 50, 40+age);
            vertex(50, 25);
            bezierVertex(50, -5, 10-age, 5, 50, 40+age);
            endShape();
            //end of drawing heart
            text(records[i].getChol(),0,0);
            popMatrix();
            strokeWeight(0);
            stroke(0);
        }

        // regular people's data visualization
        NormalRecord[] normalRecords = dataset.getNormalRecords();
        for (int i=0; i<normalRecords.length; i++) {
            strokeWeight(normalRecords[i].getStrokeWeight());
            int chol = normalRecords[i].getChol();
            float newX = map(chol, minChol, maxChol, 0, width); // the map function takes our value and maps it to a value that fits on our canvas
            int maxHeartRate = records[i].getMaxHeartRate();
            float newY = map(maxHeartRate, minMHR, maxMHR, 0, height);
            if (normalRecords[i].getSex() == 0){
                if(normalRecords[i].getThal() == 0){
                    fill(255, 153, 204,255); //opaque
                } else if (normalRecords[i].getThal() == 1){
                    fill(255, 153, 204, 200);
                } else if (normalRecords[i].getThal() == 2){
                    fill(255, 153, 204, 130);
                } else if (normalRecords[i].getThal() == 3){
                    fill(255, 153, 204, 60);
                }
            } else{
                if (normalRecords[i].getThal() == 0){
                    fill(133, 235, 255, 255);
                } else if (normalRecords[i].getThal() == 1){
                    fill(133, 235, 255, 200);
                } else if (normalRecords[i].getThal() == 2){
                    fill(133, 235, 255, 130);
                } else if (normalRecords[i].getThal() == 3){
                    fill(133, 235, 255, 60);
                }
            }
            text(normalRecords[i].getChol(),newX-30,newY-30);
            ellipse(newX, newY, normalRecords[i].getAge(), normalRecords[i].getAge());
            strokeWeight(0);
            stroke(0);
        }
    }

    public void animation(){
        Record[] records = dataset.getRecords();
        if(counter > 0) {
            for (int i = 0; i < records.length; i++) {
                if (records[i].getAge() > 35 && records[i].getAge() < 46) {
                    displaySingleRecord(records[i]);
                }
            }
        }
        if (counter > 200) {
            for (int i = 0; i < records.length; i++) {
                // now s is a multiple of 10
                if (records[i].getAge() > 46 && records[i].getAge() < 56) {
                    displaySingleRecord(records[i]);
                }
            }
        }
        if (counter > 400) {
            for (int i = 0; i < records.length; i++) {
                // now s is a multiple of 10
                if (records[i].getAge() > 56 && records[i].getAge() < 71) {
                    displaySingleRecord(records[i]);
                }
            }
        }
        counter++;
        if (counter>600){
            animationMode = false;
        }
    }

    public void displaySingleRecord(Record record){
        // people with heart disease's data visualization
        int chol = record.getChol();
        // the map function takes our value and maps it to a value that fits on our canvas
        float newX = map(chol, minChol, maxChol, 0, width);
        int maxHeartRate = record.getMaxHeartRate();
        float newY = map(maxHeartRate, minMHR, maxMHR, 0, height);

        smooth();
        // determines color & hue of heart
        if (record.getSex() == 1) { //male
            if (record.getThal() == 0){
                fill(133, 235, 255, 255); //regular
            } else if (record.getThal() == 1){
                fill(133, 235, 255, 200);
            } else if (record.getThal() == 2){
                fill(133, 235, 255, 130);
            } else if (record.getThal() == 3){
                fill(133, 235, 255, 60);
            }
        } else{ //female
            if(record.getThal() == 0){
                fill(255, 153, 204,255);
            }else if (record.getThal() == 1){
                fill(255, 153, 204, 200);
            } else if (record.getThal() == 2){
                fill(255, 153, 204, 130);
            } else if (record.getThal() == 3){
                fill(255, 153, 204, 60);
            }
        }
        int age = record.getAge();
        pushMatrix();
        translate(newX - 50, newY - 15);
        //draws heart
        beginShape();
        if (record.getExang() == 1){
            strokeWeight(3);
        }
        vertex(50, 25);
        bezierVertex(50, -5, 90+age, 5, 50, 40+age);
        vertex(50, 25);
        bezierVertex(50, -5, 10-age, 5, 50, 40+age);
        endShape();
        //end of drawing heart
        popMatrix();
        //reset stroke and strokeWeight
        strokeWeight(0);
        stroke(0);
    }

    public int getMin(Record[] records){
        int min = Integer.MAX_VALUE;
        for (int i=0; i<records.length; i++) {
            if (records[i].getChol()<min){
                min = records[i].getChol();
            }
        }
        return min;
    }

    public int getMax(Record[] records){ //gets maxChol of records
        int max = Integer.MIN_VALUE;
        for (int i=0; i<records.length; i++){
            if (records[i].getChol()>max){
                max = records[i].getChol();
            }
        }
        return max;
    }

    public int getMinMHR(Record[] records){
        int min = Integer.MAX_VALUE;
        for (int i=0; i<records.length; i++) {
            if (records[i].getMaxHeartRate()<min){
                min = records[i].getMaxHeartRate();
            }
        }
        return min;
    }

    public int getMaxMHR(Record[] records){
        int max = Integer.MIN_VALUE;
        for (int i=0; i<records.length; i++){
            if (records[i].getMaxHeartRate()>max){
                max = records[i].getMaxHeartRate();
            }
        }
        return max;
    }
}
