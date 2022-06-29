package com.gulukal.springboottesting.exception;

public class ResourceNorFoundException extends RuntimeException{

     public ResourceNorFoundException(String message){
         super(message);
     }

     public ResourceNorFoundException(String message , Throwable cause){
         super(message, cause);
     }
}
