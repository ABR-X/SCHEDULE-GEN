package com.example;


import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/*
 * Boards are where the majority of work happens. 
 * Trello boards can have multiple members that can create and edit cards,
 */
public class Board {
    private String id;

    // CreateBoard FUNCTION GETS THE NECESSARY PARAMS TO CREATE A BOARD
    //  THEN IT CREATES IT AND RETURNS THE BOARD  
    public Board CreateBoard(Board board, String name) throws UnirestException{

        Gson gson = new Gson();

        // GENERATING THE RESPONCE THAT WILL BE SENT
        HttpResponse<String> response = Unirest.post("https://api.trello.com/1/boards/")
        .queryString("name", name)
        .queryString("key", Auth.getKey())
        .queryString("token", Auth.getToken())
        .queryString("defaultLists", "false")
        .asString();
        board = gson.fromJson(response.getBody(), Board.class);
        System.out.println("\nBOARD CREATED SUCCESSFULLY!\n");
        return board;
    }

    public String getId() {
        return id;
    }
}
