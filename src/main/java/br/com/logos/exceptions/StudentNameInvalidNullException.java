package br.com.logos.exceptions;

public class StudentNameInvalidNullException extends RuntimeException{

    public StudentNameInvalidNullException() {
    }

    public StudentNameInvalidNullException(String message) {
        super(message);
    }
}
