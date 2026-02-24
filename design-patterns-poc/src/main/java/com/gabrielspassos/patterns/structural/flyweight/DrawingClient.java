package com.gabrielspassos.patterns.structural.flyweight;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingClient extends JFrame {

    @Serial
    private static final long serialVersionUID = -1350200437285282550L;
    private final int width;
    private final int height;

    private static final ShapeType[] shapes = { ShapeType.LINE, ShapeType.OVAL_FILL, ShapeType.OVAL_NO_FILL };
    private static final Color[] colors = { Color.RED, Color.YELLOW, Color.BLUE, Color.BLACK, Color.WHITE, Color.GREEN };

    public DrawingClient(int width, int height) {
        this.width = width;
        this.height = height;
        Container contentPane = getContentPane();

        JButton startButton = new JButton("Draw");
        final JPanel panel = new JPanel();

        contentPane.add(panel, BorderLayout.CENTER);
        contentPane.add(startButton, BorderLayout.SOUTH);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        startButton.addActionListener(event -> {
            Graphics g = panel.getGraphics();
            for (int i = 0; i < 20; ++i) {
                Shape shape = ShapeFactory.getShape(getRandomShape());
                shape.draw(g, getRandomX(), getRandomY(), getRandomWidth(),
                        getRandomHeight(), getRandomColor());
            }
        });
    }

    private ShapeType getRandomShape() {
        return shapes[(int) (Math.random() * shapes.length)];
    }

    private int getRandomX() {
        return (int) (Math.random() * width);
    }

    private int getRandomY() {
        return (int) (Math.random() * height);
    }

    private int getRandomWidth() {
        return (int) (Math.random() * (width / 10));
    }

    private int getRandomHeight() {
        return (int) (Math.random() * (height / 10));
    }

    private Color getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    static void main() {
        DrawingClient drawing = new DrawingClient(500,600);
    }
}
