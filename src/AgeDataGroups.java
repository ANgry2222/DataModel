import java.util.ArrayList;

public class AgeDataGroups {
    static final int maxAge = 19;
    private ArrayList<Student>[] studentsAgeMap;
    public AgeDataGroups(){
        studentsAgeMap = new ArrayList[maxAge];
        for(int i = 0; i < maxAge; i++){
            studentsAgeMap[i] = new ArrayList();
        }
    }

    public ArrayList<Student> getStudentsByAge(int age){
        return studentsAgeMap[age - 1];
    }

    public void addStudent(Student s){
        studentsAgeMap[s.getAge() - 1].add(s);
    }
}
