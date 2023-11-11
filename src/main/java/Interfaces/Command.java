package Interfaces;

import java.net.URISyntaxException;

public interface Command {
    void execute() throws URISyntaxException;
    String getResult();
}
