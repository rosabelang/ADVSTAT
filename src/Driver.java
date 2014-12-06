
import controller.Controller;
import model.Calculations;
import view.Graph;
import view.MainMenu;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Renz
 */
public class Driver {
    
    public static void main(String[] args){
        Calculations theModel = new Calculations();
        MainMenu menuView = new MainMenu();
        Graph graphView = new Graph();
        Controller theController = new Controller(menuView, theModel, graphView);
        
        menuView.setVisible(true);
        
    }
    
}
