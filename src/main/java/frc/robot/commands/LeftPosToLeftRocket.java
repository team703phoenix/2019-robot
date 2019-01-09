/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.io.File;

import edu.wpi.first.wpilibj.command.CommandGroup;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

public class LeftPosToLeftRocket extends CommandGroup {

  public LeftPosToLeftRocket() {
    Trajectory leftTraj1 = Pathfinder.readFromCSV(
      new File("home//lvuser//LeftPosDriveOffHab.left.pf1.csv"));
    Trajectory rightTraj1 = Pathfinder.readFromCSV(
      new File("home//lvuser//LeftPosDriveOffHab.right.pf1.csv"));

    Trajectory leftTraj2 = Pathfinder.readFromCSV(
      new File("home//lvuser//LeftPosGoToRocket.left.pf1.csv"));
    Trajectory rightTraj2 = Pathfinder.readFromCSV(
      new File("home//lvuser//LeftPosGoToRocket.right.pf1.csv"));

    addSequential(new FollowPath(leftTraj1, rightTraj1));
    addSequential(new FollowPath(leftTraj2, rightTraj2));
  }
}
