package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Eugeny on 14.06.2015.
 */
public class MainForm extends JFrame {
    JTextField stepsField;
    JTextField threadsField;
    JButton button;
    JLabel resultLabel;

    public MainForm() {
        setPreferredSize(new Dimension(400,300));
        setTitle("Интеграл");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        button = new JButton("Посчитать");
        button.addActionListener(e -> { // lambda - выражение (передать кусок кода на исполнение при нажатии кнопки)
            buttonClick();
        });
        JPanel panel = new JPanel();
        panel.add(button);
        getContentPane().add(panel, BorderLayout.SOUTH);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2,2,10,10));
        stepsField = new JTextField(10);
        threadsField = new JTextField(10);
        panel1.add(new Label("К-во шагов"));
        panel1.add(stepsField);
        panel1.add(new Label("К-во потоков"));
        panel1.add(threadsField);
        getContentPane().add(panel1, BorderLayout.NORTH);
        JPanel mainPanel = new JPanel();
        resultLabel = new JLabel("Результат");
        mainPanel.add(resultLabel);
        getContentPane().add(mainPanel);
        pack();
    }

    private void buttonClick() {
        Main m = new Main();
        m.setN_THREADS(Integer.parseInt(threadsField.getText()));
        m.setSteps(Integer.parseInt(stepsField.getText()));
        m.run();
        resultLabel.setText("Результат " + m.totalResult);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
}
