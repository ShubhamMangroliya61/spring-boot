package com.springboot.blog.exceptions;

public class ResourceNotFountException extends RuntimeException {

    String resourceName;
    String fieldNamne;
    long fieldValue;

    public ResourceNotFountException(String resourceName, String fieldNamne, long fieldValue) {
        super(String.format("%s not found with %s : %l", resourceName, fieldNamne, fieldValue));
        this.resourceName = resourceName;
        this.fieldNamne = fieldNamne;
        this.fieldValue = fieldValue;
    }
}
