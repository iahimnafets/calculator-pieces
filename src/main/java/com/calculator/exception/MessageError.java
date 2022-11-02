package com.calculator.exception;

public enum MessageError {

    ID_AND_PENCE_ARE_MANDATORY(1, "The externalId and pence are mandatory!"),
    PENCE_NO_DECIMALS(2, "The pence need to be without decimals!");

    private final int id;
    private final String message;

    MessageError(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() { return id; }
    public String getMessage() { return message; }

}
