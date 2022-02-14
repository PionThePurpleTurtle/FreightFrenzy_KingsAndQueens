package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvWebcam;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

//@Disabled //Comment out to run
@Autonomous(name = "Red Duck and Park", group = "Auto")
public class DetectionDuckAutoRed extends LinearOpMode {

    OpenCvWebcam webcam;
    private String position;
    private double leftPer;
    private double middlePer;
    private double rightPer;
    DcMotor leftFront, rightFront, leftRear, rightRear;
    DcMotor duckW, harvester, spool;
    Servo dumpster;

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

        rightRear.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        rightRear.setPower(0);
        rightFront.setPower(0);
        leftRear.setPower(0);
        leftFront.setPower(0);
        duckW.setPower(0);
        harvester.setPower(0);
        spool.setPower(0);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        ShippingElementDetector detector = new ShippingElementDetector(telemetry);
        webcam.setPipeline(detector);
        position = detector.position;
        leftPer = detector.leftPer;
        middlePer = detector.middlePer;
        rightPer = detector.rightPer;
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() //Start streaming with the webcam
        {
            @Override
            public void onOpened()
            {
                /*
                 * Tell the webcam to start streaming images to us! Note that you must make sure
                 * the resolution you specify is supported by the camera. If it is not, an exception
                 * will be thrown.
                 */
                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });


        while (!isStarted() && !isStopRequested()){
            position = detector.position;
            leftPer = detector.leftPer;
            middlePer = detector.middlePer;
            rightPer = detector.rightPer;
            telemetry.addData("Percent left",leftPer);
            telemetry.addData("Percent middle",middlePer);
            telemetry.addData("Percent right",rightPer);
            telemetry.addData("Position", position);
            telemetry.addData("time", getRuntime());
            telemetry.addData("Frame Count", webcam.getFrameCount());
            telemetry.addData("FPS", String.format("%.2f", webcam.getFps()));
            telemetry.addData("Theoretical max FPS", webcam.getCurrentPipelineMaxFps());
            telemetry.update();
        }

        waitForStart();
        while (opModeIsActive()){
            telemetry.update();
            switch (detector.getElementPosition()){
                case LEFT: //top
                    telemetry.addLine("Position Detected: LEFT");
                    telemetry.update();

                    earlyActions();
                    Backward(17,.3);
                    sleep(100);
                    scoreLow();
                    Forward(30,.5);
                    sleep(100);
                    strafeLeft(11);
                    sleep(100);
                    Turn180();
                    sleep(100);
                    Backward(6,.2);

                    break;
                case MIDDLE: //mid
                    telemetry.addLine("Position Detected: MIDDLE");
                    telemetry.update();

                    earlyActions();
                    scoreMid();
                    strafeLeft(11);
                    sleep(100);
                    Turn180();
                    sleep(100);
                    Backward(6,.2);

                    break;
                case RIGHT: //low
                    telemetry.addLine("Position Detected: RIGHT");
                    telemetry.update();

                    earlyActions();
                    Backward(15,.3);
                    sleep(100);
                    scoreTop();
                    Forward(26,.5);
                    sleep(100);
                    strafeLeft(11);
                    sleep(100);
                    Turn180();
                    sleep(100);
                    Backward(10,.2);

                    break;
                default:
                    strafeRight(20);
                    telemetry.addLine("None");
                    telemetry.update();
                    break;



            }
            sleep(100000000);
        }
        webcam.stopStreaming();
    }

    void earlyActions(){
        dumpster.setPosition(.2);
        Backward(12, .3);
        sleep(100);
        Turn90Left();
        sleep(100);
        Backward(27,.5);
        sleep(100);
        Turn90Left();
        sleep(100);
        strafeLeft(12);
        sleep(100);
        Backward(8, .2);
        sleep(100);
        duck();
        sleep(100);
        strafeRight(4);
        sleep(100);
        Forward(31,.6);
        sleep(100);
        Turn90Left();
        Forward(10,.2);
        sleep(100);
        Backward(15,.6);
        sleep(100);
    }

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

        leftFront.setTargetPosition(660);
        leftRear.setTargetPosition(660);
        rightFront.setTargetPosition(-660);
        rightRear.setTargetPosition(-660);
        leftFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        leftFront.setPower(.3);
        leftRear.setPower(.3);
        rightFront.setPower(-.3);
        rightRear.setPower(-.3);
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
    void Turn90Left() {
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
    void Turn180() {
        leftFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

        leftFront.setTargetPosition(-1320);
        leftRear.setTargetPosition(-1320);
        rightFront.setTargetPosition(1320);
        rightRear.setTargetPosition(1320);
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


    void Forward (int distance, double power) {
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
        leftFront.setPower(power);
        leftRear.setPower(power);
        rightFront.setPower(power);
        rightRear.setPower(power);
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

    void Backward (int distance, double power) {
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
        leftFront.setPower(-power);
        leftRear.setPower(-power);
        rightFront.setPower(-power);
        rightRear.setPower(-power);
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

    void strafeRight (int distance) {
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
        rightFront.setPower(-.5);
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
        sleep(2500);
        dumpster.setPosition(.2);
        sleep(500);
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

    void scoreMid() {
        spool.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spool.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

        spool.setTargetPosition(700);
        spool.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        spool.setPower(1);
        while (opModeIsActive()&& spool.isBusy()) {
            sleep(0);
        }
        spool.setPower(0);
        spool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        dumpster.setPosition(.5);
        sleep(400);
        Backward(19,.3);
        dumpster.setPosition(.64);
        sleep(2500);
        dumpster.setPosition(.5);
        sleep(400);
        Forward(30, .5);
        sleep(100);
        dumpster.setPosition(.2);
        sleep(500);

        spool.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);
        spool.setTargetPosition(-700);
        spool.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        spool.setPower(-1);
        while (opModeIsActive()&& spool.isBusy()) {
            sleep(0);
        }
        spool.setPower(0);
        spool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void scoreLow() {
        spool.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        spool.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

        spool.setTargetPosition(475);
        spool.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        spool.setPower(1);
        while (opModeIsActive()&& spool.isBusy()) {
            sleep(0);
        }
        spool.setPower(0);
        spool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        dumpster.setPosition(.65);
        sleep(2500);
        dumpster.setPosition(.2);
        sleep(500);
        spool.setMode(DcMotor.RunMode. STOP_AND_RESET_ENCODER);

        spool.setTargetPosition(-475);
        spool.setMode(DcMotor.RunMode. RUN_TO_POSITION);
        spool.setPower(-1);
        while (opModeIsActive()&& spool.isBusy()) {
            sleep(0);
        }
        spool.setPower(0);
        spool.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    void duck(){
        duckW.setPower(-.5);
        sleep(3500);
        duckW.setPower(0);
    }

}