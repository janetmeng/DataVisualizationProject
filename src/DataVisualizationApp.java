import processing.core.PApplet;

public class DataVisualizationApp extends PApplet {
    private static DataVisualizationApp app;
    private Dataset dataset;
    private int foundAt;
    private String searched = "";
    int minChol;
    int minMHR;
    int maxChol;
    int maxMHR;
    float[] regCholArr = new float[19];
    float[] regMHRArr = new float[19];
    int[] regAgeArr = new int[19];

    public static void main(String[] args){
        app = new DataVisualizationApp();
        app.runSketch();
    }

    public DataVisualizationApp(){
        foundAt = -1;
    }

    public void settings(){
        size(1500, 800);
    }

    public void setup(){
        dataset = new Dataset();
        fill(0);

        Record[] records = dataset.getRecords();
        minChol = getMin(records);
        minMHR = getMinMHR(records);
        maxChol = getMax(records);
        maxMHR = getMaxMHR(records);

        for (int i=0; i<19; i++) {
            int regChol = (int) (Math.random()*76 + 125);
            int regMHR = (int) (Math.random() * 36 + 150);
            int regAge = (int) (Math.random() * 36 + 35);
            float newRegX = map(regChol, 125, maxChol, 0, width);

            regCholArr[i] = newRegX;
            float newRegY = map(regMHR, 150, maxMHR, 0, height);
            regMHRArr[i] = newRegY;
            regAgeArr[i] = regAge;
        }
    }

    public void draw(){
        background(255);
        displayRecords();
        text("What cholesterol are you searching for? " + searched, 1150, 200);
    }

    public void keyPressed(){
        if (key == 'e'){ //search
            foundAt = dataset.find(Integer.parseInt(searched));
        } else if (key == 'o'){ //sort
            dataset.sort();
        } else if (key == '1' || key == '2' || key == '3' || key == '4' || key == '5' || key == '6' || key == '7' || key == '8' || key == '9' || key == '0'){
            searched = searched + key;
        } else if (key == 'd'){
            if(searched.length() > 0) {
                searched = searched.substring(0, searched.length() - 1);
            }
        } else if (key == 'a') { //animation, sorting by age display
            animation();
            int startSeconds = second();
        }
    }

    public static DataVisualizationApp getApp(){
        return app;
    }

    private void displayRecords(){ //displays all records
        Record[] records = dataset.getRecords();
       //boolean labelDrawn = false;

        for (int i=0; i<records.length; i++){
            // people with heart disease's data visualization
            int chol = records[i].getChol();
            // the map function takes our value and maps it to a value that fits on our canvas
            float newX = map(chol, minChol, maxChol, 0, width);

            int maxHeartRate = records[i].getMaxHeartRate();
            float newY = map(maxHeartRate, minMHR, maxMHR, 0, height);

            if (i==foundAt){
                strokeWeight(5);
                stroke(255,255,0); //yellow
            } else{
                strokeWeight(0);
                stroke(0); //black
            }
            smooth();
            // determines color & hue of heart
            if (records[i].getSex() == 1) { //male
                if (records[i].getThal() == 0){
                    fill(133, 235, 255, 0); //regular
                } else if (records[i].getThal() == 1){
                    fill(133, 235, 255, 100);
                } else if (records[i].getThal() == 2){
                    fill(133, 235, 255, 200);
                }
            } else{ //female
                if(records[i].getThal() == 0){
                    fill(255, 153, 204,0);
                }else if (records[i].getThal() == 1){
                    fill(255, 153, 204, 100);
                } else if (records[i].getThal() == 2){
                    fill(255, 153, 204, 200);
                }
            }
            int age = records[i].getAge();
            pushMatrix();
            translate(newX - 50, newY - 15);

            //start of drawing heart
            beginShape();
            if (records[i].getExang() == 1){
                stroke(20);
            }
            vertex(50, 25);
            bezierVertex(50, -5, 90+age, 5, 50, 40+age);
            vertex(50, 25);
            bezierVertex(50, -5, 10-age, 5, 50, 40+age);
            endShape();
            //end of drawing heart
            text(records[i].getChol(),0,0);
            popMatrix();

            // Here we put a name label on the plotted data when the mouse is over it
            //if(dist(mouseX,mouseY,newX,newY)<10 && !labelDrawn){
                //fill(255);
              //  text(records[i].getNumber(),mouseX-10,mouseY-10);
              //  labelDrawn = true; // only draw one label at a time
            ///}
        }

        // regular people's data visualization
        for (int i=0; i<regCholArr.length; i++) {
            if (i % 2 == 0) {
                fill(255, 153, 204);
            } else {
                fill(133, 235, 255); //male
            }
            ellipse(regCholArr[i], regMHRArr[i], regAgeArr[i] / 2, regAgeArr[i] / 2);
        }
    }

    public void animation(){
        dataset.sort();
        Record[] records = dataset.getRecords();
        boolean visible_lowest = true;
        boolean visible_middle = false;
        boolean visible_highest = false;
        int j=0;

        while (j<2) {
            for (int i = 0; i < records.length; i++) {
                int s = second();
                while (s % 5 != 0) {
                    s = second();
                }

                if (i < 6 && visible_lowest) {
                    displaySingleRecord(records[i]);
                    if (i==5){
                        visible_lowest = false;
                        visible_middle = true;
                    }
                }
                if (i < 11 && visible_middle) {
                    displaySingleRecord(records[i]);
                    if (i==10){
                        visible_middle = false;
                        visible_highest = true;
                    }
                }
                if (i < 16 && visible_highest) {
                    displaySingleRecord(records[i]);
                    if (i==15){
                        visible_highest = false;
                        visible_lowest = true;
                    }
                }
            }
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
                fill(133, 235, 255, 0); //regular
            } else if (record.getThal() == 1){
                fill(133, 235, 255, 100);
            } else if (record.getThal() == 2){
                fill(133, 235, 255, 200);
            }
        } else{ //female
            if(record.getThal() == 0){
                fill(255, 153, 204,0);
            }else if (record.getThal() == 1){
                fill(255, 153, 204, 100);
            } else if (record.getThal() == 2){
                fill(255, 153, 204, 200);
            }
        }
        int age = record.getAge();
        pushMatrix();
        translate(newX - 50, newY - 15);
        //draws heart
        beginShape();
        if (record.getExang() == 1){
            stroke(20);
        }
        vertex(50, 25);
        bezierVertex(50, -5, 90+age, 5, 50, 40+age);
        // original: bezierVertex(50, -5, 90, 5, 50, 40);
        vertex(50, 25);
        bezierVertex(50, -5, 10-age, 5, 50, 40+age);
        // original: bezierVertex(50, -5, 10, 5, 50, 40);
        endShape();
        //end of drawing heart
        popMatrix();
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

    public int getMax(Record[] records){
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
