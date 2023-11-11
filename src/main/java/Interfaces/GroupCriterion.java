package Interfaces;

import org.example.Student;

@FunctionalInterface
public interface GroupCriterion{
    int getGroupKey(Student student);
}