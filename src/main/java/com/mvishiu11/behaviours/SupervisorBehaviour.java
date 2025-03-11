package com.mvishiu11.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.core.Agent;

public class SupervisorBehaviour extends CyclicBehaviour {
    private int completedPomodoros = 0;

    public SupervisorBehaviour(Agent a) {
        super(a);
    }

    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            String content = msg.getContent();
            if (content.startsWith("completed")) {
                String sessionType = content.split(":")[1];
                if (sessionType.equals("Work")) {
                    completedPomodoros++;
                    System.out.println(myAgent.getLocalName() + ": Pomodoro " + completedPomodoros + " completed.");
                    startBreak();
                } else {
                    startNextPomodoro();
                }
            }
        } else {
            block();
        }
    }

    private void startBreak() {
        int breakTime = (completedPomodoros % 4 == 0) ? 15 : 5;
        sendMessage("GUIAgent", "Session: Break (" + breakTime + "s)");
        sendMessage("CountdownAgent", "break:" + breakTime);
    }

    private void startNextPomodoro() {
        sendMessage("GUIAgent", "Session: Pomodoro");
        sendMessage("PomodoroAgent", "start");
    }

    private void sendMessage(String receiver, String content) {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new jade.core.AID(receiver, jade.core.AID.ISLOCALNAME));
        msg.setContent(content);
        myAgent.send(msg);
    }
}
