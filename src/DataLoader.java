import java.util.List;

public interface DataLoader{
    String filePath = null;

    public List<Student> getData();

    public void readDataFromFile();
}
