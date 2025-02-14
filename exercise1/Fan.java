package exercise1;

public class Fan implements Device {

    private int flag=0;
    private String name;
//    private int speed=0;

    public Fan() {
    }

    public Fan(String name) {
        this.name = name;
    }

    public int getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void turnOn() {
        if(flag ==0){
            flag=1;
            System.out.println(name+ "Turned On");
        }else{
            System.out.println(name+" is Already On");
        }
    }

    @Override
    public void turnOff() {
//        speed=1;
        if(flag==1){
            flag=0;
            System.out.println(name+" Turned Off");
        }else{
            System.out.println(name+" is Already Off");
        }
    }

//    public void setSpeed(int speed){
//        if(flag==1 && speed>=0 && speed<=5){
//            this.speed=speed;
//            System.out.println(name +"Speed Changed to "+ speed);
//        }else if (flag==0){
//            System.out.println("exercise1.Fan is Off");
//        }else{
//            System.out.println("Enter a Valid Speed between 0 to 5 for" +name);
//        }
//    }
}
