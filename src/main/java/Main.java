import graphics.GraphUtills;
import graphics.Toolbar;
import graphics.Field;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setTitle("Game of Life by Fedor Pushkov");

        Field field = new Field();
        JScrollPane scrollPaneField = new JScrollPane(field);
        scrollPaneField.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneField.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel toolbarPanel = field.toolbar.getToolbar();
        JScrollPane scrollPaneToolbar = new JScrollPane(toolbarPanel);
        scrollPaneToolbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPaneToolbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);
        container.add(scrollPaneToolbar, BorderLayout.NORTH);
        container.add(scrollPaneField, BorderLayout.CENTER);

        frame.add(container);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(GraphUtills.JFRAME_WIDTH, GraphUtills.JFRAME_HEIGHT);
        frame.setVisible(true);
    }
}
