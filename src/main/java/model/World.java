package model;

import Utilities.GaluaField;
import Utilities.Pair;

import java.util.ArrayList;
import java.util.Iterator;

public class World {
    public int hexInRow;
    public int hexInCol;
    private ArrayList<Cell> field;

    public World(int hexInRow, int hexInCol){
        this.hexInRow = hexInRow;
        this.hexInCol = hexInCol;

        field = new ArrayList<>();
        for(int i = 0; i < hexInRow * hexInCol; i++){
            field.add(new Cell());
        }
        meetNeighbours();
        for(int i = 0; i < hexInRow * hexInCol; i++){
            field.get(i).setImpact();
        }
    }

    public ArrayList<Cell> getField() {
        return field;
    }

    public Cell getCell(int x, int y){
        return field.get(y*hexInRow + x);
    }

    public int getHexInCol() {
        return hexInCol;
    }

    public int getHexInRow() {
        return hexInRow;
    }

    public void setAliveCell(int x, int y){
        field.get(y * hexInRow + x).makeAlive();
    }

    public void setDeadCell(int x, int y){
        field.get(y * hexInRow + x).makeDead();
    }

    private void meetNeighbours(){
        int galuaDimension = field.size();
//        System.out.println(hexInRow);
        for(int i = 0; i < field.size(); i++){
            ArrayList<Cell> neighbours = new ArrayList<>();
            Pair<Integer, Integer> coord = getCoordFromNumber(i);
            int posY = coord.getSecond();
            int dI;
            int currentI = i - 2*hexInRow;
            if(posY % 2 == 0) dI = hexInRow - 2; else dI = hexInRow - 1;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            currentI+=dI;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            currentI++;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            currentI++;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            currentI++;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            if(posY % 2 == 0) dI = hexInRow - 2; else dI = hexInRow - 3;
            currentI+=dI;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            currentI+=2;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            if(posY % 2 == 0) dI = hexInRow - 3; else dI = hexInRow - 2;
            currentI+=dI;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            currentI++;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            currentI++;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            currentI++;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            if(posY % 2 == 0) dI = hexInRow - 1; else dI = hexInRow - 2;
            currentI+=dI;
            neighbours.add(field.get(GaluaField.getNumber(galuaDimension, currentI)));
            field.get(i).setNeighbours(neighbours);
        }
    }

    public void nextGeneration(){
//        calculateImpact();
        for(int i = 0; i < field.size(); i++){
            field.get(i).nextState();
        }
    }

    public void calculateImpact(){
        for(int i = 0; i < field.size(); i++){
            field.get(i).setImpact();
        }
    }

    public void resetWorld(){
        Iterator<Cell> iterator = field.iterator();
        while(iterator.hasNext()){
            Cell cell = iterator.next();
            cell.makeDead();
        }
    }

    private Pair<Integer, Integer> getCoordFromNumber(int i){
        int y = i / hexInRow;
        int x = i % hexInRow;
        return new Pair<>(x, y);
    }





}
