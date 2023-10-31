package Commands;

import Interfaces.Command;

public class WrongCommand implements Command {
    @Override
    public void execute() {}

    @Override
    public void printResult() {
        System.out.println("Введена неизвестная команда!");
    }
}
