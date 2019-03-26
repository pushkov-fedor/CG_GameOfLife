package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Cell {

    private boolean isAlive;
    private ArrayList<Cell> neighbours;
    private double impact;

    public Cell(){
        isAlive = false;
        neighbours = new ArrayList<>(ModelUtills.NEIGHBOURS_NUMBER);
        impact = 0;
    }

    public Cell(boolean isAlive){
        super();
        this.isAlive = isAlive;
    }

    public double getImpact() {
        return impact;
    }

    public void setNeighbours(ArrayList<Cell> neighbours) {
        this.neighbours = neighbours;
    }

    public void setImpact(){
        impact = 0;
        Iterator<Cell> neighboursIterator = neighbours.iterator();
        int[] neighboursTypePattern = ModelUtills.neighboursTypePattern;
        int i = 0;
        while(neighboursIterator.hasNext()){
            Cell neighbour = neighboursIterator.next();
            int neighbourType = neighboursTypePattern[i];
            if(neighbour.isAlive()){
                if(neighbourType==1){
                    impact += ModelUtills.FST_IMPACT;
                } else {
                    impact += ModelUtills.SND_IMPACT;
                }
            }
            i++;
        }
    }

    public void nextState(){
        if(isAlive == false && ModelUtills.BIRTH_BEGIN <= impact && impact <= ModelUtills.BIRTH_END){
            this.makeAlive();
            return;
        }
        if(isAlive == true && ModelUtills.LIVE_BEGIN <= impact && impact <= ModelUtills.LIVE_END){
            return;
        }
        if(isAlive == true && impact < ModelUtills.LIVE_BEGIN){
            this.makeDead();
        }
        if(isAlive == true && impact > ModelUtills.LIVE_END){
            this.makeDead();
        }
    }

    public void makeAlive(){
        isAlive = true;
    }

    public void makeDead() {
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
