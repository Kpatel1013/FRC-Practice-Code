// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.util.WPILibVersion;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private boolean isTracking;
  private LimeLight limelight;

  public boolean isTracking() {
    return isTracking;
  }

  public void setTracking(boolean isTracking) {
    this.isTracking = isTracking;
  }

  private DifferentialDrive diffDrive;
  public DriveTrain(LimeLight limelight) {
    
    this.limelight = limelight;
    isTracking = true;
    //instantiates the whells and creates constants for them
    WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.frontLeft);
    WPI_TalonFX frontRight = new WPI_TalonFX(Constants.frontRight);
    WPI_TalonFX middleLeft = new WPI_TalonFX(Constants.middleLeft);
    WPI_TalonFX middleRight = new WPI_TalonFX(Constants.middleRight);
    WPI_TalonFX backRight = new WPI_TalonFX(Constants.backRight);
    WPI_TalonFX backLeft = new WPI_TalonFX(Constants.backLeft);
    
    //sets invert types for all wheels
    frontRight.setInverted(Constants.setInvertedRight);
    middleRight.setInverted(Constants.setInvertedRight);
    backRight.setInverted(Constants.setInvertedRight);
    frontLeft.setInverted(Constants.setInvertedLeft);
    middleLeft.setInverted(Constants.setInvertedLeft);
    backLeft.setInverted(Constants.setInvertedLeft);

    // nuetral type(bassicaly breaking the robot, (think of a bike) 
    //You dont want to hit the brakes on the front or your pp will go bye
    //(in this case your robot will topple forward)
    frontRight.setNeutralMode(NeutralMode.Coast);
    frontLeft.setNeutralMode(NeutralMode.Coast); //Coast means that the wheel will just stop moving/freespin(no pressure)
    middleRight.setNeutralMode(NeutralMode.Brake); //.Brake means it will stop working(pressure)
    middleLeft.setNeutralMode(NeutralMode.Brake);
    middleRight.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);
    backLeft.setNeutralMode(NeutralMode.Brake);


    //follows the middle and back wheels to the front wheels(makes them all follow the same speed)
    middleLeft.follow(frontLeft);
    backLeft.follow(frontLeft);
    middleRight.follow(frontRight);
    backRight.follow(frontRight);
  
    diffDrive = new DifferentialDrive(frontLeft,frontRight);
  }

  public int calculateTrackingTurn() {
    if(Math.abs(limelight.getXAngle()) <= 0.5) {
      return 0;
    }
    if(Math.abs(limelight.getXAngle()) == limelight.getXAngle() ) {
      return 1;  
    } else {
      return -1;
    }
  }

  public void manualDrive(double throttle, double turn) {
    diffDrive.arcadeDrive(throttle, (isTracking ? calculateTrackingTurn() : turn));
  }
  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
