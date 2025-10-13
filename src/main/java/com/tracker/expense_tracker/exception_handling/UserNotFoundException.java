package com.tracker.expense_tracker.exception_handling;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(){
        super("User is not found in Database");
    }

}
