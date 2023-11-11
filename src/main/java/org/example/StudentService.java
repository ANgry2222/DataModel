package org.example;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    ArrayList<ArrayList<Student>> studentsMap;

    public int getMapSize(){
        return studentsMap.size();
    }

    public StudentService(DataGroup dg, LoadStudentsData loadedData){
        studentsMap = new ArrayList<>(1);
        fillStudentsMap(dg, loadedData.getData());
    }

    private void fillStudentsMap(DataGroup dg, List<Student> studentList){
        for (Student s: studentList) {
            if(dg.getKey(s) >= studentsMap.size()){
                while (dg.getKey(s) >= studentsMap.size()){
                    studentsMap.add(new ArrayList<>());
                }
            }
            studentsMap.get(dg.getKey(s)).add(s);
        }
    }

    public ArrayList<Student> getStudentsByKey(int key){
        return studentsMap.get(key);
    }
}
