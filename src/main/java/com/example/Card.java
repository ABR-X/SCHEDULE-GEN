package com.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/*
 A Card is the most basic unit of information in Trello. Cards have a name, description etc...
 */
public class Card {
    private String id;


    public String getId(){
        return id;
    }

    // createCard GETS THE NECESSARY PARAMS TO CREATE A CARD 
    // GENERATES THE RESPONCE AND CREATES IT
    public void createCard(String name, String boardId, String description) throws UnirestException{
        
        // GENERATING THE RESPONCE THAT WILL BE SENT
        HttpResponse<JsonNode> response = Unirest.post("https://api.trello.com/1/cards")
        .header("Accept", "application/json")
        .queryString("idList", boardId)
        .queryString("key", Auth.getKey())
        .queryString("token", Auth.getToken())
        .queryString("name", name)
        .queryString("desc", description)
        .asJson();
        response.getBody();
    }
}
