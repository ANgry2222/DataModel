import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadStudentsData implements DataLoader{
    String filePath = null;
    private List<Student> studentsList = null;

    public List<Student> getData() {
        return studentsList;
    }

    public LoadStudentsData(String filePath){
        this.filePath = filePath;
        studentsList = new ArrayList<Student>();
        readDataFromFile();
    }

    public void readDataFromFile(){
        Scanner sc = null;

        try {
            sc = new Scanner(new File(Main.class.getResource(filePath).getFile()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        sc.useDelimiter("\n");
        sc.next();
        String buffer;
        while(sc.hasNext()){
            buffer = sc.next();
            studentsList.add(new Student(buffer));
        }
        sc.close();
    }
}
