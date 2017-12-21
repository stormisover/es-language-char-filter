package org.elasticsearch.plugin.analysis.exception;

public class EmptyFilterLanguageException extends RuntimeException {

    public EmptyFilterLanguageException() {
    }

    public EmptyFilterLanguageException(String message) {
        super(message);
    }
}
