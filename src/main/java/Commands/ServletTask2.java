package Commands;

import Interfaces.Command;
import org.example.DataGroup;
import org.example.LoadStudentsData;
import org.example.Main;
import org.example.StudentService;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class ServletTask2 implements Command {
    private DataGroup dg;
    private String result;
    private int group;
    private String studentName;
    private String subject;
    private int newMark;

    public ServletTask2(org.example.DataGroup dg, int group, String studentName, int newMark, String subject){
        this.dg = dg;
        this.result = "";
        this.group = group;
        this.studentName = studentName;
        this.subject = subject;
        this.newMark = newMark;
    }
    @Override
    public void execute() throws URISyntaxException {
        URL resource = Main.class.getResource("/students.csv");
        Paths.get(resource.toURI()).toFile();
        StudentService studentService = new StudentService(dg, new LoadStudentsData(resource.getPath()));

        for(int i = 0; i < studentService.getStudentsByKey(group).size(); i++){
            if(new String(studentService.getStudentsByKey(group).get(i).getFullName().getBytes(), StandardCharsets.UTF_8).contains(studentName)){
                studentService.getStudentsByKey(group).get(i).setMark(subject, newMark);
                this.result = "Success," + studentService.getStudentsByKey(group).get(i).getAverageMark();
                return;
            }
        }
    }

    @Override
    public String getResult() {
        switch (result){
            case "": return "Failure, 1";
            default: return result;
        }
    }
}