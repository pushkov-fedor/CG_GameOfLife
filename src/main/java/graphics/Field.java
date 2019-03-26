package graphics;

import Utilities.Mode;
import Utilities.Pair;
import model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Field extends JPanel implements MouseListener, MouseMotionListener {

    public World world;
    public Toolbar toolbar;
    public Mode mode;


    public Field(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        toolbar = new Toolbar();
        mode = Mode.Replace;
        world = new World(GraphUtills.HEXAGONS_IN_ROW, GraphUtills.HEXAGONS_IN_COL);
        toolbar.setWorld(world);
        toolbar.setField(this);
        toolbar.setObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        GraphUtills.drawWorld(world, g, this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickedPoint = e.getPoint();
        int x = clickedPoint.x;
        int y = clickedPoint.y;
        if(GraphUtills.isPointInHexagonField(x, y, GraphUtills.BUFFERED_IMAGE)) {
            Pair<Integer, Integer> hexogon = GraphUtills.getSelectedHexagon(x, y);
            if(mode == Mode.Replace) {
                world.setAliveCell(hexogon.getFirst(), hexogon.getSecond());
            } else {
                boolean isAlive = world.getCell(hexogon.getFirst(), hexogon.getSecond()).isAlive();
                if(isAlive){
                    world.setDeadCell(hexogon.getFirst(), hexogon.getSecond());
                } else {
                    world.setAliveCell(hexogon.getFirst(), hexogon.getSecond());
                }
            }
            world.calculateImpact();
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if(x >= 0 && y >= 0 && x <= GraphUtills.IMAGE_WIDTH && y <= GraphUtills.IMAGE_HEIGHT) {
            if(GraphUtills.isPointInHexagonField(x, y, GraphUtills.BUFFERED_IMAGE)) {
                Pair<Integer, Integer> hexogon = GraphUtills.getSelectedHexagon(x, y);
                if(mode == Mode.Replace) {
                    world.setAliveCell(hexogon.getFirst(), hexogon.getSecond());
                } else {
                    boolean isAlive = world.getCell(hexogon.getFirst(), hexogon.getSecond()).isAlive();
                    if(isAlive){
                        world.setDeadCell(hexogon.getFirst(), hexogon.getSecond());
                    } else {
                        world.setAliveCell(hexogon.getFirst(), hexogon.getSecond());
                    }
                }
                world.calculateImpact();
                repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
