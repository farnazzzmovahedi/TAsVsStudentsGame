package HeroesP;

import com.example.tasvsstudents.PlayButtonCL;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class StudentHeroes extends Heroes
{
    //----------------------PROPERTIES----------------------//
    private int timeToLoad;

    //----------------------CONSTRUCTOR----------------------//
    public StudentHeroes(String name, double power, double speed, double health, int timeToLoad)
    {
        super(name, power, speed, health);
        this.timeToLoad = timeToLoad;
    }

    //----------------------SETTERS AND GETTERS----------------------//
    public int getTimeToLoad() {return timeToLoad;}

    public void setTimeToLoad(int timeToLoad) {this.timeToLoad = timeToLoad;}

    //----------------------METHODS----------------------//
    public void play(int line , int x , int y , ImageView studentHero)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                double backToStartX;
                double backToStartY;

                synchronized (PlayButtonCL.tempSynchronized) {
                    hero = studentHero;
                    backToStartX = studentHero.getLayoutX();
                    backToStartY = studentHero.getLayoutY();

                    Heroes.studentHeroesInLine[line].add(StudentHeroes.this);

                    studentHero.setLayoutX(x);
                    studentHero.setLayoutY(y);
                }

                boolean shouldKeepGoing = true;
                double currentTime1 = (double) System.currentTimeMillis() / 1000;
                while (true)
                {
                    double currentTime2 = (double) System.currentTimeMillis() / 1000;
                    if (shouldKeepGoing)
                    {
                        synchronized (PlayButtonCL.tempSynchronized)
                        {
                            studentHero.setY(0);
                            studentHero.setLayoutX(studentHero.getLayoutX() - ((currentTime2 - currentTime1) * getSpeed()));
                        }

                    }
                    synchronized (PlayButtonCL.tempSynchronized) {
                        shouldKeepGoing = true;
                        for (int i = 0; i < Heroes.TAHeroesInLine[line].size(); i++) {
                            if (Math.abs(studentHero.getLayoutX() - Heroes.TAHeroesInLine[line].get(i).hero.getLayoutX()) <= 40) {
                                //fight
                                TranslateTransition transition = new TranslateTransition();
                                transition.setNode(studentHero);
                                transition.setDuration(Duration.millis(50));
                                transition.setToX(-15);
                                transition.setAutoReverse(true);
                                transition.setCycleCount(2);
                                transition.play();

                                Heroes.TAHeroesInLine[line].get(i).warHealth -= getPower() * (currentTime2 - currentTime1);
                                if (Heroes.TAHeroesInLine[line].get(i).warHealth > 0)
                                    shouldKeepGoing = false;
                            }
                        }
                        if (warHealth <= 0 || studentHero.getLayoutX() <= 0) {
                            hero.setLayoutX(backToStartX);
                            hero.setLayoutY(backToStartY);
                            break;
                        }
                    }
                    currentTime1 = currentTime2;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (PlayButtonCL.tempSynchronized)
                {
                    studentHero.setY(0);
                    for (int i = 0; i < studentHeroesInLine[line].size(); i++) {
                        if (studentHeroesInLine[line].get(i) == StudentHeroes.this) {
                            studentHeroesInLine[line].get(i).warHealth = studentHeroesInLine[line].get(i).getHealth();
                            studentHeroesInLine[line].remove(i);
                        }
                    }
                }
            }
        });
        thread.start();
    }

}
