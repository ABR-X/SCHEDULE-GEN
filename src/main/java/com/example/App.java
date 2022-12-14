package com.example;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.mashape.unirest.http.exceptions.UnirestException;

public class App 
{

    // FUNCTION TO REVERSE A LIST
    public static <T> void revlist(List<T> list)
    {
  
        if (list.size() <= 1 || list == null)
            return;
        T value = list.remove(0);
        revlist(list);
        list.add(value);
    }

    public static void main( String[] args ) throws UnirestException, IOException
    {   


        Scanner  in = new Scanner(System.in);   

        // GET THE API KEY AND TOKEN
        System.out.println("ENTER THE API KEY : ");
        String key = in.next();
        System.out.println("ENTER THE TOKEN : ");
        String token = in.next();

        // SET THE KEY AND THE TOKEN INTO THE CLASS
        Auth.Authentication(key,token);
        
        // SET THE NAME OF THE BOARD
        Board board = new Board();
        System.out.println("Entrez le nom d'emploi :");
        String boardName = in.next();

        // CREATE A BOARD USING THE CreateBoard FUNCTION
        board = board.CreateBoard(board,boardName );
        

        // CREATING THE LIST OF DAYS
        DaysList list;
        for(int i = 0;i<6;i++){
            list =  new DaysList();
            list.createList(board.getId());
        }

        // CREATING THE LIST THAT CONTAINES THE TIME 
        System.out.println("Entrez le nom du filiere :");
        String listForTimeName = in.next();
        list =  new DaysList();
        list.createListForTime(board.getId(), listForTimeName);

          
        // GET THE NUMBER OF PROFESSORS
        System.out.println("Donnez le nombre des profs existant : ");
        int nombreDeProfs ;
        nombreDeProfs= in.nextInt();
        
        // GETTING INFOS ABOUT PROFESSORS
        System.out.println("Veuillez saisir les informations apropos des profs:\n");

        for(int i = 0; i<nombreDeProfs;i++){

            System.out.println("\n ****************************************************** \n");
            System.out.println("PROF NUM "  + (i+1) + ": \n");

            System.out.println("nom: ");
            String n = in.next();

            System.out.println("matiere: ");
            String m = in.next();

            System.out.println("heures par semaine: ");
            int h = in.nextInt();
            // CREATE PROFESSOR
            new Prof(m, n, h, in);  
        }
        



        /*  GET THE NAME OF THE DAYS(CREATED LISTS) FROM THE DaysList CLASS
            ASSIGN THE VALUES TO CeatedLists LIST AND THEN REVERSE THE LIST
            TO GET THE CORRECT ORDER FOR CREATING THE CARDS PROPERLY   */
        List<String> CreatedLists = DaysList.getKeys();
        revlist(CreatedLists);
        
        // CREATING THE CARDS 
        Card card;
        for (String day : CreatedLists) {
            
            if(day.equals(listForTimeName)){
                // CREATING THE CARDS IN THE TIME LIST
                card =  new Card();
                card.createCard("8:00 / 10:00",DaysList.getIdFromDaysList(day),"8:00 / 10:00");
                card.createCard("10:15 / 12:15",DaysList.getIdFromDaysList(day),"10:15 / 12:15");
                card.createCard("14:00 / 16:00",DaysList.getIdFromDaysList(day),"14:00 / 16:00");
                card.createCard("16:15 / 18:15",DaysList.getIdFromDaysList(day),"16:15 / 18:15");
                continue;
            }
            else{
                // CREATING THE CARDS IN THE DAYS LIST RANDOMLY
                for(int i = 0; i<4; i++){
                    Prof randomProf;
                    do{
                        randomProf = Prof.getRandomProf();
                    }while(randomProf.getHeures() < 2);
                    
                    randomProf.travaille();

                    card =  new Card();
                    card.createCard(randomProf.getMatiere(),DaysList.getIdFromDaysList(day),"Prof: " + randomProf.getNom());
                }
            }
        }
        in.close();



 
        




    }
}
