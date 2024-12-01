package com.project.urlshortner.utils;

public enum Constants {

    CHARACTERS("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"),

    Short_URL_LENGTH(8);
    private final String characters;
    private final int length;

    Constants(String characters) {
        this.characters = characters;
        this.length = -1;
    }
    Constants(int length) {
        this.characters = null;
        this.length = length;
    }

    public String getCharacters() {
        return characters;
    }

    public int getLength() {
        return length;
    }
}
