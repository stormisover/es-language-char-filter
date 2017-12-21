package org.elasticsearch.plugin.analysis.exception;

public class NoSuchFilterLanguageException extends RuntimeException {

    public NoSuchFilterLanguageException() {
    }

    public NoSuchFilterLanguageException(String message) {
        super(message);
    }
}
