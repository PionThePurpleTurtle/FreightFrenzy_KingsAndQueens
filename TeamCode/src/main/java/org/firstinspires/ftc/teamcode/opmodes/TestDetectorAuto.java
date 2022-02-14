package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.opmodes.ShippingElementDetector;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvWebcam;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Disabled //Comment out to run
@Autonomous(name = "TestDetectorAuto", group = "Auto")
public class TestDetectorAuto extends LinearOpMode {

    OpenCvWebcam webcam;
    private String position;
    private double leftPer;
    private double middlePer;
    private double rightPer;

    @Override
    public void runOpMode() throws InterruptedException {
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


        while (!isStarted()){
            position = detector.position;
            leftPer = detector.leftPer;
            middlePer = detector.middlePer;
            rightPer = detector.rightPer;
            telemetry.addData("Percent left",leftPer);
            telemetry.addData("Percent middle",middlePer);
            telemetry.addData("Percent right",rightPer);
            telemetry.addData("Position", position);
            telemetry.addData("Frame Count", webcam.getFrameCount());
            telemetry.addData("FPS", String.format("%.2f", webcam.getFps()));
            telemetry.addData("Theoretical max FPS", webcam.getCurrentPipelineMaxFps());
            telemetry.update();
        }

        waitForStart();
        if (opModeIsActive()){
            telemetry.update();
                switch (detector.getElementPosition()){
                    case LEFT:

                        telemetry.addLine("Position Detected: LEFT");
                        telemetry.update();
                        break;
                    case MIDDLE:
                        telemetry.addLine("Position Detected: MIDDLE");
                        telemetry.update();
                        break;
                    case RIGHT:
                        telemetry.addLine("Position Detected: RIGHT");
                        telemetry.update();
                        break;
                    default:
                        telemetry.addLine("None");
                        telemetry.update();
                        break;
                }

                telemetry.update();
                sleep(3000000);
            }
        webcam.stopStreaming();
    }
}
