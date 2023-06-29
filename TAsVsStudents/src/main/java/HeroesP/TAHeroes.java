package HeroesP;

import com.example.tasvsstudents.PlayButtonCL;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class TAHeroes extends Heroes
{

    public TAHeroes(String name, double power, double speed, double health)
    {
        super(name, power, speed, health);
    }

    public void play(int line , int x , int y , ImageView TAHero)
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                double backToStartX;
                double backToStartY;

                synchronized (PlayButtonCL.tempSynchronized) {
                    hero = TAHero;
                    backToStartX = TAHero.getLayoutX();
                    backToStartY = TAHero.getLayoutY();

                    Heroes.TAHeroesInLine[line].add(TAHeroes.this);

                    TAHero.setLayoutX(x);
                    TAHero.setLayoutY(y);
                }
                boolean shouldKeepGoing = true;
                double currentTime1 = (double) System.currentTimeMillis() / 1000;
                while (true)
                {
                    double currentTime2 = (double) System.currentTimeMillis() / 1000;
                    if (shouldKeepGoing && TAHero.getLayoutX() <= 590)
                    {
                        synchronized (PlayButtonCL.tempSynchronized)
                        {
                            TAHero.setY(0);
                            TAHero.setLayoutX(TAHero.getLayoutX() + ((currentTime2 - currentTime1) * getSpeed()));
                        }
                    }
                    else if (TAHero.getLayoutX() >= 540)
                    {
                        synchronized (PlayButtonCL.tempSynchronized)
                        {
                            //fight
                            TranslateTransition transition = new TranslateTransition();
                            transition.setNode(TAHero);
                            transition.setDuration(Duration.millis(50));
                            transition.setToX(-15);
                            transition.setAutoReverse(true);
                            transition.setCycleCount(2);
                            transition.play();

                            RamezaniOffice ramezaniOffice = new RamezaniOffice();
                            ramezaniOffice.health -= getPower() * (currentTime2 - currentTime1);
                            if (ramezaniOffice.health <= 0)
                            {
                                PlayButtonCL playButtonCL = new PlayButtonCL();
                                playButtonCL.getGameOver().setVisible(true);
                                RotateTransition rotate = new RotateTransition(Duration.seconds(2));
                                rotate.setByAngle(360);
                                rotate.play();
                                System.exit(0);
                            }
                        }
                    }
                    synchronized (PlayButtonCL.tempSynchronized) {
                        shouldKeepGoing = true;
                        for (int i = 0; i < Heroes.studentHeroesInLine[line].size(); i++) {
                            if (Math.abs(TAHero.getLayoutX() - Heroes.studentHeroesInLine[line].get(i).hero.getLayoutX()) <= 40) {
                                //fight
                                TranslateTransition transition = new TranslateTransition();
                                transition.setNode(TAHero);
                                transition.setDuration(Duration.millis(50));
                                transition.setToX(-15);
                                transition.setAutoReverse(true);
                                transition.setCycleCount(2);
                                transition.play();

                                Heroes.studentHeroesInLine[line].get(i).warHealth -= getPower() * (currentTime2 - currentTime1);
                                if (Heroes.studentHeroesInLine[line].get(i).warHealth > 0)
                                    shouldKeepGoing = false;
                            }
                        }
                        if (warHealth <= 0) {
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
                    TAHero.setY(0);
                    for (int i = 0; i < TAHeroesInLine[line].size(); i++) {
                        if (TAHeroesInLine[line].get(i) == TAHeroes.this) {
                            TAHeroesInLine[line].get(i).warHealth = TAHeroesInLine[line].get(i).getHealth();
                            TAHeroesInLine[line].remove(i);
                        }
                    }
                }
            }
        });
        thread.start();
    }
}

