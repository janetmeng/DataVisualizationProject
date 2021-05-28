public class NormalRecord extends Record{
    private int regNumber;
    private int regAge;
    private int regSex;
    private int regChol;
    private int regMHR;
    private int regExang;
    private int regThal;
    private int regTarget;
    private int strokeWeight;

    public NormalRecord(){
        super();
        regNumber = 25;
        regAge = (int) (Math.random() * 36) + 35;
        regSex = (int) (Math.random() * 2);
        regChol = (int) (Math.random() * 76) + 125;
        regMHR = (int) (Math.random() * 36) + 150;
        regExang = (int) (Math.random()*100.0);
        if (regSex == 0) {
            if (regExang <= 6.7) {
                strokeWeight = 5;
            }
        } else {
            if (regExang <= 5.7){
                strokeWeight = 5;
            }
        }
        regThal = 0;
        regTarget = 0;

        setRecord(regNumber, regAge, regSex, regChol, regMHR, regExang, regThal, regTarget); // pass in ^^ those generated numbers

    }

}
