package Interfaces;

import org.example.Student;

import java.io.FileNotFoundException;
import java.util.List;

public interface DataLoader{

    public List<Student> getData();

    public void readDataFromFile(String str) throws FileNotFoundException;
}
