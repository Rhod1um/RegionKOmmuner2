package com.example.regionkommuner2.exception;


public class ResourceNotFoundException extends RuntimeException { //nu er det unchecked - runtime
    //skal være unchecked for ellers skal vi try catche
    //så husk: er det checked skal den try catches fra start

    //skal have denne linje ved fejlmeddenlse på databaseadgang:
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
} //errrormessage er en ekstrem generel måde at præsentere det på, bare tom beholder, derfor ikke exenter noget
//den viser bare hvordan ting skal præsenteres
//Den her klasse er en exception på linje med arithmetikexception osv
