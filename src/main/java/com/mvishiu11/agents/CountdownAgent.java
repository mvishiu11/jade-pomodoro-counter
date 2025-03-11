package com.mvishiu11.agents;

import jade.core.Agent;
import com.mvishiu11.behaviours.CountdownBehaviour;

public class CountdownAgent extends Agent {
    protected void setup() {
        System.out.println(getLocalName() + ": Ready to count down.");
        addBehaviour(new CountdownBehaviour(this));
    }
}
