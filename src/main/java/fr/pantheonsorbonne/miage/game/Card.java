package fr.pantheonsorbonne.miage.game;


public class Card{
    protected static final String[] names = {"ACE","ROI",
        "REINE",
        "VALET",
        "10",
        "9",
        "8",
        "7", "6", "5", "4", "3", "2"};
    protected static final String[] colors = {"CARREAU", "COEUR", "TREFLE","PIC" };
    private String name;
    private String color;

    public String getColor() {
        return color;
    }


    public void setColor(String color) {
        this.color = color;
    }


    public String[] getNames() {
        return names;
    }

   
    public String[] getColors() {
        return colors;
    }


    public String getName(){
        return this.name;
    }
    public Card(String name, String color ){
        this.name = name;
        this.color = color;
    }
   
    
    
    

}