import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static ClassroomDataGroups cdg;
    static AgeDataGroups adg;
    static NameDataGroups ndg;

    public static void main(String[] args){
        fillDataGroups();
        if(args[0].equals("task1")){
            highClassAverageMarks();
        } else if(args[0].equals("task2")){
            studentsWithExcellentMarks(14, 5.0f);
        } else if(args[0].equals("task3")){
            String searchName = args[1];
            findStudentsByName(searchName);
        }
    }

    //В данном случае мы ищем заданное вхождение в списке всех учеников, у которых имя начинается на ту же букву, что и заданное через параметр
    //Полный перебор учеников с именем на ту же букву для нахождения полных совпадений займет O(m)
    public static void findStudentsByName(String searchName){
        StringBuilder result = new StringBuilder();
        for (Student s: ndg.getStudentsByName(searchName.toLowerCase().toCharArray()[0] - 'а')) {
            if(s.getFullName().startsWith(searchName))
                result.append(String.format("%s, %d лет\n", s.getFullName(), s.getAge()));
        }
        System.out.println(result);
    }

    //Благодаря разделению учеников по возрасту, можно получить доступ к списку всех учеников определенного возраста за О(1)
    //Перебор всех учеников определенного возраста займет О(m*c), m <= n, где n - общее число учеников, m - число учеников определенного
    //возраста, c - количество различных оценок, из которых вычисляется средняя оценка (в данном случае c = 6)
    //Так как выбор по возрасту задан только нижней границей, верхней границей является максимальный возраст ученика, значит
    //появляется дополнительный множитель j, равный разнице заданного возраста и максимального, j <= 19
    //Общее время выполнения: О(m*c*j)
    public static void studentsWithExcellentMarks(int startAge, float marksCriteria){
        for(int i = startAge; i < AgeDataGroups.maxAge; i++){
            System.out.printf("Ученики %d лет с оценками выше %.2f:\n", i, marksCriteria);
            System.out.println(findStudentsByAgeAndMarkCriteria(i, marksCriteria));
        }
    }

    public static String findStudentsByAgeAndMarkCriteria(int age, float marksCriteria){
        StringBuilder result = new StringBuilder();
        for (Student s: adg.getStudentsByAge(age)) {
            if(s.getAverageMark() >= marksCriteria){
                result.append(String.format("%s, %d лет, средний балл: %.2f\n", s.getFullName(), s.getAge(), s.getAverageMark()));
            }
        }
        return result.toString();
    }

    //Благодаря разделению учеников по номеру класса, можно получить доступ к списку всех учеников определенного класса за О(1)
    //Перебор всех учеников определенного класса займет О(m*c), m <= n, где n - общее число учеников, m - число учеников определенного
    //класса, c - количество различных оценок, из которых вычисляется средняя оценка (в данном случае c = 6)
    public static void highClassAverageMarks(){
        float tenthGrade = findAverageMarkForClassGroup(10);
        float eleventhGrade = findAverageMarkForClassGroup(11);
        System.out.printf("Средняя оценка учеников 10 класса: %.4f\n", tenthGrade);
        System.out.printf("Средняя оценка учеников 11 класса: %.4f\n", eleventhGrade);
        System.out.printf("Общая средняя оценка учеников страших классов: %.4f\n", (tenthGrade + eleventhGrade)/2);
    }

    static float findAverageMarkForClassGroup(int group){
        float result = 0;
        for (Student s: cdg.getStudentsByGroup(group)) {
            result += s.getAverageMark();
        }
        return result / cdg.getStudentsByGroup(group).size();
    }

    static void fillDataGroups(){
        cdg = new ClassroomDataGroups();
        adg = new AgeDataGroups();
        ndg = new NameDataGroups();

        String desktopPath = System.getProperty("user.home") + File.separator +"Desktop";
        Scanner sc = null;
        try {
            sc = new Scanner(new File(Main.class.getResource("students.csv").getFile()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        sc.useDelimiter("\n");
        sc.next();
        String buffer;
        while(sc.hasNext()){
            buffer = sc.next();
            cdg.addStudent(new Student(buffer));
            adg.addStudent(new Student(buffer));
            ndg.addStudent(new Student(buffer));
        }
    }
}