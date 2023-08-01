package br.com.logos.exceptions;

public class SemesterNotFoundException extends RuntimeException {

    public SemesterNotFoundException(String message) {
        super(message);
    }
}

