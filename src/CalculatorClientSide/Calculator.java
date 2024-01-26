package CalculatorClientSide;

import CalculatorServerSide.CalculatorData;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Calculator extends JFrame implements Serializable {
    private JPanel panel;
    private JLabel l1, l2, l3, resultLabel;
    private JTextField tx1, tx2, tx3;
    private JButton bEquals;

    public Calculator() {
        l1 = new JLabel("First Number");
        l2 = new JLabel("Second Number");
        l3 = new JLabel("Operation");
        tx1 = new JTextField();
        tx2 = new JTextField();
        tx3 = new JTextField();
        bEquals = new JButton("=");

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(l1);
        panel.add(tx1);
        panel.add(l2);
        panel.add(tx2);
        panel.add(l3);
        panel.add(tx3);
        panel.add(bEquals);

        resultLabel = new JLabel("Result: ");

        panel.add(resultLabel);
        this.getContentPane().add(panel);

        bEquals.addActionListener(e -> {
            CalculatorData calculatorData = new CalculatorData(
                    Integer.parseInt(tx1.getText()),
                    Integer.parseInt(tx2.getText()),
                    tx3.getText());
            String result = sendToServer(calculatorData);

            resultLabel.setText("Result : " + result);
        });

        setBounds(500, 100, 450, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private String sendToServer(CalculatorData calculatorData) {
        try {
            Socket socket = new Socket("localhost", 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(calculatorData);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String result = (String) ois.readObject();

            oos.close();
            ois.close();
            socket.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error !!!");
            return "";
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
