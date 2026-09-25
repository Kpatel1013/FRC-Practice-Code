// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static final int frontLeft = 1;
  public static final int backLeft = 2;
  public static final int middleLeft = 3;
  public static final int frontRight = 4;
  public static final int backRight = 5;
  public static final int middleRight = 6;


  public static final int TOP_MOTORID = 7; // neo
  public static final int BOTTOM_MOTORID = 8; //neo 550
  public static final int LEFTARM_MOTORID = 9; //neo 
  public static final int RIGHTARM_MOTORID = 9; //neo

  public static final int TOP_MOTOR_CURRENT_LIMIT = 40; // neo amp
  public static final int BOTTOM_MOTOR_CURRENT_LIMIT = 20; // neo 550 amp
  public static final int LEFTARM_MOTOR_CURRENT_LIMIT = 40; //neo amp
  public static final int RIGHTARM_MOTOR_CURRENT_LIMIT = 40; // neo amp

  // public static final IdleMode TOP_MOTOR_IDLE_MODE = IdleMode.kCoast;
  // public static final IdleMode BOTTOM_MOTOR_IDLE_MODE = IdleMode.kBrake;
  // public static final IdleMode LEFTARM_MOTOR_IDLE_MODE = IdleMode.kBrake;
  // public static final IdleMode RIGHTMOTOR_MOTOR_IDLE_MODE = IdleMode.kBrake;

  public static final boolean setInvertedRight = false;
  public static final boolean setInvertedLeft = !setInvertedRight;

  public static final int xboxPort = 1;

  public static final double LIMELIGHT_LENS_HEIGHT = 20.3213213;

  public static final double ShootingP = 0;
  public static final double ShootingI = 0;
  public static final double ShootingD = 0;

  public static final double feedforward = 0;
}


