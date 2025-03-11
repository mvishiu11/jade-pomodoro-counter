package com.mvishiu11;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final ExecutorService jadeExecutor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        final Runtime runtime = Runtime.instance();
        final jade.core.Profile profile = new ProfileImpl();
        profile.setParameter("gui", "true"); // Enable JADE GUI

        try {
            // Create Main Container
            final ContainerController mainController = jadeExecutor.submit(() -> runtime.createMainContainer(profile)).get();

            // Start JADE GUI (Agent Management Tool)
            // final AgentController gui = mainController.createNewAgent("rma", "jade.tools.rma.rma", new Object[]{});
            // gui.start();

            // Start Pomodoro Timer Agents
            final AgentController pomodoroAgent = mainController.createNewAgent("PomodoroAgent", "com.mvishiu11.agents.PomodoroAgent", new Object[]{});
            final AgentController countdownAgent = mainController.createNewAgent("CountdownAgent", "com.mvishiu11.agents.CountdownAgent", new Object[]{});
            final AgentController supervisorAgent = mainController.createNewAgent("SupervisorAgent", "com.mvishiu11.agents.SupervisorAgent", new Object[]{});
            final AgentController guiAgent = mainController.createNewAgent("GUIAgent", "com.mvishiu11.agents.GUIAgent", new Object[]{});

            // Start all Pomodoro agents
            pomodoroAgent.start();
            countdownAgent.start();
            supervisorAgent.start();
            guiAgent.start();

            System.out.println("All agents started!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}