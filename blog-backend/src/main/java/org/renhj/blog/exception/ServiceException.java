package org.renhj.blog.exception;

public class ServiceException extends RuntimeException {
    private String message;

    public ServiceException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
