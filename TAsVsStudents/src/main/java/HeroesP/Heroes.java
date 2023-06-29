package HeroesP;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Heroes
{
    //----------------------PROPERTIES----------------------//
    private String name;
    private double power;
    private double speed;
    private double health;
    double warHealth;
    ImageView hero;

    static ArrayList<StudentHeroes>[] studentHeroesInLine = new ArrayList[] {new ArrayList<StudentHeroes>() , new ArrayList<StudentHeroes>() , new ArrayList<StudentHeroes>()};
    static ArrayList<TAHeroes>[] TAHeroesInLine = new ArrayList[] {new ArrayList<TAHeroes>() , new ArrayList<TAHeroes>() , new ArrayList<TAHeroes>()};

    //----------------------CONSTRUCTOR----------------------//
    public Heroes(String name, double power, double speed, double health)
    {
        this.name = name;
        this.power = power;
        this.speed = speed;
        this.health = health;
        this.warHealth = health;
    }

    //----------------------SETTERS AND GETTERS----------------------//
    public String getName() {return name;}
    public double getPower() {return power;}
    public double getSpeed() {return speed;}
    public double getHealth() {return health;}
    public double getWarHealth() {return warHealth;}

    public void setName(String name) {this.name = name;}
    public void setPower(double power) {this.power = power;}
    public void setSpeed(double speed) {this.speed = speed;}
    public void setHealth(double health) {this.health = health;}

}

