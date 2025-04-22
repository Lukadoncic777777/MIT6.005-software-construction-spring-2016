/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package turtle;

import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        drawRegularPolygon(turtle,4,sideLength);
        return;
//        throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        return (sides-2)*180.0/sides;
//        throw new RuntimeException("implement me!");
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        return (int)Math.round(360.0/(180.0-angle));
//        throw new RuntimeException("implement me!");
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        double sumDegree=360.0;
        double degree=sumDegree/sides;//正多边形内角公式
        for(int i=1;i<=sides;i++)
        {
            turtle.forward(sideLength);
            turtle.turn(degree);
        }
//        throw new RuntimeException("implement me!");

    }

    /**
     * Given the current direction, current location, and a target location, calculate the heading
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentHeading. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentHeading current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to heading (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateHeadingToPoint(double currentHeading, int currentX, int currentY,
                                                 int targetX, int targetY) {
        /*
        使用向量来计算其夹角。
        * */
        double pi=Math.acos(-1);
        double finalHeading=0.0;
        if(currentY!=targetY)finalHeading=180.0*Math.atan(1.0*(targetX-currentX)/(targetY-currentY))/pi;
        double eps=1e-12;
//        if(finalHeading<0)
//        {
//            finalHeading+=360.0;
//        }
        if(currentX-targetX>0&&finalHeading<-eps)
        {
            finalHeading+=360.0;
        }
        if(currentX-targetX<0&&finalHeading<-eps)
        {
            finalHeading+=180.0;
        }
        if(currentX-targetX>0&&finalHeading>eps)
        {
            finalHeading+=180.0;
        }
        System.out.println("finalHeading="+finalHeading+" currentHeading="+currentHeading);
        if(finalHeading-currentHeading<-eps)
        {
            finalHeading+=360.0;
        }
        return finalHeading-currentHeading;
//        throw new RuntimeException("implement me!");
    }

    /**
     * Given a sequence of points, calculate the heading adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateHeadingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of heading adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateHeadings(List<Integer> xCoords, List<Integer> yCoords) {
//        return (int)(in)
        List<Double> res=new ArrayList<Double>();
        int n= xCoords.size();
        double now=0;
        for(int i=1;i<n;i++)
        {
            double nowturn=calculateHeadingToPoint(now,xCoords.get(i-1),yCoords.get(i-1),xCoords.get(i),yCoords.get(i));
            res.add(nowturn);
            now=nowturn;
        }
        return res;
//                throw new RuntimeException("implement me!");
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        throw new RuntimeException("implement me!");
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

//        drawRegularPolygon(turtle, 5,40);
        drawRegularPolygon(turtle,7,80);

        // draw the window
        turtle.draw();
    }

}
