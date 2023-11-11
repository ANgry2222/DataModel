package org.example;

import java.util.ArrayList;
import java.util.List;

public class Student {
    public static ArrayList<String> subjectsList = new ArrayList<>(List.of("physics", "mathematics", "rus", "literature", "geometry", "informatics"));
    static final int subjectsCount = 6;
    private String family;
    private String name;
    private int age;
    private int group; //Класс ученика
    private int[] marks; //Список оценок

    public int getAge() {
        return age;
    }

    public int getGroup() {
        return group;
    }

    public String getFullName(){
        return family + " " + name;
    }

    public float getAverageMark(){
        int result = 0;
        for (int s: marks) {
            result += s;
        }
        return (float) result / subjectsCount;
    }

    public Student(String studentString){
        marks = new int[subjectsCount];
        String[] studentStringArray = studentString.split(";");
        this.family = studentStringArray[0];
        this.name = studentStringArray[1];
        this.age = Integer.parseInt(studentStringArray[2]);
        this.group = Integer.parseInt(studentStringArray[3]);
        for (int i = 4; i < 4 + subjectsCount; i++){
            marks[i-4] = Integer.parseInt(studentStringArray[i]);
        }
    }

    public void setMark(String subject, int newMark){
        marks[subjectsList.indexOf(subject)] = newMark;
    }
}