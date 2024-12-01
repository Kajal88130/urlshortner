package com.project.urlshortner.exception;

public class UrlShortnerException {

    public static  class InvalidUrlException extends  RuntimeException {
        public InvalidUrlException(String message) {
            super(message);
        }
    }



}
