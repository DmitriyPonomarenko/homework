package part1.lesson02.task03.customExceptions;

public class DuplicatePersonException extends Exception {

    public DuplicatePersonException(String message) {
        super(message);
    }
}
