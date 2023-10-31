package Commands;

import Interfaces.Command;
import Main.DataGroup;
import Main.LoadStudentsData;
import Main.Student;
import Main.StudentService;

public class Task1 implements Command {
    private DataGroup dg;
    private String result;
    public Task1(DataGroup dg){
        this.dg = dg;
        this.result = "";
    }
    @Override
    public void execute() {
        StudentService studentService = new StudentService(dg, new LoadStudentsData("../res/students.csv"));

        float tenthGrade = findAverageMarkForClassGroup(10, studentService);
        float eleventhGrade = findAverageMarkForClassGroup(11, studentService);
        float twelfthGrade = findAverageMarkForClassGroup(12, studentService);

        this.result =
                String.format("Средняя оценка учеников 10 класса: %.4f\n", tenthGrade) +
                String.format("Средняя оценка учеников 11 класса: %.4f\n", eleventhGrade) +
                String.format("Средняя оценка учеников 12 класса: %.4f\n", twelfthGrade) +
                String.format("Общая средняя оценка учеников страших классов: %.4f\n", (tenthGrade + eleventhGrade + twelfthGrade) / 3);
    }

    public static float findAverageMarkForClassGroup(int group, StudentService studentService){
        float result = 0;
        for (Student s: studentService.getStudentsByKey(group)) {
            result += s.getAverageMark();
        }
        return result / studentService.getStudentsByKey(group).size();
    }

    @Override
    public void printResult() {
        switch (result){
            case "": System.out.println("Поиск не дал результатов.");
            default: System.out.println(result);
        }
    }
}