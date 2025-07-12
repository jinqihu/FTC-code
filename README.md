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
# 🚀 GitHub Branching & Merging Rules



A simple guide for maintaining clean and collaborative Git workflows.

---

## 🔀 Branching Rules

### 1. `main` Branch
- Always contains **production-ready** code.
- **Never commit directly** to `main`.

### 2. Feature Branches
- Naming: `feature/your-feature-name`
- Used for building new features.
- Branch off from `main`.

### 3. Bugfix Branches
- Naming: `bugfix/short-description`
- Used for fixing small bugs or issues.
- Branch off from `main`.

### 4. Hotfix Branches
- Naming: `hotfix/critical-fix`
- For urgent production fixes.
- Branch off from `main`, and merged into both `main` and `develop` (if used).

### 5. Naming Convention
- Use lowercase letters.
- Use hyphens or slashes for clarity.
- Examples:
  - `feature/login-page`
  - `bugfix/navbar-crash`
  - `hotfix/payment-error`

---

## 🔁 Merging Rules

### 1. Use Pull Requests (PRs)
- No direct pushes to `main`.
- Always use a PR for code reviews.
- PR title and description should be clear and link to related issues.

### 2. Code Review
- At least **one team member** must approve a PR before merging.

### 3. Rebase or Squash
- Rebase your branch onto `main` before merging:  
  ```bash
  git fetch origin
  git rebase origin/main

