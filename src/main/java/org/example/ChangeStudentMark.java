package org.example;

import Commands.ServletTask2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = {"/student"})
public class ChangeStudentMark extends HttpServlet {
    private String studentName;
    private int studentGroup;
    private String subject;
    private int newMark;
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkRequestParameters(req, resp)) return;

        DataGroup cdg = new DataGroup(Student::getGroup);
        ServletTask2 servletTask = new ServletTask2(cdg, studentGroup, studentName, newMark, subject);
        try {
            servletTask.execute();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        resp.setCharacterEncoding("UTF-8");
        String responseHead = "Изменение оценки ученика ";
        String responseTail = " по предмету " + subject + " на оценку " + newMark + ": "
                + servletTask.getResult().split(",")[0]
                + "\nНовая средняя оценка: " + servletTask.getResult().split(",")[1];
        try (var output = resp.getWriter()){
            output.write(new String(responseHead.getBytes(), StandardCharsets.UTF_8));
            output.write(studentName);
            output.write(new String(responseTail.getBytes(), StandardCharsets.UTF_8));
            output.flush();
        }
    }

    private boolean checkRequestParameters(HttpServletRequest req, HttpServletResponse resp){
        String studName = req.getParameter("studentName");
        String studGrp = req.getParameter("studentGroup");
        String subj = req.getParameter("subject");
        String newMark = req.getParameter("newMark");

        if(studName == null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        } else{
            this.studentName = studName;
        }

        if(studGrp == null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        } else{
            this.studentGroup = Integer.parseInt(studGrp);
        }

        if(subj == null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        } else{
            this.subject = subj;
        }

        if(newMark == null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        } else{
            this.newMark = Integer.parseInt(newMark);
        }

        if(this.newMark < 1|| this.newMark > 5){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        if(this.studentGroup < 1 || this.studentGroup > 12){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        if(!Student.subjectsList.contains(subject)){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        return true;
    }
}
