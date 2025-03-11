package com.mvishiu11.behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.Agent;

public class CountdownBehaviour extends Behaviour {
    private int countdown = 0;
    private String mode = "";

    public CountdownBehaviour(Agent a) {
        super(a);
    }

    public void action() {
        // Wait for a message
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            String content = msg.getContent();
            if (content.startsWith("work")) {
                countdown = Integer.parseInt(content.split(":")[1]);
                mode = "Work";
            } else if (content.startsWith("break")) {
                countdown = Integer.parseInt(content.split(":")[1]);
                mode = "Break";
            }

            // Start countdown
            doCountdown();
        } else {
            block();
        }
    }

    private void doCountdown() {
        for (int i = countdown; i > 0; i--) {
            String countdownMsg = "Time: " + i;
            sendMessage("GUIAgent", countdownMsg);  // <-- Send UI Update

            System.out.println(myAgent.getLocalName() + ": " + mode + " - " + i);

            try {
                Thread.sleep(1000); // Simulate 1-second delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(myAgent.getLocalName() + ": " + mode + " session ended.");
        sendMessage("SupervisorAgent", "completed:" + mode);
    }

    private void sendMessage(String receiver, String content) {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new jade.core.AID(receiver, jade.core.AID.ISLOCALNAME));
        msg.setContent(content);
        myAgent.send(msg);
    }

    public boolean done() {
        return false;
    }
}
