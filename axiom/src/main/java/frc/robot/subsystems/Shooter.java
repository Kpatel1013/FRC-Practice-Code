package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;

import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;


public class Shooter extends PIDSubsystem {
    
    private final CANSparkMax topMotor; //neo
    private final CANSparkMax bottomMotor; //neo 550
    private final CANSparkMax armLeftMotor; //neo
    private final CANSparkMax armRightMotor; //neo

    private final RelativeEncoder topEncoder; //encoder for top 
    private final RelativeEncoder bottomEncoder; //encoder for bottom
    private final RelativeEncoder armLeftEncoder; //encoder for left arm
    private final RelativeEncoder armRightEncoder; //encoder for right arm
  
    public Shooter() {

      topMotor = new CANSparkMax(Constants.TOP_MOTORID, CANSparkMax.MotorType.kBrushless);
      bottomMotor = new CANSparkMax(Constants.BOTTOM_MOTORID, CANSparkMax.MotorType.kBrushless);
      armLeftMotor = new CANSparkMax(Constants.LEFTARM_MOTORID, CANSparkMax.MotorType.kBrushless);
      armRightMotor = new CANSparkMax(Constants.RIGHTARM_MOTORID, CANSparkMax.MotorType.kBrushless);

      topMotor.restoreFactoryDefaults();
      bottomMotor.restoreFactoryDefaults();
      armLeftMotor.restoreFactoryDefaults();
      armRightMotor.restoreFactoryDefaults();

      topMotor.setInverted(false); 
      bottomMotor.setInverted(false); 

      topMotor.follow(bottomMotor);

      armLeftMotor.setInverted(true);
      armRightMotor.setInverted(false);

      armRightMotor.follow(armLeftMotor);

      topMotor.setSmartCurrentLimit(Constants.TOP_MOTOR_CURRENT_LIMIT);
      bottomMotor.setSmartCurrentLimit(Constants.BOTTOM_MOTOR_CURRENT_LIMIT);
      armLeftMotor.setSmartCurrentLimit(Constants.LEFTARM_MOTOR_CURRENT_LIMIT);
      armRightMotor.setSmartCurrentLimit(Constants.RIGHTARM_MOTOR_CURRENT_LIMIT);

      // topMotor.setIdleMode(TurretConstants.kShootMotorIdleMode);
      // bottomMotor.setIdleMode(TurretConstants.kRotateMotorIdleMode);
      // armLeftMotor.setIdleMode(TurretConstants.kIntakeMotorIdleMode);
      // armRightMotor.setIdleMode(TurretConstants.kIntakeMotorIdleMode);


      topEncoder = topMotor.getEncoder();
      bottomEncoder = bottomMotor.getEncoder();
      armLeftEncoder = armLeftMotor.getEncoder();
      armRightEncoder = armRightMotor.getEncoder();

      PIDController turretPID = new PIDController(Constants.ShootingP, 
                                                  Constants.ShootingI, 
                                                  Constants.ShootingD); 
      PIDController.enableContinuousOutput(-180, 180); 
      

      armLeftMotor.set(pid.calculate(armLeftEncoder.getDistance(), setPoint) + feedforward);
      armRightMotor.set(pid.calculate(armRightEncoder.getDistance(), setPoint) + feedforward);
    }
 