public class Student {
    static final int subjectsCount = 6;
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
        return (float) result / subjectsCount;
    }

    public Student(String studentString){
        marks = new int[subjectsCount];
        String[] studentStringArray = studentString.split(";");
        this.family = studentStringArray[0];
        this.name = studentStringArray[1];
        this.age = Integer.parseInt(studentStringArray[2]);
        this.group = Integer.parseInt(studentStringArray[3]);
        for (int i = 4; i < 4 + subjectsCount; i++){
            marks[i-4] = Integer.parseInt(studentStringArray[i]);
        }
    }
}