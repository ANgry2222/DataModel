import java.util.ArrayList;

public class NameDataGroups {
    static final int lettersCount = 33;
    private ArrayList<Student>[] studentsNameMap;
    public NameDataGroups(){
        studentsNameMap = new ArrayList[lettersCount];
        for(int i = 0; i < lettersCount; i++){
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
