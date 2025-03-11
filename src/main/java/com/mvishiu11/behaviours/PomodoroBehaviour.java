package com.mvishiu11.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import jade.core.Agent;

public class PomodoroBehaviour extends CyclicBehaviour {
    public PomodoroBehaviour(Agent a) {
        super(a);
    }

    public void action() {
        System.out.println(myAgent.getLocalName() + ": Starting new Pomodoro session.");

        // Send message to CountdownAgent for work session
        sendMessage("CountdownAgent", "work:25");

        // Block until next cycle
        block();
    }

    private void sendMessage(String receiver, String content) {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(receiver, AID.ISLOCALNAME));
        msg.setContent(content);
        myAgent.send(msg);
    }
}
