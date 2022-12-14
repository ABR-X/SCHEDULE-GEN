package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


// Prof REPRESENT A PROFESSOR THAT HAS A NAME(name), SUBJECT(matiere) AND THE WORKING HOURS(heures)
public class Prof {
    private String matiere;
    private String nom;
    private int heures;
    private static int totalHeuresDispo = 48; // TOTAL AVAILABLE HOURS IN THE TIMETABLE
    static List<Prof> listProfs = new ArrayList<>(); // LIST OF PROFESSORS
    
    
    
    public Prof(String matiere, String nom, int heures, Scanner input){
        setHeures(heures, input);
        setMatiere(matiere);
        setNom(nom);
        listProfs.add(this);
    }
    

    // SETS THE HOURS OF WORK WITH VERIFICATIONS
    public void setHeures(int heures, Scanner input) {
 
        while((heures <= 1) || (heures % 2 != 0)){
            System.out.println("Veuillez entrez une valeur pair sup a 1 h: ");
            heures = input.nextInt();
        }
        int h = totalHeuresDispo;
        if(totalHeuresDispo <=1){
            System.out.println("Les heures de travails ne sont pas disponible dans cet emploi!!");
            return;
        }
        while((h-heures)<0){
            System.out.println("les heures disponible dans l'emploi sont :" + totalHeuresDispo);
            System.out.println("\nVeuillez resaisir les heures de travails: ");
            heures = input.nextInt();
        }
        this.heures = heures;
        totalHeuresDispo -= heures;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getHeures() {
        return heures;
    }

    public String getMatiere() {
        return matiere;
    }

    public String getNom() {
        return nom;
    }

    // travaille METHODE subtract 2 HOURS OF WORK (heures attribute) WHEN IT'S CALLED
    public void travaille(){
        this.heures = heures - 2;
    }

    // THE Prof METHOD RETURNS A RANDOM Prof OBJECT FROM THE ListProfs attribute
    public static Prof getRandomProf(){
        int randomIndex = new Random().nextInt(listProfs.size());
        Prof randomProf = listProfs.get(randomIndex);
        return randomProf;
    }

    @Override
    public String toString() {
        
        return "nom : " + getNom() + ", heures : " + getHeures() + ", matiere : " + getMatiere() + "\n";
    }
    
}
