package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.List;


import java.util.List;

@Disabled
@Autonomous(name = "No")
public class Park_Far extends LinearOpMode {
    DcMotor leftFront, rightFront, leftRear, rightRear;
    DcMotor duckW, harvester, spool;
    Servo dumpster;
// //   DcMotor rightScissor, leftScissor, leftIn, rightIn;
// //   CRServo topSlide;
// //   Servo foundation1, foundation2,scoreSlide;
// //   ColorSensor colorSensor;
// //   DistanceSensor distanceSensor;
// //   boolean left = false;
// //   boolean right = false;
// //   boolean middle = false;


// // //    private static final double TICKS_PER_REV_LIFT = 1120;
// // //    private static final double MM_PER_REV_LIFT = 1;
// // //    private static final double TICKS_PER_REV_DRIVE = 537.6;
// // //    private static final double MM_PER_REV_DRIVE = 16;

// //   void Turn180() {
// //       leftFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
// //       leftRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
// //       rightFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
// //       rightRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

// //       leftFront.setTargetPosition(-2875);
// //       leftRear.setTargetPosition(2875);
// //       rightFront.setTargetPosition(-2875);
// //       rightRear.setTargetPosition(-2875);
// //       leftFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       leftRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       rightFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       rightRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       leftFront.setPower(-.5);
// //       leftRear.setPower(.5);
// //       rightFront.setPower(-.5);
// //       rightRear.setPower(-.5);
// //       while (opModeIsActive()&& leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
// //           sleep(0);
// //       }
// //       leftFront.setPower(0);
// //       leftRear.setPower(0);
// //       rightFront.setPower(0);
// //       rightRear.setPower(0);
// //       leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// //       rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// //       leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// //       rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// //   }


    void Turn45Right() {
        leftFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-430);
        leftRear.setTargetPosition(-430);
        rightFront.setTargetPosition(430);
        rightRear.setTargetPosition(430);
        leftFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftFront.setPower(-.3);
        leftRear.setPower(-.3);
        rightFront.setPower(.3);
        rightRear.setPower(.3);
        while (opModeIsActive()&& leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(0);
        }
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    void Turn90Right() {
        leftFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-660);
        leftRear.setTargetPosition(-660);
        rightFront.setTargetPosition(660);
        rightRear.setTargetPosition(660);
        leftFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftFront.setPower(-.3);
        leftRear.setPower(-.3);
        rightFront.setPower(.3);
        rightRear.setPower(.3);
        while (opModeIsActive()&& leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(0);
        }
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

// //   void Turn90Left() {
// //       leftFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
// //       leftRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
// //       rightFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
// //       rightRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

// //       leftFront.setTargetPosition(-1415);
// //       leftRear.setTargetPosition(-1415);
// //       rightFront.setTargetPosition(1415);
// //       rightRear.setTargetPosition(-1415);
// //       leftFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       leftRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       rightFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       rightRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       leftFront.setPower(.5);
// //       leftRear.setPower(-.5);
// //       rightFront.setPower(.5);
// //       rightRear.setPower(.5);
// //       while (opModeIsActive()&& leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
// //           sleep(0);
// //       }
// //       leftFront.setPower(0);
// //       leftRear.setPower(0);
// //       rightFront.setPower(0);
// //       rightRear.setPower(0);
// //       leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// //       rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// //       leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// //       rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// //   }

    void Forward (int distance) {
        //int ticksToDrive = (int) Math.floor() ;
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(distance * 28 /* 29  */);
        leftRear.setTargetPosition(distance * 28);
        rightFront.setTargetPosition(distance * 28);
        rightRear.setTargetPosition(distance * 28);
        leftFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftFront.setPower(.3);
        leftRear.setPower(.3);
        rightFront.setPower(.3);
        rightRear.setPower(.3);
        while (opModeIsActive()&& leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(0);
        }
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    void Backward (int distance) {
        leftFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-distance * 28);
        leftRear.setTargetPosition(-distance * 28);
        rightFront.setTargetPosition(-distance * 28);
        rightRear.setTargetPosition(-distance * 28);
        leftFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftFront.setPower(-.2);
        leftRear.setPower(-.2);
        rightFront.setPower(-.2);
        rightRear.setPower(-.2);
        while (opModeIsActive()&& leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(0);
        }
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    void strafeLeft (int distance) {
        leftFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(distance * 43);
        leftRear.setTargetPosition(-distance * 43);
        rightFront.setTargetPosition(-distance * 43);
        rightRear.setTargetPosition(distance * 43);
        leftFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftFront.setPower(.5);
        leftRear.setPower(-.5);
        rightFront.setPower(-5);
        rightRear.setPower(.5);
        while (opModeIsActive()&& leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(0);
        }
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    void strafeRight (int distance) {
        leftFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-distance * 43);
        leftRear.setTargetPosition(distance * 43);
        rightFront.setTargetPosition(distance * 43);
        rightRear.setTargetPosition(-distance * 43);
        leftFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftFront.setPower(-.5);
        leftRear.setPower(.5);
        rightFront.setPower(.5);
        rightRear.setPower(-.5);
        while (opModeIsActive()&& leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(0);
        }
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

// //   void Intake (int distance) {
// //       //int ticksToDrive = (int) Math.floor() ;
// //       leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
// //       leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
// //       rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
// //       rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
// //       leftScissor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
// //       rightScissor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

// //       leftFront.setTargetPosition(distance * 64);
// //       leftRear.setTargetPosition(-distance * 64);
// //       rightFront.setTargetPosition(-distance * 64);
// //       rightRear.setTargetPosition(-distance * 64);
// //       leftIn.setTargetPosition(6000);
// //       rightIn.setTargetPosition(6000);
// //       leftFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       leftRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       rightFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       rightRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       leftIn.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       rightIn.setMode(DcMotor.RunMode. RUN_TO_POSITION);
// //       leftFront.setPower(1);
// //       leftRear.setPower(-1);
// //       rightFront.setPower(-1);
// //       rightRear.setPower(-1);
// //       leftIn.setPower(-1);
// //       rightIn.setPower(1);
// //       while (opModeIsActive()&& leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy() && rightIn.isBusy() && leftIn.isBusy()) {
// //           sleep(0);
// //       }
// //       scoreSlide.setPosition(.09);
// //       leftFront.setPower(0);
// //       leftRear.setPower(0);
// //       rightFront.setPower(0);
// //       rightRear.setPower(0);
// //       rightIn.setPower(0);
// //       leftIn.setPower(0);
// //       leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       leftIn.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       rightIn.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// //       rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// //       leftRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
// //       rightRear.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

// //   }

// //   void Grab() {
// //       Turn90Left();
// //       Forward(8);
// //       strafeRight(12);
// //       Intake(8);
// //       Lower();
// //       strafeLeft(12);
// //   }

// //   void Lower() {
// //       leftScissor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       rightScissor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       leftScissor.setTargetPosition(-400);
// //       rightScissor.setTargetPosition(-400);
// //       leftScissor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
// //       rightScissor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
// //   }
// //   void Raise() {
// //       leftScissor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       rightScissor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
// //       leftScissor.setTargetPosition(400);
// //       rightScissor.setTargetPosition(400);
// //       leftScissor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
// //       rightScissor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

// //   }
// //   void foundationGrab() {
// //       foundation1.setPosition(.67);
// //       foundation2.setPosition(.34);
// //   }
// //   void foundation() {
// //       foundation1.setPosition(1);
// //       foundation2.setPosition(0);
// //   }

    void scoreTop() {
        spool.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spool.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

        spool.setTargetPosition(1430);
        spool.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        spool.setPower(1);
        while (opModeIsActive()&& spool.isBusy()) {
            sleep(0);
        }
        spool.setPower(0);
        spool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dumpster.setPosition(.635);
        sleep(3000);
        dumpster.setPosition(.2);
        sleep(1000);
        spool.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

        spool.setTargetPosition(-1430);
        spool.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        spool.setPower(-1);
        while (opModeIsActive()&& spool.isBusy()) {
            sleep(0);
        }
        spool.setPower(0);
        spool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }






    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftRear = hardwareMap.dcMotor.get("leftRear");
        rightRear = hardwareMap.dcMotor.get("rightRear");
        duckW = hardwareMap.dcMotor.get("duckW");
        harvester = hardwareMap.dcMotor.get("harvester");
        spool = hardwareMap.dcMotor.get("spool");
        dumpster = hardwareMap.servo.get("dumpy");

// //       leftScissor = hardwareMap.dcMotor.get("leftScissor");
// //       rightScissor = hardwareMap.dcMotor.get("rightScissor");
// //       scoreSlide = hardwareMap.servo.get("scoreSlide");
// //       topSlide = hardwareMap.crservo.get("topSlide");
// //       foundation1 = hardwareMap.servo.get("foundation1");
// //       foundation2 = hardwareMap.servo.get("foundation2");
// //       leftIn = hardwareMap.dcMotor.get("leftIn");
// //       rightIn = hardwareMap.dcMotor.get("rightIn");
// //       colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor1");
// //       distanceSensor = hardwareMap.get(DistanceSensor.class, "colorSensor1");

        leftRear.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
// //       rightScissor.setDirection(DcMotor.Direction.REVERSE);
// //       leftIn.setDirection(DcMotor.Direction.REVERSE);


        rightRear.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        leftFront.setPower(0);
        duckW.setPower(0);
        harvester.setPower(0);
        spool.setPower(0);


        while (!isStarted() && !isStopRequested()) {
            telemetry.addData("time", getRuntime());
            telemetry.update();
        }
        //blue side
        while (opModeIsActive()) {
            dumpster.setPosition(.2);
            Backward(27);
            scoreTop();
            Forward(16);
            sleep(200);
            Turn45Right();
            sleep(200);
            Forward(30);
            sleep(200);
            Turn90Right();
            sleep(200);
            Backward(8);
            sleep(200);
            duckW.setPower(-.5);
            sleep(3500);
            duckW.setPower(0);
            sleep(250);
            Forward(17);
            strafeLeft(10);

            sleep(9000000);
        }
    }
}