package Commands;

import Interfaces.Command;
import org.example.*;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class ServletTask1 implements Command {
    private DataGroup dg;
    private String result;
    private int group;
    private int listSize;
    public ServletTask1(org.example.DataGroup dg, int group){
        this.dg = dg;
        this.result = "";
        this.group = group;
        this.listSize = 0;
    }
    @Override
    public void execute() throws URISyntaxException {
        URL resource = Main.class.getResource("/students.csv");
        Paths.get(resource.toURI()).toFile();
        StudentService studentService = new StudentService(dg, new LoadStudentsData(resource.getPath()));
        this.listSize = studentService.getStudentsByKey(group).size();
        for (Student s: studentService.getStudentsByKey(group)) {
            result = result + ("Ученик " + group + " класса " + s.getFullName() + ". Средний балл: " + String.format("%.2f", s.getAverageMark()) + "\n");
        }
    }

    @Override
    public String getResult() {
        switch (result){
            case "": return "Поиск не дал результатов.";
            default: return result;
        }
    }

    public int getListSize(){
        return listSize;
    }
}
