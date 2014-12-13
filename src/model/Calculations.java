package model;

import java.util.ArrayList;

public class Calculations {

    //Declarations for Regula Falsi and Bisection
    private ArrayList<Double> x0 = new ArrayList<>();
    private ArrayList<Double> x1 = new ArrayList<>();
    private ArrayList<Double> x2 = new ArrayList<>();
    private ArrayList<Double> y0 = new ArrayList<>();
    private ArrayList<Double> y1 = new ArrayList<>();
    private ArrayList<Double> y2 = new ArrayList<>();
    private ArrayList<Double> roots = new ArrayList<>();
    //Declarations for Secant Method
    private ArrayList<Double> xValues = new ArrayList<>();
    private ArrayList<Double> yValues = new ArrayList<>();
	//Declarations for Errors
    private ArrayList<Double> regulaError = new ArrayList<>();
    private ArrayList<Double> bisectionError = new ArrayList<>();
    private ArrayList<Double> secantError = new ArrayList<>();
    private ArrayList<Double> newtonsError = new ArrayList<>();
    
    public double fOfX(ArrayList<Double> polynomial, double x) {
        int i = 0;
        double answer = 0.0;
        while (i < polynomial.size()) {
            answer += (polynomial.get(i) * Math.pow(x, polynomial.get(i + 1)));
            i += 2;
        }

        return answer;
    }
<<<<<<< HEAD
<<<<<<< HEAD
    
    public double fOfXPrime(ArrayList<Double> polynomial, double x) {
        ArrayList<Double> array = new ArrayList<>();
        int i=0;
        while (i < polynomial.size()) {
           if((polynomial.get(i) * polynomial.get(i+1))!=0){
               array.add(polynomial.get(i) * polynomial.get(i+1));
               array.add(polynomial.get(i+1) - 1);
           }
            i += 2;
        }
        return fOfX(array, x);
    }
	
    public double fOfXDoublePrime(ArrayList<Double> polynomial, double x) {
        ArrayList<Double> array = new ArrayList<>();
        int i=0;
        while (i < polynomial.size()) {
            if((polynomial.get(i) * polynomial.get(i+1))!=0){
                array.add(polynomial.get(i) * polynomial.get(i+1));
                array.add(polynomial.get(i+1) - 1);
            }
            i += 2;
        }
        double ans = fOfXPrime(array, x);
        return ans;
    }
=======
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
=======
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
	
    //Bisection Method
    public void solveBisection(ArrayList<Double> polynomial, ArrayList<Double> interval, double stopVal, String type) {
        clearBisection();

        //initialize intervals to be used
        x0.add(interval.get(0));
        x1.add(interval.get(1));
        //add roots for plotting, arrangement does not matter. Graph auto arranges.
        roots.add(interval.get(0));
        roots.add(interval.get(1));
        switch (type) {
            case "Iteration":
                for (int i = 0; i < stopVal; i++) {
                    
                    if(i == 0){
                        y0.add(fOfX(polynomial, x0.get(i)));
                        y1.add(fOfX(polynomial, x1.get(i)));
                    }
                    x2.add((x0.get(i) + x1.get(i))/2);
                    y2.add(fOfX(polynomial, x2.get(i)));

                    //First error is automatic NaN.
                    if (i == 0) {
                        bisectionError.add(0.0);
                    } else {
                        double error = (x1.get(i) - x0.get(i)) / 2;
                        bisectionError.add(Math.abs(error));
                    }

<<<<<<< HEAD
<<<<<<< HEAD
                    //if a is neg, b is pos, c is neg
                    if(y0.get(i) < 0 && y1.get(i) > 0 && y2.get(i) < 0){
                        x0.add(x2.get(i));
                        y0.add(y2.get(i));
                        x1.add(x1.get(i));
                        y1.add(y1.get(i));
                    }
                    //if a is pos, b is neg, c is neg
                    else if(y0.get(i) > 0 && y1.get(i) < 0 && y2.get(i) < 0){
                        x0.add(x0.get(i));
                        y0.add(y0.get(i));
                        x1.add(x2.get(i));
                        y1.add(y2.get(i));
                    }
                    //if c is 0
                    else if (y2.get(i) == 0){
                        i = (int) stopVal + 1;
                    }
                    //if a*b is pos
                    else if (y0.get(i) * y1.get(i) > 0){
                            System.out.println("Intervals must be of different signs.");
                    }

                    roots.add(x2.get(i));

=======
					if(y2.get(i) < 0 && y0.get(i) < 0 || y2.get(i) > 0 && y0.get(i) > 0){
						x0.add(x2.get(i));
						y0.add(y2.get(i));
						x1.add(x1.get(i));
						y1.add(y1.get(i));
					}
					else if(y2.get(i) < 0 && y1.get(i) < 0 || y2.get(i) > 0 && y1.get(i) > 0){
						x0.add(x0.get(i));
						y0.add(y0.get(i));
						x1.add(x2.get(i));
						y1.add(y2.get(i));
					}
					else if (y2.get(i) == 0){
						i = (int) stopVal + 1;
					}
				
					roots.add(x2.get(i));

>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
=======
					if(y2.get(i) < 0 && y0.get(i) < 0 || y2.get(i) > 0 && y0.get(i) > 0){
						x0.add(x2.get(i));
						y0.add(y2.get(i));
						x1.add(x1.get(i));
						y1.add(y1.get(i));
					}
					else if(y2.get(i) < 0 && y1.get(i) < 0 || y2.get(i) > 0 && y1.get(i) > 0){
						x0.add(x0.get(i));
						y0.add(y0.get(i));
						x1.add(x2.get(i));
						y1.add(y2.get(i));
					}
					else if (y2.get(i) == 0){
						i = (int) stopVal + 1;
					}
				
					roots.add(x2.get(i));

>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
                }
                break;
            case "TOL":
                int i = 0;
<<<<<<< HEAD
<<<<<<< HEAD
                while (Math.abs((x1.get(i) - x0.get(i)) / 2) > stopVal) {
=======
                while (Math.abs(x1.get(i) - x0.get(i)) / 2) > stopVal) {
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
=======
                while (Math.abs(x1.get(i) - x0.get(i)) / 2) > stopVal) {
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
                    y0.add(fOfX(polynomial, x0.get(i)));
                    y1.add(fOfX(polynomial, x1.get(i)));

                    x2.add((x0.get(i) + x1.get(i))/2);
                    y2.add(fOfX(polynomial, x2.get(i)));
					
                    if (i == 0) {
                        bisectionError.add(0.0);
                    } else {
                        double error = (x1.get(i) - x0.get(i)) / 2;
                        bisectionError.add(Math.abs(error));
<<<<<<< HEAD
<<<<<<< HEAD
                    }
					
					//if a is neg, b is pos, c is neg
                    if(y0.get(i) < 0 && y1.get(i) > 0 && y2.get(i) < 0){
                        x0.add(x2.get(i));
                        y0.add(y2.get(i));
                        x1.add(x1.get(i));
                        y1.add(y1.get(i));
                    }
					//if a is pos, b is neg, c is neg
                    else if(y0.get(i) > 0 && y1.get(i) < 0 && y2.get(i) < 0){
                        x0.add(x0.get(i));
                        y0.add(y0.get(i));
                        x1.add(x2.get(i));
                        y1.add(y2.get(i));
                    }
                    //if c is 0
                    else if (y2.get(i) == 0){
                        break;
                    }
                    //if a*b is pos
                    else if (y0.get(i) * y1.get(i) > 0){
                            System.out.println("Intervals must be of different signs.");
                    }
=======
                    }
=======
                    }
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL

                    if(y2.get(i) < 0 && y0.get(i) < 0 || y2.get(i) > 0 && y0.get(i) > 0){
						x0.add(x2.get(i));
						y0.add(y2.get(i));
						x1.add(x1.get(i));
						y1.add(y1.get(i));
					}
					else if(y2.get(i) < 0 && y1.get(i) < 0 || y2.get(i) > 0 && y1.get(i) > 0){
						x0.add(x0.get(i));
						y0.add(y0.get(i));
						x1.add(x2.get(i));
						y1.add(y2.get(i));
					}
					else if (y2.get(i) == 0){
						break;
					}
<<<<<<< HEAD
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
=======
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
					
                    roots.add(x2.get(i));
                    i++;
                }
                break;
        }
    }
    
	//Newton's
    public void solveNewtons(ArrayList<Double> polynomial, ArrayList<Double> points, double stopVal, String type) {
        clearNewtons();
<<<<<<< HEAD
<<<<<<< HEAD
        
        //initialize points to be used
        xValues.add(points.get(0));
        yValues.add(fOfX(polynomial, xValues.get(0)));
        
        double nextPoint = 0.0;
        switch (type) {
            case "Iteration":
                for (int i = 1; i <= stopVal; i++) {
                    nextPoint = xValues.get(i-1) - (fOfX(polynomial, xValues.get(i-1)) / fOfXPrime(polynomial, xValues.get(i - 1)));
                    System.out.println(nextPoint);
=======

        //initialize points to be used
=======

        //initialize points to be used
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
        /*xValues.add(points.get(0));
        xValues.add(points.get(1));
        yValues.add(fOfX(polynomial, xValues.get(0)));
        yValues.add(fOfX(polynomial, xValues.get(1)));
        
        double nextPoint = 0.0;*/
        switch (type) {
            case "Iteration":
              /*  for (int i = 1; i < stopVal + 1; i++) {
                    nextPoint = xValues.get(i) - ((fOfX(polynomial, xValues.get(i)) * (xValues.get(i - 1) - xValues.get(i))) / (fOfX(polynomial, xValues.get(i - 1)) - fOfX(polynomial, xValues.get(i))));
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
                    xValues.add(nextPoint);
                    yValues.add(fOfX(polynomial, xValues.get(i)));
                    
                    if (i == 1) {
                        newtonsError.add(0.0);
                        newtonsError.add(0.0);
                    } else {
<<<<<<< HEAD
                        double error = fOfX(polynomial, xValues.get(i)) * fOfXDoublePrime(polynomial, xValues.get(i)) / Math.pow(fOfXPrime(polynomial, xValues.get(i)), 2);
                        newtonsError.add(Math.abs(error));
                    }
                    
                    //if error is 0
                    if (fOfX(polynomial, xValues.get(i)) * fOfXDoublePrime(polynomial, xValues.get(i)) / Math.pow(fOfXPrime(polynomial, xValues.get(i)), 2) == 0){
                        i = (int) stopVal + 1;
                    }
		}
               
                break;
            case "TOL":
                int i = 1;
                while (fOfX(polynomial, xValues.get(i)) * fOfXDoublePrime(polynomial, xValues.get(i)) / Math.pow(fOfXPrime(polynomial, xValues.get(i)), 2) > stopVal) {
                    nextPoint =  xValues.get(i-1) - (fOfX(polynomial, xValues.get(i-1)) / fOfXPrime(polynomial, xValues.get(i - 1)));
=======
                        double error = (xValues.get(i + 1) - xValues.get(i)) / xValues.get(i + 1);
                        newtonsError.add(Math.abs(error));
                    }
                }*/
                for(int i = 1; i < stopVal + 1; i++){
                    xValues.add(1.5);
                    yValues.add(2.3);
                    newtonsError.add(0.0);
                }
                break;
            case "TOL":
                /*int i = 1;
                while (!(Math.abs(xValues.get(i) - xValues.get(i - 1)) <= stopVal)) {
                    nextPoint = xValues.get(i) - ((fOfX(polynomial, xValues.get(i)) * (xValues.get(i - 1) - xValues.get(i))) / (fOfX(polynomial, xValues.get(i - 1)) - fOfX(polynomial, xValues.get(i))));
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
                    xValues.add(nextPoint);
                    yValues.add(fOfX(polynomial, xValues.get(i + 1)));
                    if (i == 1) {
                        newtonsError.add(0.0);
                        newtonsError.add(0.0);
                    } else {
<<<<<<< HEAD
                        double error = fOfX(polynomial, xValues.get(i)) * fOfXDoublePrime(polynomial, xValues.get(i)) / Math.pow(fOfXPrime(polynomial, xValues.get(i)), 2);
=======
                        double error = (xValues.get(i + 1) - xValues.get(i)) / xValues.get(i + 1);
<<<<<<< HEAD
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
=======
>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
                        newtonsError.add(Math.abs(error));
                    }
                    i++;
                }*/
                for(int i = 1; i < stopVal + 1; i++){
                    xValues.add(1.5);
                    yValues.add(2.3);
                    newtonsError.add(0.0);
                }
                break;

        }
    }
	
	//Regula Falsi
    public void solveRegula(ArrayList<Double> polynomial, ArrayList<Double> interval, double stopVal, String type) {
        clearRegula();

        //initialize intervals to be used
        x0.add(interval.get(0));
        x1.add(interval.get(1));
        //add roots for plotting, arrangement does not matter. Graph auto arranges.
        roots.add(interval.get(0));
        roots.add(interval.get(1));
        switch (type) {
            case "Iteration":
                for (int i = 0; i < stopVal; i++) {
                    y0.add(fOfX(polynomial, x0.get(i)));
                    y1.add(fOfX(polynomial, x1.get(i)));

                    x2.add(((x0.get(i) * y1.get(i)) - (x1.get(i) * y0.get(i))) / (y1.get(i) - y0.get(i)));
                    y2.add(fOfX(polynomial, x2.get(i)));

                    //First error is automatic NaN.
                    if (i == 0) {
                        regulaError.add(0.0);
                    } else {
                        double error = (x2.get(i - 1) - x2.get(i)) / x2.get(i);
                        regulaError.add(Math.abs(error));
                    }

                    if (y2.get(i) == 0) {
                        break;
                    }

                    if ((y1.get(i) * y2.get(i)) < 0) {
                        x0.add(x2.get(i));
                        x1.add(x1.get(i));
                    } else {
                        x0.add(x0.get(i));
                        x1.add(x2.get(i));
                    }

                    roots.add(x2.get(i));
                }
                break;
            case "TOL":
                int i = 0;
                while (Math.abs(x1.get(i) - x0.get(i)) > stopVal) {
                    y0.add(fOfX(polynomial, x0.get(i)));
                    y1.add(fOfX(polynomial, x1.get(i)));

                    x2.add(((x0.get(i) * y1.get(i)) - (x1.get(i) * y0.get(i))) / (y1.get(i) - y0.get(i)));
                    y2.add(fOfX(polynomial, x2.get(i)));
                    if (i == 0) {
                        regulaError.add(0.0);
                    } else {
                        double error = (x2.get(i - 1) - x2.get(i)) / x2.get(i);
                        regulaError.add(Math.abs(error));
<<<<<<< HEAD
                    }

                    if (y2.get(i) == 0) {
                        break;
                    }

=======
                    }

                    if (y2.get(i) == 0) {
                        break;
                    }

>>>>>>> parent of 3f5ddf2... Fixed Bisection TOL and graph and Regula Falsi TOL
                    if ((y1.get(i) * y2.get(i)) < 0) {
                        x0.add(x2.get(i));
                        x1.add(x1.get(i));
                    } else {
                        x0.add(x0.get(i));
                        x1.add(x2.get(i));
                    }
                    roots.add(x2.get(i));
                    i++;
                }
                break;
        }
    }

	//Secant
    public void solveSecant(ArrayList<Double> polynomial, ArrayList<Double> points, double stopVal, String type) {
        clearSecant();

        //initialize points to be used
        xValues.add(points.get(0));
        xValues.add(points.get(1));
        yValues.add(fOfX(polynomial, xValues.get(0)));
        yValues.add(fOfX(polynomial, xValues.get(1)));

        double nextPoint = 0.0;
        switch (type) {
            case "Iteration":
                for (int i = 1; i < stopVal + 1; i++) {
                    nextPoint = xValues.get(i) - ((fOfX(polynomial, xValues.get(i)) * (xValues.get(i - 1) - xValues.get(i))) / (fOfX(polynomial, xValues.get(i - 1)) - fOfX(polynomial, xValues.get(i))));
                    xValues.add(nextPoint);
                    yValues.add(fOfX(polynomial, xValues.get(i + 1)));
                    if (i == 1) {
                        secantError.add(0.0);
                        secantError.add(0.0);
                    } else {
                        double error = (xValues.get(i + 1) - xValues.get(i)) / xValues.get(i + 1);
                        secantError.add(Math.abs(error));
                    }
                }
                break;
            case "TOL":
                int i = 1;
                while (Math.abs(xValues.get(i) - xValues.get(i - 1)) > stopVal) {
                    nextPoint = xValues.get(i) - ((fOfX(polynomial, xValues.get(i)) * (xValues.get(i - 1) - xValues.get(i))) / (fOfX(polynomial, xValues.get(i - 1)) - fOfX(polynomial, xValues.get(i))));
                    xValues.add(nextPoint);
                    yValues.add(fOfX(polynomial, xValues.get(i + 1)));
                    if (i == 1) {
                        secantError.add(0.0);
                        secantError.add(0.0);
                    } else {
                        double error = (xValues.get(i + 1) - xValues.get(i)) / xValues.get(i + 1);
                        secantError.add(Math.abs(error));
                    }
                    i++;
                }

                break;

        }
    }

    public void clearRegula() {
        x0.clear();
        x1.clear();
        x2.clear();
        y0.clear();
        y1.clear();
        y2.clear();
        regulaError.clear();
        roots.clear();
    }

    public void clearSecant() {
        xValues.clear();
        yValues.clear();
        secantError.clear();
    }
    
    public void clearBisection() {
        x0.clear();
        x1.clear();
        x2.clear();
        y0.clear();
        y1.clear();
        y2.clear();
        bisectionError.clear();
        roots.clear();
    }
    
    public void clearNewtons() {
        xValues.clear();
        yValues.clear();
        newtonsError.clear();
    }

    public ArrayList<Double> getX0() {
        return x0;
    }

    public ArrayList<Double> getX1() {
        return x1;
    }

    public ArrayList<Double> getX2() {
        return x2;
    }

    public ArrayList<Double> getY0() {
        return y0;
    }

    public ArrayList<Double> getY1() {
        return y1;
    }

    public ArrayList<Double> getY2() {
        return y2;
    }

    public ArrayList<Double> getRoots() {
        return roots;
    }

    public ArrayList<Double> getxValues() {
        return xValues;
    }

    public ArrayList<Double> getyValues() {
        return yValues;
    }

    public ArrayList<Double> getRegulaError() {
        return regulaError;
    }
    
    public ArrayList<Double> getSecantError() {
        return secantError;
    }
    
    public ArrayList<Double> getBisectionError() {
        return bisectionError;
    }
    
    public ArrayList<Double> getNewtonsError() {
        return newtonsError;
    }
}
