package org.firstinspires.ftc.teamcode.pedroPathing;

import static java.lang.Thread.sleep;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous(name = "one singlar pathchain", group = "Pedropathing")
public class testingauto extends OpMode {

    private Follower follower;

    private DcMotorEx shooter, intake, transfer, index;

    private Timer pathTimer, actionTimer, opmodeTimer;

    private int pathState;

    /* REAL BLOCKING WAIT */
    private void shoot(double ms) {
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
    private void wait(double ms) {
        double end = System.currentTimeMillis() + ms;
        while (System.currentTimeMillis() < end) {
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
    private final Pose end = new Pose(48, 48, Math.toRadians(0));


    /* Control Points */
    private final Pose antigate = new Pose(60, 70, Math.toRadians(0));

    private Path path1, path2, path3, path4, path5, path6, path7, path8;
    private PathChain autonomous;

    public void buildPaths() {
        autonomous = follower.pathBuilder()
                .addPath(new BezierLine(startPose, scorePose))
                .addPath(new BezierLine(scorePose, pickup1Pose))
                .addPath(new BezierLine(pickup1Pose, pickup1closePose))
                .addPath(new BezierLine(pickup1closePose, scorePose))
                .addPath(new BezierLine(scorePose, pickup2Pose))
                .addPath(new BezierLine(pickup2Pose, pickup2closePose))
                .addPath(new BezierCurve(pickup2closePose, antigate, scorePose))
                .addPath(new BezierLine(scorePose, pickup3Pose))
                .addPath(new BezierLine(pickup3Pose, pickup3closePose))
                .addPath(new BezierLine(pickup3closePose, scorePose))
                .addPath(new BezierLine(scorePose, end))
                .build();
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                if (!follower.isBusy()) {
                    follower.followPath(autonomous, 1, false);
                    setPathState(1);
                }
                break;


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
