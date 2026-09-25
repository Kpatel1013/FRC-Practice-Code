// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;


/** An example command that uses an example subsystem. */
public class DriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_subsystem;
  private XboxController xboxController;
  private double throttle;
  private double turn;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveCommand(DriveTrain subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
  

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    xboxController = new XboxController(Constants.xboxPort);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    throttle = xboxController.getLeftY();
    throttle = -throttle; //the xbox controller is dumb dumb and thinks going back is front, so you reverse it
    turn = xboxController.getRightX();

    if (!(throttle > 0.05 || throttle < -0.05)) {
        throttle = 0;
    }
    if (!(turn > 0.05 || turn < -0.05)){
        throttle = 0;
    }

    m_subsystem.manualDrive(throttle, turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}