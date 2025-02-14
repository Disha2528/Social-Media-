package exercise1;

import java.util.HashMap;

public class SmartHome {
    public static void main(String[] args) {

        HashMap<String, Device> hash= new HashMap<>();
        hash.put("f1",new Fan("f1"));
        hash.put("f2",new Fan("f2"));
        hash.put("AC1", new AC("AC1"));
        hash.put("AC2", new AC("AC2"));
        hash.put("l1",new Light("l1"));
        hash.put("l2",new Light("l2"));


        hash.get("f1").turnOn();
        hash.get("f1").turnOff();
        hash.get("f2").turnOff();
        hash.get("f2").turnOn();
        hash.get("f2").turnOn();

        hash.get("l1").turnOn();
        hash.get("l1").turnOff();
        hash.get("l2").turnOff();
        hash.get("l2").turnOn();
        hash.get("l2").turnOn();

        hash.get("AC1").turnOn();
        hash.get("AC1").turnOff();
        hash.get("AC2").turnOff();
        hash.get("AC2").turnOn();
        hash.get("AC2").turnOn();








    }
}
