# FRC Practice Code

WPILib Java for a practice drivetrain on **Team Axiom 4787**. The robot is a six-motor differential drive on TalonFX controllers. In teleop, an Xbox controller runs arcade drive, and a Limelight can take over the turn so the robot points at a vision target. The arm code is a PID position loop, which is the control work in this repo.

This is practice code from the 2023 season. It is not a full match robot. The autonomous routine is still the WPILib example, and the shooter class is drafted but not wired into the robot.

## Overview

The project uses the command-based WPILib layout. `RobotContainer` owns the subsystems and hands the scheduler a drive command in teleop.

- **Arcade drive** on six Falcons. Three motors per side follow the front motor, and `DifferentialDrive` turns throttle and turn into left and right output.
- **Limelight aiming** reads the target's horizontal angle from NetworkTables. While tracking is on, that angle replaces the driver's turn stick.
- **Arm PID** holds a target angle on the Spark Max arm. A `PIDController` reads the arm encoder, adds a feedforward term, and sends that voltage to the arm motors. The gains live in `Constants` and are still 0, so the loop is built but not tuned. `RobotContainer` does not construct this subsystem yet.

## What each file does

- **`DriveTrain.java`** — Creates six `WPI_TalonFX` motors on CAN IDs 1 through 6. The right side is not inverted, the left side is, the front motors coast, and the middle and back motors brake. Middle and back motors follow the front motor on that side. `manualDrive` calls `arcadeDrive`.
- **`DriveCommand.java`** — Reads the Xbox controller on port 1. Left stick Y is throttle, flipped so pushing the stick forward drives forward. Right stick X is turn. Inputs inside 0.05 are treated as zero.
- **`LimeLight.java`** — Reads the default `limelight` NetworkTables table: whether a target is visible (`tv`), horizontal and vertical angle (`tx`, `ty`), target area (`ta`), and AprilTag id (`tid`). It can switch pipelines and publishes those values to SmartDashboard. `calculateDistance` is in the file and is not called by the drive command.
- **`Shooter.java`** — Arm and shooter practice class. The arm is the PID. A `PIDController` is built from `ShootingP`, `ShootingI`, and `ShootingD`. `enableContinuousInput` is set from -180 to 180 degrees so the arm takes the short way around instead of spinning the long way. Each cycle is meant to command the arm with `pid.calculate(encoder distance, setpoint) + feedforward`. The top Neo follows the bottom Neo 550. This class is not constructed by `RobotContainer`.
- **`Autos.java`** — The template autonomous command. It does not drive a scripted path.
- **`Constants.java`** — CAN IDs, Xbox port, Limelight lens height, and the arm PID. `ShootingP`, `ShootingI`, `ShootingD`, and `feedforward` are the numbers to tune. All four are 0 right now.

Tracking starts on. `calculateTrackingTurn` returns 0 when `tx` is within 0.5 degrees, and otherwise a full turn left or right. It is not a proportional aim loop.

## Hardware

| CAN ID | Device |
| --- | --- |
| 1, 2, 3 | Front, back, and middle left drive, TalonFX. 2 and 3 follow 1. |
| 4, 5, 6 | Front, back, and middle right drive, TalonFX. 5 and 6 follow 4. |
| 7 | Shooter top motor, Neo (not used by the running robot) |
| 8 | Shooter bottom motor, Neo 550 (not used by the running robot) |
| 9 | Both arm motors in `Shooter.java` (not used by the running robot) |

The drive command uses an Xbox controller on port 1. The Limelight is expected under the default NetworkTables name `limelight`.

## Tech stack

- **Language:** Java 11
- **Framework:** [WPILib](https://docs.wpilib.org/) 2023.4.3, command-based, deployed to a RoboRIO
- **Vendor libraries:** CTRE Phoenix 5 (`WPI_TalonFX`), REVLib (`CANSparkMax`, only in the unused shooter)
- **Vision:** Limelight over NetworkTables
