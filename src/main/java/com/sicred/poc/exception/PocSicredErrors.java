package com.sicred.poc.exception;

import lombok.Getter;

public enum PocSicredErrors {

    AGENDA_NOT_FOUND("Agenda Not Found"),
    ASSOCIATE_NOT_FOUND("Associate Not Found"),
    ASSOCIATE_NOT_SAVED("Associate Not Saved"),
    ASSOCIATE_NOT_UPDATED("Associate Not Updated"),
    VALUE_INVALID("Value Invalid"),
    AGENDA_NOT_SAVED("Agenda Not Saved"),
    AGENDA_NOT_UPDATED("Agenda Not Updated"),
    ID_MUST_NOT_BE_NULL_WHEN_UPDATE("Id Must Not Be Null When Update"),
    VOTE_INVALID("Vote Invalid");

    @Getter
    private String message;

    PocSicredErrors(String message) {
        this.message = message;
    }
}
