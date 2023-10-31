package Main;

import Commands.*;
import Interfaces.Command;
import java.util.Scanner;

public class CommandBuilder {
    DataGroup adg = new DataGroup(student -> student.getAge());
    DataGroup ndg = new DataGroup(student -> student.getFullName().toLowerCase().toCharArray()[0] - 'а');
    DataGroup cdg = new DataGroup(student -> student.getGroup());

    public CommandBuilder(){
        printMenu();
        Command executeCommand = null;
        Scanner consoleScanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit){
            String input = consoleScanner.nextLine();
            switch (input){
                case "1": {
                    executeCommand = new Task1(cdg);
                    break;
                }
                case "2": {
                    executeCommand = new Task2(adg);
                    break;
                }
                case "3": {
                    System.out.println("Введите имя ученика для поиска и нажмите [Enter]");
                    executeCommand = new Task3(consoleScanner.nextLine(), ndg);
                    break;
                }
                case "4":{
                    return;
                }
                default:{
                    executeCommand = new WrongCommand();
                }
            }
            executeCommand.execute();
            executeCommand.printResult();
            printMenu();
        }
    }

    private void printMenu(){
        System.out.println("Анализ данных студентов");
        System.out.println("Выберите пункт меню и нажмите [Enter]");
        System.out.println("1. Вычислить среднюю оценку в старших классах");
        System.out.println("2. Поиск всех отличников старше 14 лет");
        System.out.println("3. Поиск ученика по фамилии");
        System.out.println("4. Выход");
    }
}
