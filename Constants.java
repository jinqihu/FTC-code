package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {

    /* ------------------ PEDRO FOLLOWER CONSTANTS ------------------ */

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(11.6)
            .forwardZeroPowerAcceleration(-43.50489697981093)
            .lateralZeroPowerAcceleration(-70.13144004378732)

            // ----------- TUNED TRANSLATIONAL PIDF -----------
            .translationalPIDFCoefficients(new PIDFCoefficients(
                    0.001,   // P
                    0,
                    0.00,    // D
                    0.012    // F
            ))
            .translationalPIDFSwitch(3.5)
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(
                    0.01,
                    0,
                    0.00,
                    0.0005
            ))

            // ----------- TUNED HEADING PIDF -----------
            .headingPIDFCoefficients(new PIDFCoefficients(
                    0.3,
                    0,
                    0.00,
                    0.01
            ))
            .secondaryHeadingPIDFCoefficients(new PIDFCoefficients(
                    1.0,
                    0,
                    0.00,
                    0.0004
            ))

            // ----------- TUNED DRIVE PIDF -----------
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(
                    0.03,   // P
                    0,
                    0,   // D
                    0.6,    // F
                    0.015
            ))
            .secondaryDrivePIDFCoefficients(new FilteredPIDFCoefficients(
                    0.003,
                    0,
                    0.00,
                    0.6,
                    0.01
            ))
            .drivePIDFSwitch(20)
            .centripetalScaling(0.0005);

    /* ------------------ DRIVE CONSTANTS ------------------ */

    public static MecanumConstants driveConstants = new MecanumConstants()
            .leftFrontMotorName("frontLeftMotor")
            .leftRearMotorName("backLeftMotor")
            .rightFrontMotorName("frontRightMotor")
            .rightRearMotorName("backRightMotor")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(85.97208915169784)
            .yVelocity(57.16178882018174);

    /* ------------------ LOCALIZER CONSTANTS ------------------ */

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-1.0)
            .strafePodX(-5.0)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

    /* ------------------ PATH CONSTRAINTS ------------------ */

    public static PathConstraints pathConstraints = new PathConstraints(
            4,
            30,
            0.1,
            0.009,
            0,
            1,
            10,
            1
    );

    /* ------------------ MECHANISM CONSTANTS ------------------ */
    // All motors as DcMotorEx for velocity control

    public static class Mechanism {
        public static final String SHOOTER = "shooterMotor";
        public static final String INTAKE = "intakeMotor";
        public static final String TRANSFER = "transferMotor";
        public static final String INDEX = "indexMotor";
        public static final String HOOD = "hood";


        public static final DcMotorSimple.Direction SHOOTER_DIR = DcMotorSimple.Direction.REVERSE;
        public static final DcMotorSimple.Direction INTAKE_DIR = DcMotorSimple.Direction.REVERSE;
        public static final DcMotorSimple.Direction TRANSFER_DIR = DcMotorSimple.Direction.FORWARD;
        public static final DcMotorSimple.Direction INDEX_DIR = DcMotorSimple.Direction.REVERSE;

        public static final double INDEX_FEED_POWER = 0.7;
        public static final double INDEX_RETRACT_POWER = -0.7;
    }

    /* ------------------ BUILD FOLLOWER ------------------ */

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .pathConstraints(pathConstraints)
                .build();
    }
}
