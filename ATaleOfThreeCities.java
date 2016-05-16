package com.wordpress.logicprog.ataleofthreecities;

import java.util.Arrays;

public class ATaleOfThreeCities {
	
	public static double connect(int[] ax, int[] ay, int[]bx, int[]by, int[] cx, int[]cy) {
		
		int indexAB = 0;
		int indexAC = 0;
		int indexBC = 0;
		
		// Initialise distance arrays among every city
		double distanceAB[] = new double[ax.length * bx.length];
		for(int i = 0; i < ax.length * bx.length; i++) {distanceAB[i] = 0;}
		
		double distanceAC[] = new double[ax.length * cx.length];
		for(int i = 0; i < ax.length * cx.length; i++) {distanceAC[i] = 0;}
		
		double distanceBC[] = new double[bx.length * cx.length];
		for(int i = 0; i < bx.length * cx.length; i++) {distanceBC[i] = 0;}
		
		// Calculate distance among every possible combinations of cities
		for(int countB = 0; countB < bx.length; countB++) {
			for(int countA = 0; countA < ax.length; countA++) {
				distanceAB[indexAB] = calculate(ax[countA], ay[countA], bx[countB], by[countB]);
				indexAB++;
			}
		}
		
		for(int countC = 0; countC < cx.length; countC++) {
			for(int countA = 0; countA < ax.length; countA++) {
				distanceAC[indexAC] = calculate(ax[countA], ay[countA], cx[countC], cy[countC]);
				indexAC++;
			}
		}
		
		for(int countC = 0; countC < cx.length; countC++) {
			for(int countB = 0; countB < bx.length; countB++) {
				distanceBC[indexBC] = calculate(bx[countB], by[countB], cx[countC], cy[countC]);
				indexBC++;
			}
		}
		
		// Find the most minimum distance between two cities, do it for the 3 possible combinations of cities and put in array
		double[] minDistance = {findMin(distanceAB), findMin(distanceAC), findMin(distanceBC)};
		
		// Find the 2 most minimum distance
		double minimalDis = findMin(minDistance);
		double secondMinimalDis = findSecondMin(minDistance);
		
		// Add them together
		return minimalDis + secondMinimalDis;
		
	}
	
	// Distance between 2 Cartesian Points
	public static double calculate(int x1, int y1, int x2, int y2) {
		double a = Math.pow(x2 - x1, 2);
		double b = Math.pow(y2 - y1, 2);
		double distance = Math.sqrt(a + b);
		
		return distance;
		
	}
	
	// Return the most minimum distance
	public static double findMin(double[] distance) {
		Arrays.sort(distance);
		
		return distance[0];
		
	}
	
	// Return second most minimum distance
	public static double findSecondMin(double[] distance) {
		Arrays.sort(distance);
		
		return distance[1];
		
	}
	
	public static void main(String[] args) {
		
		int[] ax = {0,0,0};
		int[] ay = {0,1,2};
		int[] bx = {2,3};
		int[] by = {1,1};
		int[] cx = {1,5};
		int[] cy = {3,28};
		double min = connect(ax, ay, bx, by, cx, cy);
		System.out.println(min);
		
	}
}
