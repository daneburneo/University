package br.com.logos.exceptions;

public class SemesterNameNotFound extends RuntimeException {

    public SemesterNameNotFound(String message) {
        super(message);
    }
}

