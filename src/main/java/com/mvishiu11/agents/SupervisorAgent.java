package com.mvishiu11.agents;

import jade.core.Agent;
import com.mvishiu11.behaviours.SupervisorBehaviour;

public class SupervisorAgent extends Agent {
    protected void setup() {
        System.out.println(getLocalName() + ": Supervisor started.");
        addBehaviour(new SupervisorBehaviour(this));
    }
}
