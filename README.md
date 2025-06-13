# Renert FTC Robotics Codebase

Welcome to the official GitHub repository for **Renert School's FTC Robotics Team**! This codebase powers our competitive robot in the **FIRST Tech Challenge (FTC)**. Here you'll find all the code used for autonomous, teleop, and subsystem control — developed collaboratively by students.

## 🛠 About This Project

This repository contains Java-based FTC SDK code designed for our robot for the current competition season. Our main goals are to:

- Utilize precise **odometry-based localization**
- Implement robust and adaptable **autonomous routines**
- Provide a responsive and driver-friendly **TeleOp control scheme**
- Encourage clean, modular, and reusable code

This project is built on top of the **FTC SDK** provided by REV Robotics and FIRST.

## 📁 Repository Structure

```plaintext
TeamCode/
├── Autonomous/         # All autonomous OpModes (Red/Blue, Cycle, Park, etc.)
├── TeleOp/             # Manual driver control code
├── Subsystems/         # Modular components like DriveTrain, Odometry, Intake, etc.
├── Hardware/           # Robot hardware configuration
├── Utilities/          # Helper classes and utilities (e.g. PID, trajectory, etc.)
└── Robot.java          # Centralized robot control logic

