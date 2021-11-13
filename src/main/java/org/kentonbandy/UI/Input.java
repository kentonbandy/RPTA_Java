package org.kentonbandy.UI;

public interface Input {
    String prompt();
    boolean promptYN(String message);
    boolean quit();
}
