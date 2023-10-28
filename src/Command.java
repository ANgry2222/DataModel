public interface Command {
    DataGroup dg = null;
    String result = "";
    void execute();
    void printResult();
}
