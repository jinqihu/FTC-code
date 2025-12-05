package org.firstinspires.ftc.teamcode.pedroPathing;

import static java.lang.Thread.sleep;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name = "LM2BlueFarSideAutoWithoutPush", group = "Pedropathing")
public class nopushauto extends OpMode {

    private Follower follower;

    private DcMotorEx shooter, intake, transfer, index;

    private Timer pathTimer, actionTimer, opmodeTimer;

    private int pathState;

    /* REAL BLOCKING WAIT */
    private void waitMs(double ms) {
        double end = System.currentTimeMillis() + ms;
        while (System.currentTimeMillis() < end) {
            index.setVelocity(2500);
            transfer.setVelocity(2000);
            intake.setVelocity(2300);
            shooter.setVelocity(1700);
        }
        follower.update();
        index.setVelocity(0);
        transfer.setVelocity(0);
    }


    /* Poses */
    private final Pose startPose = new Pose(56, 8, Math.toRadians(0));
    private final Pose scorePose = new Pose(54, 82, Math.toRadians(136));
    private final Pose pickup1Pose = new Pose(48, 78, Math.toRadians(180));
    private final Pose pickup1closePose = new Pose(15, 78, Math.toRadians(180));
    private final Pose pickup2Pose = new Pose(48, 55, Math.toRadians(180));
    private final Pose pickup2closePose = new Pose(12, 55, Math.toRadians(180));
    private final Pose pickup3Pose = new Pose(48, 30, Math.toRadians(180));
    private final Pose pickup3closePose = new Pose(12, 30, Math.toRadians(180));
    private final Pose end = new Pose(48, 48, Math.toRadians(90));

    private final Pose antigate = new Pose(60,70, Math.toRadians(180));

    private PathChain scorepreload, grabPickup1, grabPickup2, grabPickup3;
    private PathChain scorePickup1, scorePickup2, scorePickup3;
    private PathChain grabclosePickup1, grabclosePickup2, grabclosePickup3 , asdf;

    public void buildPaths() {

        scorepreload = follower.pathBuilder()
                .addPath(new BezierLine(startPose, scorePose))
                .setLinearHeadingInterpolation(startPose.getHeading(), scorePose.getHeading())
                .build();

        grabPickup1 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, pickup1Pose))
                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup1Pose.getHeading())
                .build();

        grabclosePickup1 = follower.pathBuilder()
                .addPath(new BezierLine(pickup1Pose, pickup1closePose))
                .setLinearHeadingInterpolation(pickup1Pose.getHeading(), pickup1closePose.getHeading())
                .build();

        scorePickup1 = follower.pathBuilder()
                .addPath(new BezierLine(pickup1closePose, scorePose))
                .setLinearHeadingInterpolation(pickup1closePose.getHeading(), scorePose.getHeading())
                .build();

        grabPickup2 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, pickup2Pose))
                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup2Pose.getHeading())
                .build();

        grabclosePickup2 = follower.pathBuilder()
                .addPath(new BezierLine(pickup2Pose, pickup2closePose))
                .setLinearHeadingInterpolation(pickup2Pose.getHeading(), pickup2closePose.getHeading())
                .build();

        scorePickup2 = follower.pathBuilder()
                .addPath(new BezierCurve(pickup2closePose, antigate, scorePose))
                .setLinearHeadingInterpolation(pickup2closePose.getHeading(), scorePose.getHeading())
                .build();

        grabPickup3 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, pickup3Pose))
                .setLinearHeadingInterpolation(scorePose.getHeading(), pickup3Pose.getHeading())
                .build();

        grabclosePickup3 = follower.pathBuilder()
                .addPath(new BezierLine(pickup3Pose, pickup3closePose))
                .setLinearHeadingInterpolation(pickup3Pose.getHeading(), pickup3closePose.getHeading())
                .build();

        scorePickup3 = follower.pathBuilder()
                .addPath(new BezierLine(pickup3closePose, end))
                .setLinearHeadingInterpolation(pickup3closePose.getHeading(), end.getHeading())
                .build();
        asdf = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, end))
                .setLinearHeadingInterpolation(scorePose.getHeading(), end.getHeading())
                .build();
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                if (!follower.isBusy()) {
                    follower.followPath(scorepreload, 2, false);
                    setPathState(1);
                }
                break;

            case 1:
                if (!follower.isBusy()) {
                    follower.followPath(grabPickup1, 2, false);
                    waitMs(4000);
                    setPathState(2);
                }
                break;

            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(grabclosePickup1, 2, false);
                    setPathState(3);
                }
                break;

            case 3:
                if (!follower.isBusy()) {
                    follower.followPath(scorePickup1, 2, false);
                    setPathState(4);
                }
                break;

            case 4:

                if (!follower.isBusy()) {
                    follower.followPath(grabPickup2, 2, false);
                    waitMs(4000);
                    setPathState(5);
                }
                break;

            case 5:

                if (!follower.isBusy()) {
                    follower.followPath(grabclosePickup2, 2, false);
                    setPathState(6);
                }
                break;

            case 6:
                if (!follower.isBusy()) {
                    follower.followPath(scorePickup2, 0.8, false);
                    setPathState(7);
                }
                break;

            case 7:

                if (!follower.isBusy()) {
                    follower.followPath(grabPickup3, 2, false);
                    waitMs(4000);
                    setPathState(8);
                }
                break;

            case 8:

                if (!follower.isBusy()) {
                    follower.followPath(grabclosePickup3, 2, false);
                    setPathState(9);
                }
                break;

            case 9:
                if (!follower.isBusy()) {
                    follower.followPath(scorePickup3, 2, false);
                    setPathState(10);
                }
                break;
            // case 10:
            // if (!follower.isBusy()) {
            // follower.followPath(asdf, 1, false);
            // waitMs(3000);
            // setPathState(11);
            // }
            // break;

            default:
                telemetry.addData("Status", "Autonomous finished");
                break;
        }
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    /* ONLY CHANGE: transfer + index ON during 0, 3, 6, 9 */
    private void runMechanismsConstantly() {
        intake.setVelocity(2700);
        shooter.setVelocity(1700);
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
        runMechanismsConstantly();
        telemetry.addData("Path State", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.update();
    }

    @Override
    public void init() {
        follower = Constants.createFollower(hardwareMap);

        shooter = hardwareMap.get(DcMotorEx.class, Constants.Mechanism.SHOOTER);
        intake = hardwareMap.get(DcMotorEx.class, Constants.Mechanism.INTAKE);
        transfer = hardwareMap.get(DcMotorEx.class, Constants.Mechanism.TRANSFER);
        index = hardwareMap.get(DcMotorEx.class, Constants.Mechanism.INDEX);

        shooter.setDirection(Constants.Mechanism.SHOOTER_DIR);
        intake.setDirection(Constants.Mechanism.INTAKE_DIR);
        transfer.setDirection(Constants.Mechanism.TRANSFER_DIR);
        index.setDirection(Constants.Mechanism.INDEX_DIR);

        pathTimer = new Timer();
        actionTimer = new Timer();
        opmodeTimer = new Timer();

        follower.setStartingPose(startPose);
        buildPaths();
    }

    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }

    @Override
    public void stop() {}
}
