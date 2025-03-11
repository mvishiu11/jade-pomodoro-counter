package com.mvishiu11.agents;

import jade.core.Agent;
import com.mvishiu11.behaviours.PomodoroBehaviour;

public class PomodoroAgent extends Agent {
    protected void setup() {
        System.out.println(getLocalName() + ": Pomodoro Timer started!");
        addBehaviour(new PomodoroBehaviour(this));
    }
}
