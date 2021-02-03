package edu.gatech.seclass.textmodifier;

public class TextModifierException extends Exception {
    TextModifierException() {
        super("Something went wrong during processing");
    }

    TextModifierException(String str) {
        super(str);
    }
}
