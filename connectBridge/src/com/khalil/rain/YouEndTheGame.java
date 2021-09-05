package com.khalil.rain;

public class YouEndTheGame extends Exception {
    @Override
    public String getMessage() {
        return "You are out of Game!";
    }
}
