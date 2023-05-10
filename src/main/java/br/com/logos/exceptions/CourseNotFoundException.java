package br.com.logos.exceptions;

public class CourseNotFoundException extends  RuntimeException{

    public CourseNotFoundException(String message) {
        super(message);
    }
}
