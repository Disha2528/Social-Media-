package exercise3.Q2;

import java.util.Comparator;

public class MarksCompare implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2){
        return Integer.compare(s1.getMarks(),s2.getMarks());
    }
}
