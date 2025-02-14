package exercise1;

public class Light implements Device {

    private int flag=0;
    private String name;

    public Light() {
    }

    public Light(String name) {
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
            System.out.println(name +" Turned On");
        }else{
            System.out.println( name +" is Already On");
        }
    }

    @Override
    public void turnOff() {
        if(flag==1){
            flag=0;
            System.out.println(name+ " Turned Off");
        }else{
            System.out.println(name + " is Already Off");
        }
    }


}


