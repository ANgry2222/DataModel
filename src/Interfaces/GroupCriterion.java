package Interfaces;

import Main.Student;

@FunctionalInterface
public interface GroupCriterion{
    int getGroupKey(Student student);
}