package com.irojas.demojwt.sport.exception;


public class ExceptionClass {

 public static class UserNotFoundException extends RuntimeException {
     public UserNotFoundException(String message) {
         super(message);
     }
 }

 public static class PlayerNotFoundException extends RuntimeException {
     public PlayerNotFoundException(String message) {
         super(message);
     }
 }

 public static class LeagueNotFoundException extends RuntimeException {
     public LeagueNotFoundException(String message) {
         super(message);
     }
 }

 public static class PlayerAlreadyInLeagueException extends RuntimeException {
     public PlayerAlreadyInLeagueException(String message) {
         super(message);
     }
 }
}

