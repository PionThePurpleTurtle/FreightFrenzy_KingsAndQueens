package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.State;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "TestTeleOp")

public class TestTeleOp extends OpMode {

    public enum LiftState{
        LIFT_START,
        LIFT_EXTEND,
        LIFT_DUMP,
        LIFT_RETRACT
    };
    LiftState liftState = LiftState.LIFT_START;

    DcMotor leftFront, rightFront, leftRear, rightRear;
    DcMotor duckW, harvester, spool;
    Servo dumpster;
    // CRServo topSlide, tape;
    boolean bumperButtonState = true;
    boolean grabIsActive = true;
    boolean inState = false;
    boolean inActive = false;
    // boolean capState = false;
    // boolean capActive = false;
    // int padPressed;
    // int block = 1000;
    // int distance = block * padPressed;
    ElapsedTime liftTimer = new ElapsedTime();

    final double DUMP_IDLE = .2; // the idle position for the dump servo
    final double DUMP_DEPOSIT = .635; // the dumping position for the dump servo

    // the amount of time the dump servo takes to activate in seconds
    final double DUMP_TIME = 350;

    final int LIFT_LOW = 0; // the low encoder position for the lift
    final int LIFT_HIGH = 1430;



    @Override
    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightRear = hardwareMap.dcMotor.get("rightRear");
        duckW = hardwareMap.dcMotor.get("duckW");
        harvester = hardwareMap.dcMotor.get("harvester");
        dumpster = hardwareMap.servo.get("dumpy");
        spool = hardwareMap.dcMotor.get("spool");

        rightRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        rightRear.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        leftFront.setPower(0);
        duckW.setPower(0);
        harvester.setPower(0);
        spool.setPower(0);

        spool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        spool.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftTimer.reset();
    }

    @Override
    public void loop() {

        switch (liftState){
            case LIFT_START:
                if (gamepad2.a){
                    spool.setTargetPosition(LIFT_HIGH);
                    liftState = LiftState.LIFT_EXTEND;
                }
                break;
            case LIFT_EXTEND:
                if (Math.abs(spool.getCurrentPosition() - LIFT_HIGH) < 10) {
                    dumpster.setPosition(DUMP_DEPOSIT);
                    liftTimer.reset();
                    liftState = liftState.LIFT_DUMP;
                }
                break;
            case LIFT_DUMP:
                if (liftTimer.seconds() >= DUMP_TIME) {
                    dumpster.setPosition(DUMP_IDLE);
                    spool.setTargetPosition(LIFT_LOW);
                    liftState = liftState.LIFT_RETRACT;
                }
                break;
            case LIFT_RETRACT:
                if (Math.abs(spool.getCurrentPosition() - LIFT_LOW) < 10) {
                    liftState = liftState.LIFT_START;
                }
                break;



        }

        if (gamepad1.y && liftState != liftState.LIFT_START) {
            liftState = liftState.LIFT_START;
        }

        double drive;
        double strafe;
        double rotate;

        drive = -gamepad1.left_stick_y * .75;
        strafe = gamepad1.left_stick_x * .75;
        rotate = gamepad1.right_stick_x * .75;

        double driveInput = drive;
        double strafeInput = strafe;
        double rotateInput = rotate;

        duckW.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spool.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spool.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        telemetry.addData("spool", spool.getCurrentPosition());
        telemetry.addData("leftFront", leftFront.getCurrentPosition());
        telemetry.addData("rightFront", rightFront.getCurrentPosition());
        telemetry.addData("leftRear", leftRear.getCurrentPosition());
        telemetry.addData("rightRear", rightRear.getCurrentPosition());


        if (gamepad1.left_bumper) {
            driveInput = drive / 2;
            strafeInput = strafe / 2;
            rotateInput = rotate / 2;
            leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else if (gamepad1.right_bumper) {
            driveInput = drive * 2;
            strafeInput = strafe * 2;
            rotateInput = rotate * 2;
        } else {
            driveInput = drive;
            strafeInput = strafe;
            rotateInput = rotate;
        }

        leftFront.setPower(driveInput + strafeInput + rotateInput);
        leftRear.setPower(driveInput - strafeInput + rotateInput);
        rightFront.setPower(driveInput - strafeInput - rotateInput);
        rightRear.setPower(driveInput + strafeInput - rotateInput);

        spool.setPower(-gamepad2.left_stick_y);

        if (gamepad2.dpad_left) {
            duckW.setPower(.53);
        } else if (gamepad2.dpad_right) {
            duckW.setPower(-.53);
        } else {
            duckW.setPower(0);
        }


        if (gamepad2.a && !bumperButtonState) {
            grabIsActive = !grabIsActive;
        }

        if (grabIsActive) {
            dumpster.setPosition(.2);
        } else {
            dumpster.setPosition(.635);
        }

        if (gamepad2.left_bumper && !inState) {
            inActive = !inActive;
        }

        if (inActive) {
            harvester.setPower(-.62);
        } else if (gamepad2.right_bumper) {
            harvester.setPower(.2);
        } else if (gamepad2.y) {
            harvester.setPower(-.4);
        } else if (gamepad2.x) {
            harvester.setPower(-1);
        } else {
            harvester.setPower(0);
        }

        inState = gamepad2.left_bumper;
        bumperButtonState = gamepad2.a;

    }
}
