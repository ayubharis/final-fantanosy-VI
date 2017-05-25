/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FinalFantanosyVI;

import javafx.application.Application;
import javafx.stage.Stage;

/*
 * ICS3U CULMINATING ASSIGNMENT: FINAL FANTANOSY VI
 * AUTHORS: HARIS AYUB, JESSE WANG, TIM HE
 * DATE: JANUARY 23RD 2017
 */

public class startProgram extends Application {


    @Override
    public void start(Stage primaryStage) {
        //call title screen
        
        FrmStartMenu myM = new FrmStartMenu();
        myM.setVisible(true);
        myM.setResizable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
