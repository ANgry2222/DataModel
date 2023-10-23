import java.util.Arrays;

public class Student {
    private String family;
    private String name;
    private int age;
    private int group; //Класс ученика
    private int[] marks; //Список оценок

    public char getFamilyFirstLetter(){
        return family.toLowerCase().toCharArray()[0];
    }

    public int getAge() {
        return age;
    }

    public int getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getFullName(){
        return family + " " + name;
    }

    public float getAverageMark(){
        int result = 0;
        for (int s: marks) {
            result += s;
        }
        return (float) result / 6;
    }

    public Student(String studentString){
        marks = new int[6];
        String[] studentStringArray = studentString.split(";");
        this.family = studentStringArray[0];
        this.name = studentStringArray[1];
        this.age = Integer.parseInt(studentStringArray[2]);
        this.group = Integer.parseInt(studentStringArray[3]);
        for (int i = 4; i < 10; i++){
            marks[i-4] = Integer.parseInt(studentStringArray[i]);
        }
    }
}