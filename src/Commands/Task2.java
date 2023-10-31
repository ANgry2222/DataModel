package Commands;

import Interfaces.Command;
import Main.DataGroup;
import Main.LoadStudentsData;
import Main.Student;
import Main.StudentService;

public class Task2 implements Command {
    private DataGroup dg;
    private String result;
    public Task2(DataGroup dg){
        this.dg = dg;
        this.result = "";
    }
    @Override
    public void execute() {
        int minAge = 14;
        float marksCriteria = 5.0f;
        StudentService studentService = new StudentService(dg, new LoadStudentsData("res/students.csv"));
        StringBuilder resultBuilder = new StringBuilder();
        for(int i = minAge; i < studentService.getMapSize(); i++){
            resultBuilder.append(String.format("Ученики %d лет с оценками выше %.2f:\n", i, marksCriteria));
            resultBuilder.append(findStudentsByAgeAndMarkCriteria(i, marksCriteria, studentService));
        }
        this.result = resultBuilder.toString();
    }

    public static String findStudentsByAgeAndMarkCriteria(int age, float marksCriteria, StudentService studentService){
        StringBuilder resultBuilder = new StringBuilder();
        for (Student s: studentService.getStudentsByKey(age)) {
            if(s.getAverageMark() >= marksCriteria){
                resultBuilder.append(String.format("%s, %d лет, средний балл: %.2f\n", s.getFullName(), s.getAge(), s.getAverageMark()));
            }
        }
        return resultBuilder.toString();
    }

    @Override
    public void printResult() {
        switch (result){
            case "": System.out.println("Поиск не дал результатов.");
            default: System.out.println(result);
        }
    }
}
