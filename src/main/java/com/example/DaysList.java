package com.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


/*
 * A List is a collection of Cards. In the standard Trello interface,
 * Lists are stacked horizontally and are ordered on a board.
 */
public class DaysList {

    private String id;

    // THE days STOCKS THE CREATED LISTS (created days)
    private static LinkedHashMap<String, String> days = new LinkedHashMap<>();

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    // RETURNS THE ID OF THE WANTED LIST BY KEY
    public static String getIdFromDaysList(String key){
        return days.get(key);
    }

    // RETURNS THE NAME OF ALL THE DAYS IN THE days LIST
    public static List<String> getKeys(){
        // CAST THE KEYS SET INTO ARRAYLIST
        List<String> daysList = new ArrayList<>(days.keySet());
        return daysList;
    }


    /* 
    getDayName GETS THE DAYNUMBER AND RETURNS THE DAY NAME
    */ 
    public static String getDayName(int dayNumber){
        String temp = "";

        switch(dayNumber){
        
            case 0:
                
                temp = "Samedi";
                break;
        
            case 1:
                temp = "Vendredi";
                break;
            case 2:
                temp = "Jeudi";
                break;
            case 3:
                temp = "Mercredi";
                break;
            case 4:
                temp = "Mardi";
                break;
            case 5:
                temp = "Lundi";
                break;

            default:
                temp = "null";
                break;
        }

        return temp;
    }

    // createList GETS THE NECESSARY PARAMS TO CREATE A LIST 
    // GENERATES THE RESPONCE AND CREATES IT AND RETURNS THE LIST
    public DaysList createList(String idBoard) throws UnirestException{
        DaysList list;
        Gson gson = new Gson();

        // GENERATING THE RESPONCE THAT WILL BE SENT
        HttpResponse<String> response = Unirest.post("https://api.trello.com/1/lists")
        .queryString("name", getDayName(days.size()))
        .queryString("idBoard", idBoard)
        .queryString("key", Auth.getKey())
        .queryString("token", Auth.getToken())
        .asString();
        list = gson.fromJson(response.getBody(), DaysList.class);
        days.put(getDayName(days.size()), list.id);
        return list;
      


    }

    // SAME AS createList BUT PERSONALIZED FOT THE LIST OF TIME
    public DaysList createListForTime(String idBoard, String name) throws UnirestException{
        DaysList list;
        Gson gson = new Gson();


        // GENERATING THE RESPONCE THAT WILL BE SENT
        HttpResponse<String> response = Unirest.post("https://api.trello.com/1/lists")
        .queryString("name",name)
        .queryString("idBoard", idBoard)
        .queryString("key", Auth.getKey())
        .queryString("token", Auth.getToken())
        .asString();
        list = gson.fromJson(response.getBody(), DaysList.class);
        days.put(name, list.id);
        return list;
      


    }

}
