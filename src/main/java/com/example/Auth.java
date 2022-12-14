package com.example;

/*
 Trello uses a delegated authentication and authorization flow so that an application 
 never has to deal with storing or handling usernames or passwords.
 Instead, the application passes control to Trello (identifying itself via the API key) 
 and once Trello has allowed the user to choose an account and sign in,
 Trello will hand the user and control back to your application, along with an API Token.
 */


/* THIS CLASS CONTAINS THE NECESSARY PARAMETERS TO PREFORM THE AUTHENTICATION
 WE CAN SAVE OUR TOKEN AND KEY IN IT AND CALL IT WHENEVER WE WANT TO SEND AN 
 HTTPREQUEST TO TRELLO API
*/ 
public class Auth {
    private static String token;
    private static String key;

    public static void Authentication(String k, String t){
        key = k;
        token = t;
    }

    public static String getToken(){
        return token;
    }

    public static String getKey(){
        return key;
    }


}
