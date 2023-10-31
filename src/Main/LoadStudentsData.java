package Main;

import Interfaces.DataLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadStudentsData implements DataLoader {
    private List<Student> studentsList = null;

    public List<Student> getData() {
        return studentsList;
    }

    public LoadStudentsData(String filePath){
        studentsList = new ArrayList<Student>();
        readDataFromFile(filePath);
    }

    public void readDataFromFile(String filePath){
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
