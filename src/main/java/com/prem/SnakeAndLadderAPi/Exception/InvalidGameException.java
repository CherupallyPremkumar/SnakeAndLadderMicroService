package com.prem.SnakeAndLadderAPi.Exception;

public class InvalidGameException extends Exception {
    public InvalidGameException(String s) {
        super(s);
    }
    public InvalidGameException(String s,Throwable cause) {
        super(s,cause);
    }

}
