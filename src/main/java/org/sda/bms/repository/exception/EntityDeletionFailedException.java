package org.sda.bms.repository.exception;

public class EntityDeletionFailedException extends RuntimeException{
    public EntityDeletionFailedException(String message) {
        super(message);
    }

    public EntityDeletionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
