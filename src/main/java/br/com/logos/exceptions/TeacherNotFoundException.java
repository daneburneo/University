package br.com.logos.exceptions;

public class TeacherNotFoundException extends RuntimeException{

    public TeacherNotFoundException(String message) {
        super(message);
    }
}
