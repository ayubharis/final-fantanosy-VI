/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalFantanosyVI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/*
 * ICS3U CULMINATING ASSIGNMENT: FINAL FANTANOSY VI
 * AUTHORS: HARIS AYUB, JESSE WANG, TIM HE
 * DATE: JANUARY 23RD 2017
 */
public class FinalFantanosy extends javax.swing.JPanel {

    /**
     * Creates new form JPanel
     */
    public FinalFantanosy() {
        initComponents();
        //initialize timers

        charTimer = new CharTimer();
        albumTimer = new AlbumTimer();
        //initialize and gather all the required images

        ImageIcon sprite = new ImageIcon("spriteSheet.png");
        ImageIcon album = new ImageIcon("albumSprites.png");
        ImageIcon albumMiss = new ImageIcon("albumMiss.png");
        ImageIcon albumGet = new ImageIcon("albumGet.png");
        //variable for different life skins and backgrounds

        ImageIcon lifeCount = new ImageIcon(lifeName);
        ImageIcon background = new ImageIcon(backgroundName);
        spriteSheet = sprite.getImage();
        albumSheet = album.getImage();
        albumMissed = albumMiss.getImage();
        albumGot = albumGet.getImage();
        lifeCounter = lifeCount.getImage();
        bg = background.getImage();
    }

    //timer for character animations
    class CharTimer implements ActionListener {

        Timer charTimer;

        public CharTimer() {
            //set sprite changing timer
            charTimer = new Timer(100, this);
            charTimer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //change run frame of sprite
            currentFrame = currentFrame
                    + frameMod;
            //reverse direction if at first or last frame

            if (currentFrame == 1 || currentFrame == 4) {
                currentFrame = 1;
            }
        }
    }
    //timer for albums

    class AlbumTimer implements ActionListener {
        Timer albumTimer;
        public AlbumTimer() {
            albumTimer = new Timer(albumDelay, this);
            albumTimer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //randomly generate album, and album group so that we can check if it matches player

            albumGroup = r.nextInt(3);
            currentX = r.nextInt(3) + albumGroup * 3;
            currentY = r.nextInt(3);
            //randomly generate album x and y

            albumY = r.nextInt(400) + 50;
            albumX = r.nextInt(300) + 150;
            repaint();
        }
    }
    int albumDelay = 5000;
    int currentX = 0; //album row 
    int currentY = 0; //album column
    int quintX = 0; //quint x (he is drawn when you collect/miss an album)
    int quintY = 0; //quint y
    int currentFrame = 0; //frame for character
    int frameMod = 0; //direction of animation, left or right
    int currentType = 0; //row #
    int character = 0; // character variable (anthony, haris or tim)
    int speed = 5; //how fast you go, is increased further in the game, as time gets shorter
    public static int points = 28;
    int lives = 3;
    int albumGroup = 0; // variable to check if character is corrected
    boolean isCollected = false; //variable used to draw quint
    int charX = 325; //character x
    int charY = 250; //character y
    int albumX = 325; //album x
    int albumY = 150; //album y
    boolean keyPressed = false; //variable to check if character should be moving
    public static String songName = "03 In The Aeroplane Over The Sea.mp3"; //ITAOTS default song
    public static String backgroundName = "animalcollective.jpg"; //MPP default background
    public static String lifeName = "kanyeLife.png"; //Kanye default life
    Image albumSheet;
    Image spriteSheet;
    Image albumMissed;
    Image albumGot;
    Image lifeCounter;
    Image bg;
    CharTimer charTimer;
    AlbumTimer albumTimer;
    Random r = new Random();

    public void keyPressed(KeyEvent event) {
        //to move character, and if you go off screen draw on other side
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP:
                charY -= speed;
                currentType = (currentType = 1);
                frameMod = 1;
                charTimer.charTimer.start();
                if (charY < 0) {
                    charY = 420;
                }
                break;
            case KeyEvent.VK_DOWN:
                charY += speed;
                currentType = (currentType = 0);
                frameMod = 1;
                charTimer.charTimer.start();
                if (charY > 420) {
                    charY = 0;
                }
                break;
            case KeyEvent.VK_LEFT:
                currentType = (currentType = 2);
                charX -= speed;
                frameMod = 1;
                if (charX < 100) {
                    charX = 550;
                }
                charTimer.charTimer.start();
                break;
            case KeyEvent.VK_RIGHT:
                currentType = (currentType = 3);
                charX += speed;
                frameMod = 1;
                if (charX > 550) {
                    charX = 100;
                }
                charTimer.charTimer.start();
                break;
            case KeyEvent.VK_SPACE:
                //change character

                character = character + 1;
                if (character == 3) {
                    character = 0;
                }
                charTimer.charTimer.start();
                break;
        }
        repaint();
    }
//if no key is pressed, do not animate

    public void keyReleased(KeyEvent event) {
        if (KeyEvent.KEY_RELEASED != 0) {
            frameMod = 0;
            currentFrame = 0;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setToolTipText("");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(1000, 1000));
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //draw background

        g.drawImage(bg, 100, 0, this);
        //check album collision

        if (charX + 25 > albumX && charY + 50 > albumY && charX < albumX + 20 && charY < albumY + 20) {
            //assign quint to be drawn at where the album was last

            quintX = albumX;
            quintY = albumY;
            //check if album matched character, and add points, or deduct lives

            if (albumGroup == character) {
                isCollected = true;
                points++;
            } else {
                lives--;
                isCollected = false;
            }
            //generate new random album

            albumGroup = r.nextInt(3);
            currentX = r.nextInt(3) + albumGroup * 3;
            currentY = r.nextInt(3);
            albumY = r.nextInt(400) + 50;
            albumX = r.nextInt(300) + 150;
            albumTimer.albumTimer.restart();
            repaint();
        }
        //draw quint depending on if album was correct or not

        if (isCollected == true) {
            g.drawImage(albumGot, quintX - 29, quintY - 20, 60, 40, this);
        } else {
            g.drawImage(albumMissed, quintX - 16, quintY - 17, 40, 50, this);
        }
        //draw album

        g.drawImage(albumSheet, albumX, albumY, albumX + 50, albumY + 50,
                currentX * 300, currentY * 300,
                currentX * 300 + 300, currentY * 300 + 300, this);
        //draw outline for album, so it is easier to see

        g.setColor(Color.white);
        g.drawRect(albumX, albumY, 50, 50);
        //draw lives that they have left

        for (int i = 0; i < lives; i++) {
            g.drawImage(lifeCounter, 540, 10 + i * 20, 50, 50, this);
        }
        //draw background so that points can be seen easier

        g.drawRect(120, 10, 70, 20);
        g.fillRect(120, 10, 70, 20);
        //draw points

        g.setColor(Color.black);
        Font myFont = new Font("Century Gothic", Font.BOLD, 14);
        g.setFont(myFont);
        g.drawString("POINTS: " + String.valueOf(points), 122, 25);
        //draw character last, so that it does not get overlapped by anything

        g.drawImage(spriteSheet, charX, charY, charX + 50, charY + 70, 119 * currentFrame + (character * 472),
                180 * currentType,
                119 * currentFrame + (character * 472) + 119,
                180 * currentType + 180, this);
//        //depending on points, increase speed and album frequency
//        
//        if (points < 20) {
//            speed = 6;
//            albumTimer.albumTimer.setDelay(4000);
//            albumDelay = 4000;
//        } else if (points < 30) {
//            speed = 7;
//            albumTimer.albumTimer.setDelay(3500);
//            albumDelay = 3500;
//        }
//        else if(points < 40){
//            speed = 10;
//            albumTimer.albumTimer.setDelay(3000);
//            albumDelay = 3000;
//        }
//        else {
//            
//            speed = 20;
//            albumTimer.albumTimer.setDelay(1750);
//            albumDelay = 1750;
//        }
        if (lives <= 0) {
            //call game over screen when player runs out of lives

            FrmGameOver go = new FrmGameOver();
            go.setVisible(true);
            go.setBounds(500, 200, 495, 525);
            go.setResizable(false);
            this.setVisible(false);
            lives = 3;
            points = 0;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
