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

					if((y2.get(i) < 0 && y0.get(i) < 0) || (y2.get(i) >= 0 && y0.get(i) >= 0)){
						x0.add(x2.get(i));
						y0.add(y2.get(i));
						x1.add(x1.get(i));
						y1.add(y1.get(i));
					}
					else if((y2.get(i) < 0 && y1.get(i) < 0) || (y2.get(i) >= 0 && y1.get(i) >= 0)){
						x0.add(x0.get(i));
						y0.add(y0.get(i));
						x1.add(x2.get(i));
						y1.add(y2.get(i));
					}
					else if (y2.get(i) == 0){
						i = (int) stopVal + 1;
					}
				
					roots.add(x2.get(i));

                }
                break;
            case "TOL":
                int i = 0;
                while (!(Math.abs(x1.get(i) - x0.get(i)) / 2) <= stopVal)) {
                    y0.add(fOfX(polynomial, x0.get(i)));
                    y1.add(fOfX(polynomial, x1.get(i)));

                    x2.add((x0.get(i) + x1.get(i))/2);
                    y2.add(fOfX(polynomial, x2.get(i)));
					
                    if (i == 0) {
                        bisectionError.add(0.0);
                    } else {
                        double error = (x1.get(i) - x0.get(i)) / 2;
                        bisectionError.add(Math.abs(error));
                    }

                    if((y2.get(i) < 0 && y0.get(i) < 0) || (y2.get(i) >= 0 && y0.get(i) >= 0)){
						x0.add(x2.get(i));
						y0.add(y2.get(i));
						x1.add(x1.get(i));
						y1.add(y1.get(i));
					}
					else if((y2.get(i) < 0 && y1.get(i) < 0) || (y2.get(i) >= 0 && y0.get(i) >= 0)){
						x0.add(x0.get(i));
						y0.add(y0.get(i));
						x1.add(x2.get(i));
						y1.add(y2.get(i));
					}
					else if (y2.get(i) == 0){
						break;
					}
					
                    roots.add(x2.get(i));
                    i++;
                }
                break;
        }