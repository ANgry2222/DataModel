import java.util.ArrayList;

public class ClassroomDataGroups {
    static final int groupsCount = 12;
    private ArrayList<Student>[] studentsGroupMap;
    public ClassroomDataGroups(){
        studentsGroupMap = new ArrayList[groupsCount];
        for(int i = 0; i < groupsCount; i++){
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
