/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Calculations;
import org.jfree.data.xy.XYSeries;
import view.Graph;
import view.MainMenu;

/**
 *
 * @author Renz
 */
public class Controller {

    MainMenu menuView;
    Graph graphView;
    Calculations model;

    public Controller(MainMenu menuView, Calculations model, Graph graphView) {
        this.menuView = menuView;
        this.model = model;
        this.graphView = graphView;

        this.menuView.addMethodCbListener(new MethodListener());
        this.menuView.addCalculateBtnListener(new CalcListener());
        this.menuView.addGraphBtnListener(new GraphListener());

        this.graphView.addSliderListener(new SliderListener());
    }

    class SliderListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            graphView.numericalDataset.removeAllSeries();
            graphView.ptm.setRowCount(0);
            int iterationAt = graphView.graphSlider.getValue();

            if ("Regula Falsi".equals(menuView.getCbMethod())) {
                plotPolynomial(model.getRoots());
                plotRegulaFalsi(iterationAt);
                
            } 
            else if ("Secant".equals(menuView.getCbMethod())){
                plotPolynomial(model.getxValues());
                plotSecant(iterationAt + 1);

            }
            else if ("Bisection".equals(menuView.getCbMethod())){
                plotPolynomial(model.getRoots());
                plotBisection(iterationAt);
            }
        }
    }

    class GraphListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setMaximumSlider();
            graphView.setVisible(true);
        }
    }

    class MethodListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            menuView.hideTextFields();
            if ("Regula Falsi".equals(menuView.getCbMethod())) {
                menuView.tpTable.setSelectedIndex(0);
            } 
            else if("Secant".equals(menuView.getCbMethod())){
                menuView.tpTable.setSelectedIndex(1);
            }
            else if("Bisection".equals(menuView.getCbMethod())){
                menuView.tpTable.setSelectedIndex(2);
            }
            else if("Newton's".equals(menuView.getCbMethod())){
                menuView.tpTable.setSelectedIndex(3);
            }
        }
    }

    class CalcListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            graphView.numericalDataset.removeAllSeries();
            graphView.ptm.setRowCount(0);
            graphView.graphSlider.setValue(0);

            if ("Regula Falsi".equals(menuView.getCbMethod())) {
                menuView.rfTableModel.setRowCount(0);
                model.solveRegula(menuView.getTxtPolynomial(), menuView.getIntervals(), menuView.getTxtStopValue(), menuView.getCbStopType());

                //Display table results
                for (int i = 0; i < model.getX0().size() - 1; i++) {
                    menuView.rfAddRow(i + 1, model.getX0().get(i), model.getX1().get(i), model.getX2().get(i), model.getY0().get(i), model.getY1().get(i), model.getY2().get(i), model.getRegulaError().get(i));
                }

                plotPolynomial(model.getRoots());
                plotRegulaFalsi(0);

            }//Secant Method here:
            else if ("Secant".equals(menuView.getCbMethod())){
                menuView.secantTableModel.setRowCount(0);
                model.solveSecant(menuView.getTxtPolynomial(), menuView.getPoints(), menuView.getTxtStopValue(), menuView.getCbStopType());

                //Display table results
                for (int i = 0; i < model.getxValues().size() - 1; i++) {
                    menuView.secantAddRow(i, model.getxValues().get(i), model.getyValues().get(i), model.getSecantError().get(i));
                }

                plotPolynomial(model.getxValues());
                plotSecant(1);
            }
            else if ("Bisection".equals(menuView.getCbMethod())) {
                menuView.bisectionTableModel.setRowCount(0);
                model.solveBisection(menuView.getTxtPolynomial(), menuView.getIntervals(), menuView.getTxtStopValue(), menuView.getCbStopType());

                //Display table results
                for (int i = 0; i < model.getX0().size() - 1; i++) {
                    menuView.bisectionAddRow(i + 1, model.getX0().get(i), model.getX1().get(i), model.getX2().get(i), model.getY0().get(i), model.getY1().get(i), model.getY2().get(i), model.getBisectionError().get(i));
                }
                
                plotPolynomial(model.getRoots());
                plotBisection(0);

            }
            else if ("Newton's".equals(menuView.getCbMethod())){
                menuView.newtonsTableModel.setRowCount(0);
                model.solveNewtons(menuView.getTxtPolynomial(), menuView.getNewtonsPoint(), menuView.getTxtStopValue(), menuView.getCbStopType());

                //Display table results
                for (int i = 0; i < model.getxValues().size() - 1; i++) {
                    menuView.newtonsAddRow(i, model.getxValues().get(i), model.getyValues().get(i), model.getNewtonsError().get(i));
                }

                plotPolynomial(model.getxValues());
                plotNewtons(1);
            }
        }
    }

    public void plotPolynomial(ArrayList<Double> roots) {
        XYSeries polynomialSeries = new XYSeries("F(X) / Polynomial");
        double a = Collections.min(roots) - 1;
        double b = Collections.max(roots) + 1;
        while (a <= b) {
            //Add series of points to graph
            polynomialSeries.add(a, model.fOfX(menuView.getTxtPolynomial(), a));
            graphView.polyAddRow(a, model.fOfX(menuView.getTxtPolynomial(), a));
            a += 0.25;
        }
        graphView.numericalDataset.addSeries(polynomialSeries);
    }

    public void plotRegulaFalsi(int index) {
        XYSeries rfSeries = new XYSeries("Iteration: " + (index + 1));
        rfSeries.add(model.getX0().get(index).doubleValue(), model.fOfX(menuView.getTxtPolynomial(), model.getX0().get(index).doubleValue()));
        rfSeries.add(model.getX1().get(index).doubleValue(), model.fOfX(menuView.getTxtPolynomial(), model.getX1().get(index).doubleValue()));
        rfSeries.add(model.getX2().get(index).doubleValue(), 0);
        graphView.numericalDataset.addSeries(rfSeries);
    }

    public void plotSecant(int index) {
        XYSeries secantSeries = new XYSeries("Iteration: " + (index));
        secantSeries.add(model.getxValues().get(index).doubleValue(), model.fOfX(menuView.getTxtPolynomial(), model.getxValues().get(index).doubleValue()));
        secantSeries.add(model.getxValues().get(index - 1).doubleValue(), model.fOfX(menuView.getTxtPolynomial(), model.getxValues().get(index - 1).doubleValue()));
        secantSeries.add(model.getxValues().get(index + 1).doubleValue(), 0);
        graphView.numericalDataset.addSeries(secantSeries);

    }
    
    public void plotBisection(int index){
        XYSeries bisectionSeries = new XYSeries("Iteration: " + (index + 1));
        bisectionSeries.add(model.getX0().get(index).doubleValue(), model.fOfX(menuView.getTxtPolynomial(), model.getX0().get(index).doubleValue()));
        bisectionSeries.add(model.getX1().get(index).doubleValue(), model.fOfX(menuView.getTxtPolynomial(), model.getX1().get(index).doubleValue()));
        bisectionSeries.add(model.getX2().get(index).doubleValue(), 0);
        graphView.numericalDataset.addSeries(bisectionSeries);
    }
    
    public void plotNewtons(int index){
        XYSeries newtonsSeries = new XYSeries("Iteration: " + (index));
        newtonsSeries.add(model.getxValues().get(index).doubleValue(), model.fOfX(menuView.getTxtPolynomial(), model.getxValues().get(index).doubleValue()));
        newtonsSeries.add(model.getxValues().get(index - 1).doubleValue(), model.fOfX(menuView.getTxtPolynomial(), model.getxValues().get(index - 1).doubleValue()));
        newtonsSeries.add(model.getxValues().get(index + 1).doubleValue(), 0);
        graphView.numericalDataset.addSeries(newtonsSeries);
    }


    public void setMaximumSlider() {
        if ("Regula Falsi".equals(menuView.getCbMethod())) {
            graphView.graphSlider.setMaximum(model.getX0().size() - 2);
        } 
        else if ("Secant".equals(menuView.getCbMethod())){
            graphView.graphSlider.setMaximum(model.getxValues().size() - 4);
        }
        else if ("Bisection".equals(menuView.getCbMethod())) {
            graphView.graphSlider.setMaximum(model.getX0().size() - 2);
        } 
    }
}