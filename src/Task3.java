class Task3 implements Command{
    private DataGroup dg;
    private String result;
    private String name;
    public Task3(String name, DataGroup dg){
        this.dg = dg;
        this.name = name;
        this.result = "";
    }
    @Override
    public void execute() {
        StringBuilder resultBuilder = new StringBuilder();
        StudentService studentService = new StudentService(dg, new LoadStudentsData("students.csv"));
        for (Student s: studentService.getStudentsByKey(name.toLowerCase().toCharArray()[0] - 'а')) {
            if(s.getFullName().startsWith(name))
                resultBuilder.append(String.format("%s, %d лет\n", s.getFullName(), s.getAge()));
        }
        this.result = resultBuilder.toString();
    }
    @Override
    public void printResult() {
        switch (result){
            case "": System.out.println("Поиск не дал результатов.");
            default: System.out.println(result);
        }
    }
}
