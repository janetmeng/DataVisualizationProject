public class NormalRecord extends Record{
    private int strokeWeight;

    public NormalRecord(int number){
        super();
        int age = (int) (Math.random() * 36) + 35;
        int sex = (int) (Math.random() * 2);
        int chol = (int) (Math.random() * 76) + 125;
        int maxHeartRate = (int) (Math.random() * 36) + 150;
        int exang = (int) (Math.random()*100.0);
        if (sex == 0) {
            if (exang <= 6.7) {
                strokeWeight = 3;
            }
        } else {
            if (exang <= 5.7){
                strokeWeight = 3;
            }
        }
        int thal = 0;
        int target = 0;
        setRecord(number, age, sex, chol, maxHeartRate, exang, thal, target); // pass in ^^ those generated numbers
    }

    public int getStrokeWeight(){
        return strokeWeight;
    }

}
