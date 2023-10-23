import java.util.ArrayList;

public class NameDataGroups {

    private ArrayList<Student>[] studentsNameMap;
    public NameDataGroups(){
        studentsNameMap = new ArrayList[33];
        for(int i = 0; i < 33; i++){
            studentsNameMap[i] = new ArrayList();
        }
    }

    public ArrayList<Student> getStudentsByName(int firstLetter){
        return studentsNameMap[firstLetter];
    }

    public void addStudent(Student s){
        studentsNameMap[s.getFamilyFirstLetter() - 'а'].add(s);
    }
}
