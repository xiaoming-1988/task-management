package com.example.task_management.client.exception;

public class NoSuchTaskException extends RuntimeException {

    private static final String noSuchTaskMessage = "Task not fund, pls check again";
    public NoSuchTaskException() {
        super(noSuchTaskMessage);
    }
    public NoSuchTaskException(String message) {
        super(message);
    }

}
