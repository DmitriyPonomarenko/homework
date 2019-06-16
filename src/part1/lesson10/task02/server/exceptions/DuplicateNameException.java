package part1.lesson10.task02.server.exceptions;

/**
 * если найдено повторяющееся имя клиента
 */
public class DuplicateNameException extends Exception {

    public DuplicateNameException(String message) {
        super(message);
    }
}
