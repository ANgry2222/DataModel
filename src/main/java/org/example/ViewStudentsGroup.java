package org.example;

import Commands.ServletTask1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@WebServlet(urlPatterns = {"/groups"})
public class ViewStudentsGroup extends HttpServlet {
    private int pageSize;
    private int pageNumber;
    private int groupNumber;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!checkRequestParameters(req, resp)) return;

        DataGroup cdg = new DataGroup(Student::getGroup);
        ServletTask1 servletTask1 = new ServletTask1(cdg, groupNumber);
        try {
            servletTask1.execute();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        if(pageNumber < 1 || servletTask1.getListSize() < (pageNumber - 1) * pageSize){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        resp.setCharacterEncoding("UTF-8");
        try (var output = resp.getWriter()){
            int currentPageSize = (servletTask1.getListSize() < (pageNumber + 1) * pageSize) ? servletTask1.getListSize() - (pageNumber - 1) * pageSize : pageSize;

            output.write("<html><head><meta charset=\"utf-8\"></head><body>");

            for (String s: Arrays.stream(servletTask1.getResult().split("\n"),
                    (pageNumber - 1) * pageSize,
                    ((pageNumber - 1) * pageSize) + currentPageSize).toList()) {
                output.write(new String(s.getBytes(), StandardCharsets.UTF_8)  + "<br />");


            }
            output.write("</body></html>");
            output.flush();
        }
    }
    
    private boolean checkRequestParameters(HttpServletRequest req, HttpServletResponse resp){
        String grpNum = req.getParameter("groupNumber");
        String pgSize = req.getParameter("pageSize");
        String pgNum = req.getParameter("pageNumber");

        if(grpNum == null){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        } else{
            this.groupNumber = Integer.parseInt(grpNum);
        }

        this.pageSize = (pgSize == null) ? 50 : Integer.parseInt(pgSize);
        this.pageNumber = (pgNum == null) ? 1 : Integer.parseInt(pgNum);


        if(groupNumber < 1 || groupNumber > 12){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }

        if(pageSize < 1){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        return true;
    }
}
