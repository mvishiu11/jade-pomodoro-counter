package com.mvishiu11.agents;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import javax.swing.*;
import java.awt.*;

public class GUIAgent extends Agent {
    private JFrame frame;
    private JLabel sessionLabel;
    private JLabel countdownLabel;

    protected void setup() {
        System.out.println(getLocalName() + ": GUI started.");

        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Pomodoro Timer");
            frame.setSize(300, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(2, 1));

            sessionLabel = new JLabel("Session: Pomodoro (first)", SwingConstants.CENTER);
            sessionLabel.setFont(new Font("Arial", Font.BOLD, 16));

            countdownLabel = new JLabel("Time Left: --", SwingConstants.CENTER);
            countdownLabel.setFont(new Font("Arial", Font.PLAIN, 14));

            frame.add(sessionLabel);
            frame.add(countdownLabel);
            frame.setVisible(true);
        });

        addBehaviour(new jade.core.behaviours.CyclicBehaviour() {
            public void action() {
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
                ACLMessage msg = receive(mt);
                if (msg != null) {
                    String content = msg.getContent();
                    SwingUtilities.invokeLater(() -> updateUI(content));
                } else {
                    block();
                }
            }
        });
    }

    private void updateUI(String message) {
        if (message.startsWith("Session:")) {
            sessionLabel.setText(message);
        } else if (message.startsWith("Time:")) {
            countdownLabel.setText(message);
        }
    }
}
