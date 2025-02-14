package exercise1;

public class AC implements Device {
    private int flag=0;
    private String name;
//    private int temp=16;

    public AC() {
    }

    public AC(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getTemp() {
//        return temp;
//    }

    public int getFlag() {
        return flag;
    }



    @Override
    public void turnOn() {
        if(flag ==0){
            flag=1;
            System.out.println(name+" Turned On");
        }else{
            System.out.println(name+ " is Already On");
        }
    }

    @Override
    public void turnOff() {
        if(flag==1){
            flag=0;
            System.out.println(name+" Turned Off");
        }else{
            System.out.println(name+" is Already Off");
        }
    }

//    public void SetTemp(int temp){
//        if(flag==1 && temp>=16 && temp<=30){
//            this.temp=temp;
//            System.out.println("Temperature Changed to "+ temp);
//        }else if(flag==0){
//            System.out.println("exercise1.AC is Off");
//        }
//        else{
//            System.out.println("Enter a Valid Speed between 16 to 30");
//        }
//    }

}
