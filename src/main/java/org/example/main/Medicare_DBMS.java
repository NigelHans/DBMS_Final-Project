package org.example.main;

import org.example.service.UserService;

public class Medicare_DBMS {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.start();
    }
}