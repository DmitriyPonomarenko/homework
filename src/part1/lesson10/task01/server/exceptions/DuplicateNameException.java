package part1.lesson10.task01.server.exceptions;

/**
 * если найдено повторяющееся имя клиента
 */
public class DuplicateNameException extends Exception {

    public DuplicateNameException(String message) {
        super(message);
    }
}
