package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;


public class ShippingElementDetector extends OpenCvPipeline {
    public enum ElementPosition{
        LEFT,
        MIDDLE,
        RIGHT
    }

    public double percentage (double num){
        num = num * 100;
        num = Math.floor(num);
        num = num / 100;
        return num;
    }
    public String position = "LEFT";
    public double leftPer;
    public double middlePer;
    public double rightPer;

    private ElementPosition elementPosition = ElementPosition.LEFT;
    Telemetry telemetry;
    Mat mat = new Mat();
    static final Rect LEFT_ROI = new Rect(
            new Point(80, 200),
            new Point(380, 520));
    static final Rect MIDDLE_ROI = new Rect(
            new Point(480, 200),
            new Point(770, 520));
    static final Rect RIGHT_ROI = new Rect(
            new Point(860, 200),
            new Point(1160, 520));

    public ShippingElementDetector(Telemetry t) { telemetry = t; } //Constructor

    @Override
    public Mat processFrame(Mat input) { //Turn color image to greyscale
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);

        Scalar lowHSV = new Scalar(23, 50, 70);//Color threshhold for yellow ----> for red??? (0, 100, 20), (10, 255, 255)
        Scalar highHSV = new Scalar(70, 255, 255); // 70 could be 32?? (10, 255, 255)

        Core.inRange(mat, lowHSV, highHSV, mat);  //Convert from color to greyscale
        Mat left = mat.submat(LEFT_ROI); //Create a new image that is a portion of the old one within the rectangle
        Mat middle = mat.submat(MIDDLE_ROI);
        Mat right = mat.submat(RIGHT_ROI);

        double leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255; //Find % of yellow within each box
        double middleValue = Core.sumElems(middle).val[0] / MIDDLE_ROI.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;

        left.release(); //Free up memory probably
        middle.release();
        right.release();


        leftPer =  percentage(leftValue*100); //leftValue * 100 = percentage with many decimals
        middlePer = percentage(middleValue*100);
        rightPer = percentage(rightValue*100);

//        telemetry.addData("Percent left:",leftPer);
//        telemetry.addData("Percent middle:",middlePer);
//        telemetry.addData("Percent right:",rightPer);
        telemetry.addData("Position", position);
        //telemetry.update();


        if(leftValue >= middleValue && leftValue >= rightValue){ //Set elementPosition to correct position
            elementPosition = ElementPosition.LEFT;

            position = "LEFT";
        }else if (rightValue >= middleValue){
            elementPosition = ElementPosition.RIGHT;
            position = "RIGHT";
        }else{
            elementPosition = ElementPosition.MIDDLE;
            position = "MIDDLE";
        }



        //For player-side debugging view (camera stream)
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB); //Convert greyscale back to rgb

        Scalar red = new Scalar(255,0,0); //define red and green vals for rectangles
        Scalar green = new Scalar(0,255,0);

        Imgproc.rectangle(mat, LEFT_ROI, elementPosition==ElementPosition.LEFT ? green : red); //Color each box green or red depending on whether it is the calculated position
        Imgproc.rectangle(mat, MIDDLE_ROI, elementPosition==ElementPosition.MIDDLE ? green : red);
        Imgproc.rectangle(mat, RIGHT_ROI, elementPosition==ElementPosition.RIGHT ? green : red);

        return mat;
    }
    public ElementPosition getElementPosition(){
        return elementPosition;
    }
}
