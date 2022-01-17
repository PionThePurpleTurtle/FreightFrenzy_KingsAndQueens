package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.State;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "One Controller")

public class KQ_TeleOp extends OpMode {

    DcMotor leftFront, rightFront, leftRear, rightRear;
    DcMotor duckW, harvester, spool;
    Servo dumpster;
    // DcMotor rightScissor, leftScissor, leftIn, rightIn;
    // Servo scoreSlide, blockM, cap, grab2, StoneSnatch;
    // CRServo topSlide, tape;
    // Servo foundation1, foundation2;
    boolean bumperButtonState = true;
    boolean grabIsActive = true;
    boolean inState = false;
    boolean inActive = false;
    // boolean capState = false;
    // boolean capActive = false;
    // int padPressed;
    // int block = 1000;
    // int distance = block * padPressed;


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
        //   leftScissor = hardwareMap.dcMotor.get("leftScissor");
        //   rightScissor = hardwareMap.dcMotor.get("rightScissor");
        //   scoreSlide = hardwareMap.servo.get("scoreSlide");
        //   tape = hardwareMap.crservo.get("tape");
        //   topSlide = hardwareMap.crservo.get("topSlide");
        //   foundation1 = hardwareMap.servo.get("foundation1");
        //   foundation2 = hardwareMap.servo.get("foundation2");
        //   leftIn = hardwareMap.dcMotor.get("leftIn");
        //   rightIn = hardwareMap.dcMotor.get("rightIn");
        //   blockM = hardwareMap.servo.get("blockM");
        //   grab2 = hardwareMap.servo.get("grab2");
        //   cap = hardwareMap.servo.get("cap");
        //   StoneSnatch = hardwareMap.servo.get("StoneSnatch");

        rightRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        //   leftRear.setDirection(DcMotor.Direction.FORWARD);
        //   leftFront.setDirection(DcMotor.Direction.REVERSE);
        //   rightIn.setDirection(DcMotor.Direction.REVERSE);


        rightRear.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        leftFront.setPower(0);
        duckW.setPower(0);
        harvester.setPower(0);
        spool.setPower(0);

        //   leftScissor.setPower(0);
        //   rightScissor.setPower(0);
        //   leftIn.setPower(0);
        //   rightIn.setPower(0);

        spool.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


    }

    @Override
    public void loop() {

        double drive;
        double strafe;
        double rotate;

        drive = gamepad1.left_stick_y * .75;
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


        //  leftScissor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //  rightScissor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //  leftScissor.setPower(-gamepad2.left_stick_y);
        //  rightScissor.setPower(-gamepad2.left_stick_y*.97);

        //  topSlide.setPower(Range.clip(gamepad2.right_stick_y, -.9, .9));

        if (gamepad1.left_trigger != 0) {
            spool.setPower(1);
        } else if (gamepad1.right_trigger != 0) {
            spool.setPower(-1);
        } else {
            spool.setPower(0);
        }

        if (gamepad1.dpad_left) {
            duckW.setPower(.53);
        } else if (gamepad1.dpad_right) {
            duckW.setPower(-.53);
        } else {
            duckW.setPower(0);
        }


        if (gamepad1.a && !bumperButtonState) {
            grabIsActive = !grabIsActive;
        }

        if (grabIsActive) {
            dumpster.setPosition(.2);
        } else {
            dumpster.setPosition(.635);
        }

        if (gamepad1.dpad_down && !inState) {
            inActive = !inActive;
        }

        if (inActive) {
            harvester.setPower(-.62);
        } else if (gamepad1.dpad_up) {
            harvester.setPower(.2);
        } else if (gamepad1.y) {
            harvester.setPower(-.4);
        } else if (gamepad1.x) {
            harvester.setPower(-1);
        } else {
            harvester.setPower(0);
        }

        inState = gamepad1.dpad_down;
        bumperButtonState = gamepad1.a;


        //  double rightInPower;
        //  double leftInPower;

        //  rightInPower = -gamepad1.right_trigger + gamepad1.left_trigger;
        //  leftInPower = gamepad1.right_trigger - gamepad1.left_trigger;

        //  rightIn.setPower(rightInPower);
        //  leftIn.setPower(leftInPower);

        //  if (gamepad1.right_bumper) {
        //      foundation1.setPosition(0);
        //      foundation2.setPosition(1);
        //  }

        //  else {
        //      foundation1.setPosition(1);
        //      foundation2.setPosition(0);

        //  }

        //  if (gamepad2.right_bumper) {
        //      blockM.setPosition(.15);
        //  }

        //  else {
        //      blockM.setPosition(.95);
        //  }

        //  if (gamepad2.a) {
        //      cap.setPosition(.55);
        //  }

        //  else{
        //      cap.setPosition(.8);
        //  }


        //  if (gamepad1.y) {
        //     tape.setPower(.9);
        //  }
        //  else if (gamepad1.b){
        //     tape.setPower(-.9);
        //  }
        //  else {
        //     tape.setPower(0);


        //  }
    }
}