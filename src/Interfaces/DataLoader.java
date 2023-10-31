package Interfaces;

import Main.Student;
import java.util.List;

public interface DataLoader{

    public List<Student> getData();

    public void readDataFromFile(String str);
}
