package org.example;

import Interfaces.DataLoader;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadStudentsData implements DataLoader {
    private List<Student> studentsList = null;

    public List<Student> getData() {
        return studentsList;
    }

    public LoadStudentsData(String filePath){
        studentsList = new ArrayList<Student>();
        try {
            readDataFromFile(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void readDataFromFile(String filePath) throws FileNotFoundException{
        CSVReader reader = new CSVReader(new FileReader(filePath));
        List<String[]> myEntries;
        try {
            myEntries = reader.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        myEntries.remove(0); //Пропустить заголовок
        for (String[] s: myEntries) {
            studentsList.add(new Student(s[0]));
        }
    }
}
