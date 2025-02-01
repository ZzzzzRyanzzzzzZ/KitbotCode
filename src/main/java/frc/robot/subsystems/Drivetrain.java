// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.driveConstants;

/** Add your docs here. */
public class Drivetrain extends SubsystemBase
{
    TalonSRX frontL = new TalonSRX(driveConstants.fL);
    TalonSRX frontR = new TalonSRX(driveConstants.fR);
    TalonSRX backL = new TalonSRX(driveConstants.bL);
    TalonSRX backR = new TalonSRX(driveConstants.bR);

    
    public Drivetrain()
    {
        frontL.setInverted(true);
        backL.setInverted(true);
        backL.follow(frontL);
        frontR.setInverted(false);
        backR.follow(backL);
        
    }

    public void SetVoltage(double leftVolts, double rightVolts)
    {
    // OK to just multiply by 12 because voltage compensation is enabled
    frontL.set(TalonSRXControlMode.PercentOutput, leftVolts);
    frontR.set(TalonSRXControlMode.PercentOutput, rightVolts);
    }
    @Override
    public void periodic() 
    {
        
    }
    public Command driveCommand(Supplier<Double> leftSpeed, Supplier<Double> rightSpeed)
    {
        return Commands.run(()-> SetVoltage(leftSpeed.get(), rightSpeed.get()), this);


    }

}
