package com.example.tasvsstudents;

import HeroesP.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class PlayButtonCL extends AnchorPane implements Initializable {
    public static final Object tempSynchronized = new Object();
    private static int x = -700;
    private static int y = -700;
    private static int line = -700;

    @FXML
    private ImageView classroom;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button exit;

    @FXML
    private Button go;

    @FXML
    private Button line1;

    @FXML
    private Button line2;

    @FXML
    private Button line3;

    @FXML
    private ImageView amirali = new ImageView();

    @FXML
    private ImageView failed = new ImageView();

    @FXML
    private ImageView firm = new ImageView();

    @FXML
    private ImageView flatterer = new ImageView();

    @FXML
    private ImageView laidBack = new ImageView();

    @FXML
    private ImageView nerd = new ImageView();

    @FXML
    private ImageView normalS = new ImageView();

    @FXML
    private ImageView normalTA = new ImageView();

    @FXML
    private Label gameOver;

    public Label getGameOver() {
        return gameOver;
    }

    private final static String amiraliTAImage = "F:\\TAsVsStudents\\src\\main\\resources\\com\\example\\tasvsstudents\\amirali.png";
   private final static String firmTAImage = "F:\\TAsVsStudents\\src\\main\\resources\\com\\example\\tasvsstudents\\firm.png";
   private final static String normalTAImage = "F:\\TAsVsStudents\\src\\main\\resources\\com\\example\\tasvsstudents\\normal.png";
   private final static String laidBackTAImage = "F:\\TAsVsStudents\\src\\main\\resources\\com\\example\\tasvsstudents\\laid back.png";

   private final static String nerdStudentImage = "F:\\TAsVsStudents\\src\\main\\resources\\com\\example\\tasvsstudents\\nerd.png";
   private final static String flattererStudentImage = "F:\\TAsVsStudents\\src\\main\\resources\\com\\example\\tasvsstudents\\flatterer.png";
   private final static String normalStudentImage = "F:\\TAsVsStudents\\src\\main\\resources\\com\\example\\tasvsstudents\\normalS.png";
  private final static String failedStudentImage = "F:\\TAsVsStudents\\src\\main\\resources\\com\\example\\tasvsstudents\\failed.png";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);

        nerd.setVisible(false);
        flatterer.setVisible(false);
        normalS.setVisible(false);
        failed.setVisible(false);

        gameOver.setVisible(false);

    }

    @FXML
    void goOnClicked(MouseEvent event) {
        go.setVisible(false);

        line1.setVisible(true);
        line2.setVisible(true);
        line3.setVisible(true);

        nerd.setVisible(true);
        flatterer.setVisible(true);
        normalS.setVisible(true);
        failed.setVisible(true);

        Thread randomSendTAsThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    Random random = new Random();
                    int randomLine = random.nextInt(3);
                    int x;
                    int y;
                    if (randomLine == 0)
                    {
                        x = 5;
                        y = 415;
                    }
                    else if (randomLine == 1)
                    {
                        x = 5;
                        y = 274;
                    }
                    else
                    {
                        x = 5;
                        y = 181;
                    }

                    int randomTA = random.nextInt(4);
                    if (randomTA == 0 && amirali.getLayoutX() <= 0)
                        AmiraliTA.amiraliTA.play(randomLine , x , y , amirali);
                    else if (randomTA == 1 && firm.getLayoutX() <= 0)
                        FirmTA.firmTA.play(randomLine , x , y , firm);
                    else if (randomTA == 2 && laidBack.getLayoutX() <= 0)
                        LaidBackTA.laidBackTA.play(randomLine , x , y , laidBack);
                    else if (randomTA == 3 && normalTA.getLayoutX() <= 0)
                        NormalTA.normalTA.play(randomLine , x , y , normalTA);

                    try {
                        Thread.sleep(3500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        randomSendTAsThread.start();
    }


    @FXML
    void lineOneOnClicked(MouseEvent event) {
        synchronized (tempSynchronized)
        {
            line = 0;
            x = 507;
            y = 444;
        }
    }

    @FXML
    void lineTwoOnClicked(MouseEvent event) {
        synchronized (tempSynchronized)
        {
            line = 1;
            x = 482;
            y = 307;

        }
    }

    @FXML
    void lineThreeOnClicked(MouseEvent event) {
        synchronized (tempSynchronized)
        {
            line = 2;
            x = 454;
            y = 218;
        }
    }


    @FXML
    void nerdOnClicked(MouseEvent event) {
        if (x != -700)
            NerdStudent.nerdStudent.play(line , x , y , nerd);
    }

    @FXML
    void normalSOnClicked(MouseEvent event) {
        if (x != -700)
            NormalStudent.normalStudent.play(line , x , y , normalS);
    }

    @FXML
    void failedOnClicked(MouseEvent event) {
        if (x != -700)
            MashrootiStudent.failedStudent.play(line , x , y , failed);
    }

    @FXML
    void flattererOnClicked(MouseEvent event) {
        if (x != -700)
            FlattererStudent.flattererStudent.play(line , x , y , flatterer);
    }


    @FXML
    void exitOnClicked(MouseEvent event) {
        System.exit(0);
    }

//    public static void playMusic(String path)
//    {
//        InputStream music;
//        try {
//            music = new FileInputStream(new File(path));
//            AudioStream audioStream = new AudioStream;
//        }
//        catch ()
//        {
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
