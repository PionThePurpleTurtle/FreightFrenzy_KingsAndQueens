package org.firstinspires.ftc.teamcode;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvWebcam;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

//@Disabled //Comment out to run
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
        //webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

        while (!isStarted()){
            position = detector.position;
            leftPer = detector.leftPer;
            middlePer = detector.middlePer;
            rightPer = detector.rightPer;
            telemetry.addData("Percent left:",leftPer);
            telemetry.addData("Percent middle:",middlePer);
            telemetry.addData("Percent right:",rightPer);
            telemetry.addData("Position", position);
            telemetry.update();
        }

        waitForStart();

//        if (detector.getElementPosition() == ShippingElementDetector.ElementPosition.LEFT){
//            telemetry.addLine("Left");
//        }
//        else if (detector.getElementPosition() == ShippingElementDetector.ElementPosition.MIDDLE){
//            telemetry.addLine("Middle");
//
//        }
//        else {
//            telemetry.addLine("Right");
//        }
        switch (detector.getElementPosition()){
            case LEFT:
                telemetry.addLine("Left");
                break;
            case MIDDLE:
                telemetry.addLine("Middle");
                break;
            case RIGHT:
                telemetry.addLine("Right");
                break;
            default:
                telemetry.addLine("None");
                break;
        }

        telemetry.update();

        sleep(300000000);

        webcam.stopStreaming();
    }
}
