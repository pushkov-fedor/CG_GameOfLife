package graphics;

import graphics.GraphUtills;
import model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;

import static java.lang.Math.sqrt;

public class Options extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSlider sliderWidth;
    private JSlider sliderHeight;
    private JLabel minWidth;
    private JLabel maxWidth;
    private JLabel currentWidth;
    private JLabel minHeight;
    private JLabel currentHeight;
    private JLabel maxHeight;
    private JSlider sliderHexRadius;
    private JLabel minHexRadius;
    private JLabel maxHexRadius;
    private JLabel currentHexRadius;
    private JSlider sliderLineWidth;
    private JLabel minLineWidth;
    private JLabel maxLineWidth;
    private JLabel currentLineWidth;
    private JSlider sliderLB;
    private JLabel minLB;
    private JLabel currentLB;
    private JLabel maxLB;
    private JSlider sliderLE;
    private JLabel minLE;
    private JLabel currentLE;
    private JLabel maxLE;
    private JSlider sliderBB;
    private JLabel minBB;
    private JLabel currentBB;
    private JLabel maxBB;
    private JSlider sliderBE;
    private JLabel minBE;
    private JLabel currentBE;
    private JLabel maxBE;
    private JSlider sliderFI;
    private JLabel minFI;
    private JLabel currentFI;
    private JLabel maxFI;
    private JSlider sliderSI;
    private JLabel minSI;
    private JLabel currentSI;
    private JLabel maxSI;

    private World world;
    private Field field;
    private ImageObserver observer;

    public Options(World world, Field field, ImageObserver observer) {
        this.world = world;
        this.field = field;
        this.observer = observer;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        configureSliderLabels(minWidth, maxWidth, currentWidth, sliderWidth);
        currentWidth.setText(String.valueOf(GraphUtills.HEXAGONS_IN_ROW));
        sliderWidth.setValue(GraphUtills.HEXAGONS_IN_ROW);
        configureSliderLabels(minHeight, maxHeight, currentHeight, sliderHeight);
        currentHeight.setText(String.valueOf(GraphUtills.HEXAGONS_IN_COL));
        sliderHeight.setValue(GraphUtills.HEXAGONS_IN_COL);
        configureSliderLabels(minHexRadius, maxHexRadius, currentHexRadius, sliderHexRadius);
        currentHexRadius.setText(String.valueOf(GraphUtills.HEXAGON_RADIUS));
        sliderHexRadius.setValue(GraphUtills.HEXAGON_RADIUS);
        configureSliderLabels(minLineWidth, maxLineWidth, currentLineWidth, sliderLineWidth);
        configureSliderLabelsDouble(minLB, maxLB, currentLB, sliderLB, 10);
        configureSliderLabelsDouble(minLE, maxLE, currentLE, sliderLE, 10);
        configureSliderLabelsDouble(minBB, maxBB, currentBB, sliderBB, 10);
        configureSliderLabelsDouble(minBE, maxBE, currentBE, sliderBE, 10);
        configureSliderLabelsDouble(minFI, maxFI, currentFI, sliderFI, 10);
        configureSliderLabelsDouble(minSI, maxSI, currentSI, sliderSI, 10);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        sliderWidth.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                currentWidth.setText(String.valueOf(sliderWidth.getValue()));
            }
        });
        sliderWidth.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                currentWidth.setText(String.valueOf(sliderWidth.getValue()));
            }
        });
        sliderHeight.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                currentHeight.setText(String.valueOf(sliderHeight.getValue()));
            }
        });
        sliderHexRadius.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                currentHexRadius.setText(String.valueOf(sliderHexRadius.getValue()));
            }
        });
        sliderLineWidth.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                currentLineWidth.setText(String.valueOf(sliderLineWidth.getValue()));
            }
        });
        sliderLB.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                double value = sliderLB.getValue();
                value /= 10;
                currentLB.setText(String.valueOf(value));
            }
        });
        sliderLE.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                double value = sliderLE.getValue();
                value /= 10;
                currentLE.setText(String.valueOf(value));
            }
        });
        sliderBB.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                double value = sliderBB.getValue();
                value /= 10;
                currentBB.setText(String.valueOf(value));
            }
        });
        sliderBE.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                double value = sliderBE.getValue();
                value /= 10;
                currentBE.setText(String.valueOf(value));
            }
        });
        sliderFI.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                double value = sliderFI.getValue();
                value /= 10;
                currentFI.setText(String.valueOf(value));
            }
        });
        sliderSI.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                double value = sliderSI.getValue();
                value /= 10;
                currentSI.setText(String.valueOf(value));
            }
        });
    }

    private void configureSliderLabels(JLabel min, JLabel max, JLabel current, JSlider slider) {
        min.setText(String.valueOf(slider.getMinimum()));
        max.setText(String.valueOf(slider.getMaximum()));
        current.setText(String.valueOf(slider.getValue()));
    }

    private void configureSliderLabelsDouble(JLabel min, JLabel max, JLabel current, JSlider slider, int divider) {
        min.setText(String.valueOf(slider.getMinimum() / divider));
        max.setText(String.valueOf(slider.getMaximum() / divider));
        current.setText(String.valueOf(((double) slider.getValue()) / divider));
    }

    private void onOK() {
        // add your code here
        GraphUtills.HEXAGONS_IN_ROW = sliderWidth.getValue();
        GraphUtills.HEXAGONS_IN_COL = sliderHeight.getValue();
        GraphUtills.HEXAGON_RADIUS = sliderHexRadius.getValue();
        GraphUtills.FONT_SIZE = GraphUtills.HEXAGON_RADIUS;
        GraphUtills.HEXAGON_RADIUS_SMALL = (int) (GraphUtills.HEXAGON_RADIUS * sqrt(3) / 2);
        GraphUtills.ASIDE_X = 2 * GraphUtills.HEXAGON_RADIUS_SMALL;
        GraphUtills.ASIDE_Y = (int) (GraphUtills.HEXAGON_RADIUS * 1.5);
        GraphUtills.IMAGE_WIDTH = GraphUtills.HEXAGONS_IN_ROW * GraphUtills.ASIDE_X + 10 + GraphUtills.HEXAGON_RADIUS_SMALL;
        GraphUtills.IMAGE_HEIGHT = GraphUtills.HEXAGONS_IN_COL * GraphUtills.ASIDE_Y + 10 + GraphUtills.HEXAGON_RADIUS;
        world = new World(GraphUtills.HEXAGONS_IN_ROW, GraphUtills.HEXAGONS_IN_COL);
        field.world = world;
        GraphUtills.drawWorld(world, field.getGraphics(), observer);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel1.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel2.add(buttonOK, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel2.add(buttonCancel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(21, 5, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sliderWidth = new JSlider();
        sliderWidth.setMinimum(1);
        sliderWidth.setPaintLabels(true);
        sliderWidth.setPaintTicks(true);
        sliderWidth.setSnapToTicks(true);
        sliderWidth.setValueIsAdjusting(false);
        panel3.add(sliderWidth, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderHeight = new JSlider();
        sliderHeight.setMinimum(1);
        panel3.add(sliderHeight, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Выберите ширину поля");
        panel3.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Выберите высоту поля");
        panel3.add(label2, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minWidth = new JLabel();
        minWidth.setText("Label");
        panel3.add(minWidth, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxWidth = new JLabel();
        maxWidth.setText("Label");
        panel3.add(maxWidth, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentWidth = new JLabel();
        currentWidth.setText("Label");
        panel3.add(currentWidth, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minHeight = new JLabel();
        minHeight.setText("Label");
        panel3.add(minHeight, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxHeight = new JLabel();
        maxHeight.setText("Label");
        panel3.add(maxHeight, new com.intellij.uiDesigner.core.GridConstraints(3, 4, 2, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentHeight = new JLabel();
        currentHeight.setText("Label");
        panel3.add(currentHeight, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentHexRadius = new JLabel();
        currentHexRadius.setText("Label");
        panel3.add(currentHexRadius, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minHexRadius = new JLabel();
        minHexRadius.setText("Label");
        panel3.add(minHexRadius, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Выберите радиус гексагона");
        panel3.add(label3, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxHexRadius = new JLabel();
        maxHexRadius.setText("Label");
        panel3.add(maxHexRadius, new com.intellij.uiDesigner.core.GridConstraints(6, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderHexRadius = new JSlider();
        sliderHexRadius.setMinimum(5);
        panel3.add(sliderHexRadius, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        panel3.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        sliderLineWidth = new JSlider();
        sliderLineWidth.setMaximum(10);
        sliderLineWidth.setMinimum(1);
        sliderLineWidth.setValue(5);
        sliderLineWidth.setValueIsAdjusting(false);
        panel3.add(sliderLineWidth, new com.intellij.uiDesigner.core.GridConstraints(8, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Выберите толщину линии");
        panel3.add(label4, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minLineWidth = new JLabel();
        minLineWidth.setText("Label");
        panel3.add(minLineWidth, new com.intellij.uiDesigner.core.GridConstraints(8, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxLineWidth = new JLabel();
        maxLineWidth.setText("Label");
        panel3.add(maxLineWidth, new com.intellij.uiDesigner.core.GridConstraints(8, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentLineWidth = new JLabel();
        currentLineWidth.setText("Label");
        panel3.add(currentLineWidth, new com.intellij.uiDesigner.core.GridConstraints(7, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLB = new JSlider();
        sliderLB.setMaximum(50);
        sliderLB.setValue(25);
        panel3.add(sliderLB, new com.intellij.uiDesigner.core.GridConstraints(10, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minLB = new JLabel();
        minLB.setText("Label");
        panel3.add(minLB, new com.intellij.uiDesigner.core.GridConstraints(10, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxLB = new JLabel();
        maxLB.setText("Label");
        panel3.add(maxLB, new com.intellij.uiDesigner.core.GridConstraints(10, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Выберите значение LIVE_BEGIN");
        panel3.add(label5, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentLB = new JLabel();
        currentLB.setText("Label");
        panel3.add(currentLB, new com.intellij.uiDesigner.core.GridConstraints(9, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderLE = new JSlider();
        sliderLE.setMaximum(50);
        sliderLE.setValue(25);
        panel3.add(sliderLE, new com.intellij.uiDesigner.core.GridConstraints(12, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Выберите значение LIVE_END");
        panel3.add(label6, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minLE = new JLabel();
        minLE.setText("Label");
        panel3.add(minLE, new com.intellij.uiDesigner.core.GridConstraints(12, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxLE = new JLabel();
        maxLE.setText("Label");
        panel3.add(maxLE, new com.intellij.uiDesigner.core.GridConstraints(12, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentLE = new JLabel();
        currentLE.setText("Label");
        panel3.add(currentLE, new com.intellij.uiDesigner.core.GridConstraints(11, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderBB = new JSlider();
        sliderBB.setMaximum(50);
        sliderBB.setValue(25);
        panel3.add(sliderBB, new com.intellij.uiDesigner.core.GridConstraints(14, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minBB = new JLabel();
        minBB.setText("Label");
        panel3.add(minBB, new com.intellij.uiDesigner.core.GridConstraints(14, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxBB = new JLabel();
        maxBB.setText("Label");
        panel3.add(maxBB, new com.intellij.uiDesigner.core.GridConstraints(14, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentBB = new JLabel();
        currentBB.setText("Label");
        panel3.add(currentBB, new com.intellij.uiDesigner.core.GridConstraints(13, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Выберите значение BIRTH_BEGIN");
        panel3.add(label7, new com.intellij.uiDesigner.core.GridConstraints(14, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderBE = new JSlider();
        sliderBE.setMaximum(50);
        sliderBE.setValue(25);
        panel3.add(sliderBE, new com.intellij.uiDesigner.core.GridConstraints(16, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minBE = new JLabel();
        minBE.setText("Label");
        panel3.add(minBE, new com.intellij.uiDesigner.core.GridConstraints(16, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentBE = new JLabel();
        currentBE.setText("Label");
        panel3.add(currentBE, new com.intellij.uiDesigner.core.GridConstraints(15, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxBE = new JLabel();
        maxBE.setText("Label");
        panel3.add(maxBE, new com.intellij.uiDesigner.core.GridConstraints(16, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Выберите значение BIRTH_END");
        panel3.add(label8, new com.intellij.uiDesigner.core.GridConstraints(16, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderFI = new JSlider();
        sliderFI.setMaximum(50);
        sliderFI.setValue(25);
        panel3.add(sliderFI, new com.intellij.uiDesigner.core.GridConstraints(18, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minFI = new JLabel();
        minFI.setText("Label");
        panel3.add(minFI, new com.intellij.uiDesigner.core.GridConstraints(18, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentFI = new JLabel();
        currentFI.setText("Label");
        panel3.add(currentFI, new com.intellij.uiDesigner.core.GridConstraints(17, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxFI = new JLabel();
        maxFI.setText("Label");
        panel3.add(maxFI, new com.intellij.uiDesigner.core.GridConstraints(18, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Выберите значение FST_IMPACT");
        panel3.add(label9, new com.intellij.uiDesigner.core.GridConstraints(18, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sliderSI = new JSlider();
        sliderSI.setMaximum(50);
        sliderSI.setValue(25);
        panel3.add(sliderSI, new com.intellij.uiDesigner.core.GridConstraints(20, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minSI = new JLabel();
        minSI.setText("Label");
        panel3.add(minSI, new com.intellij.uiDesigner.core.GridConstraints(20, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        maxSI = new JLabel();
        maxSI.setText("Label");
        panel3.add(maxSI, new com.intellij.uiDesigner.core.GridConstraints(20, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Введите значение SND_IMPACT");
        panel3.add(label10, new com.intellij.uiDesigner.core.GridConstraints(20, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentSI = new JLabel();
        currentSI.setText("Label");
        panel3.add(currentSI, new com.intellij.uiDesigner.core.GridConstraints(19, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

//    public static void main(String[] args) {
//        Options dialog = new Options();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }

}
