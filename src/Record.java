public class Record {
    private int number;
    private int age;
    private int sex;
    private int chol;
    private int maxHeartRate;
    private int exang;
    private int thal;
    private int target;

    public Record(){
    }

    public Record(int number, int age, int sex, int chol, int maxHeartRate, int exang, int thal, int target){
        this.number = number;
        this.age = age;
        this.sex = sex;
        this.chol = chol;
        this.maxHeartRate = maxHeartRate;
        this.exang = exang;
        this.thal = thal;
        this.target = target;
    }

    public int getNumber(){
        return number;
    }

    public int getAge(){
        return age;
    }

    public int getSex() {
        return sex;
    }

    public int getChol() {
        return chol;
    }

    public int getMaxHeartRate() {
        return maxHeartRate;
    }

    public int getExang() {
        return exang;
    }

    public int getThal() {
        return thal;
    }

    public int getTarget() {
        return target;
    }

    public void setRecord(int number, int age, int sex, int chol, int maxHeartRate, int exang, int thal, int target){
        this.number = number;
        this.age = age;
        this.sex = sex;
        this.chol = chol;
        this.maxHeartRate = maxHeartRate;
        this.exang = exang;
        this.thal = thal;
        this.target = target;
    }
}