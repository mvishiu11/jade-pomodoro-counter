# **Pomodoro Timer Simulation (JADE)**
A **multi-agent system** built with **JADE (Java Agent Development Framework)** that simulates a **Pomodoro Timer** using autonomous agents.

## **📌 Features**
- ✅ **Multi-agent architecture** with JADE.
- ✅ **Fully automated Pomodoro cycle**:
  - 25-second work sessions (simulating 25 minutes).
  - 5-second short breaks.
  - 15-second long breaks after 4 Pomodoros.
- ✅ **Swing-based GUI** that displays session type and countdown in real-time.
- ✅ **JADE Remote Agent Management (RMA) GUI** for agent monitoring.

## **📂 Project Structure**
```
com.mvishiu11/
 ├── agents/          # Agent implementations
 │   ├── PomodoroAgent.java
 │   ├── CountdownAgent.java
 │   ├── SupervisorAgent.java
 │   ├── GUIAgent.java  # UI Agent
 │
 ├── behaviours/      # Agent behaviors (separation of concerns)
 │   ├── PomodoroBehaviour.java
 │   ├── CountdownBehaviour.java
 │   ├── SupervisorBehaviour.java
 │
 ├── Main.java        # JADE container setup and agent startup
```

## **🚀 Getting Started**
### **1️⃣ Prerequisites**
- **Java 21+**
- **JADE Framework** ([Download JADE](http://jade.tilab.com/))
- **Maven (Optional, for Dependency Management)**

### **2️⃣ Running the Simulation**
1. **Compile and Run:**
   ```sh
   javac -cp jade.jar com/mvishiu11/Main.java
   java -cp jade.jar:. com.mvishiu11.Main
   ```
2. **JADE GUI (`rma`)** will open for monitoring.
3. **Pomodoro countdown starts automatically** in both:
   - The **JADE console** (printed logs).
   - The **Swing UI** (real-time countdown updates).