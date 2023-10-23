import java.util.ArrayList;
import java.util.HashMap;

public class ClassroomDataGroups {
    private ArrayList<Student>[] studentsGroupMap;
    public ClassroomDataGroups(){
        studentsGroupMap = new ArrayList[12];
        for(int i = 0; i < 12; i++){
            studentsGroupMap[i] = new ArrayList();
        }
    }
    public void addStudent(Student s){
        studentsGroupMap[s.getGroup() - 1].add(s);
    }

    public ArrayList<Student> getStudentsByGroup(int group){
        return studentsGroupMap[group - 1];
    }
}
