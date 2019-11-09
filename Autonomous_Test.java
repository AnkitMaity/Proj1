package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
@Autonomous(name="Autonomous_Test", group="Pushbot")
public class Autonomous_Test extends LinearOpMode {
    // todo: write your code here
    // Declare OpMode members.
    public ElapsedTime runtime = new ElapsedTime();
    /* ashis
    public DcMotor Left_Motor_Front;
    public DcMotor Right_Motor_Front;
    public DcMotor Left_Motor_Back;
    public DcMotor Right_Motor_Back;
    public Servo SideArm;
    
    public double Four_Motor_Speed = .09;
    ashis */
    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "SKYSTONE FOUNDDDDDDDDDDDD";
    
    

    private static final String VUFORIA_KEY =
            "ATGTbhj/////AAABmZfbn4BskEYdltjKtCUmmyAWbC3srrhHUpo6KJgIklQhUnKsun2BRLIjAztnMpaOUb0VW/VuGWLO/+8IoJdAPsIhMIOMx+MxXeyMiPWfqMqvNNLcg4/7AxakkzGqQkQdTvNHPzF5Onv5pc6A/mCAdIG9d4Q9AE9NQeV3YL7kh4DmmPCxruHpu1tpDZjHhwU0NQmOALj2xHa5zuBf+0thqCERDfHCBfZt6q3641hQ3IZUcHXGJshw4f4npWOC6Nl2PEOozjqGYjzC9N55+wvGNUBXPzXx3epfoFBBUrWK26FsPZB/RtmU13ZN6IV391CIsdDRR3ynYg4LohcLtanrUtkLFidCkhdSH3yaIINOWFXZ\n";

    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    @Override
    public void runOpMode() throws InterruptedException {
       
/*  ashis nov 6 2019
        Right_Motor_Back = hardwareMap.get(DcMotor.class, "bottomright");
        Left_Motor_Back = hardwareMap.get(DcMotor.class, "bottomleft");
        Left_Motor_Front = hardwareMap.get(DcMotor.class, "topleft");
        Right_Motor_Front = hardwareMap.get(DcMotor.class, "topright");
        SideArm = hardwareMap.get(Servo.class, "SideArm");

        Left_Motor_Front.setDirection(DcMotor.Direction.FORWARD);
        Left_Motor_Back.setDirection(DcMotor.Direction.FORWARD);
        Right_Motor_Front.setDirection(DcMotor.Direction.REVERSE);
        Right_Motor_Back.setDirection(DcMotor.Direction.FORWARD);


        Left_Motor_Front.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        Left_Motor_Back.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        Right_Motor_Front.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        Right_Motor_Back.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
         ashis */
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode vvv");
        telemetry.update();
        /* ashis
        SideArm.setPosition(0.9);
        
        ForwardDistance(.5, 10);
 ashis */
        waitForStart();
        if (opModeIsActive()) {
            
            while (opModeIsActive()) {
                //Right_Motor_Back.setPower(Four_Motor_Speed);
               // Right_Motor_Front.setPower(Four_Motor_Speed);
                //Left_Motor_Back.setPower(Four_Motor_Speed);
                //Left_Motor_Front.setPower(Four_Motor_Speed);
                
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("454# Object Detected", updatedRecognitions.size());
                        
                        // step through the list of recognitions and display boundary info.
                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                            recognition.getLeft(), recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());
                                    
                                    if (recognition.getLabel().equals(LABEL_SECOND_ELEMENT)) {
                                
                                telemetry.addData("found!", "xxxxxxxxxxx"); ;
                                //SideArm.setPosition(0.1);
                                //Four_Motor_Speed = 0;
                                //Right_Motor_Back.setPower(Four_Motor_Speed);
                                //Right_Motor_Front.setPower(Four_Motor_Speed);
                                //Left_Motor_Back.setPower(Four_Motor_Speed);
                                //Left_Motor_Front.setPower(Four_Motor_Speed);
                                
                            }
                        }
                        telemetry.update();
                    }
                }
            }
        }
    }
    private void initVuforia(){
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
        "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        //tfodParameters.useObjectTracker = false;
        tfodParameters.minimumConfidence = 0.6;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
    /* ashis
    private void Forward(double power) {
        Left_Motor_Front.setPower(power);
        Left_Motor_Back.setPower(power);
        Right_Motor_Front.setPower(-power);
        Right_Motor_Back.setPower(power);
    }
    private void StopDriving(){
        Forward(0);
    }
    private void ForwardDistance(double power, int distance) {
        Left_Motor_Front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Left_Motor_Back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Right_Motor_Front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Right_Motor_Back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        double MOTOR_TICK_COUNTS = 753.2;
        double circum = 3.1415 * 3.93701;
        double rotations = distance / circum;
        
        distance = (int)(rotations * 753);
        
        
        Left_Motor_Front.setTargetPosition(distance);
        Left_Motor_Back.setTargetPosition(distance);
        Right_Motor_Front.setTargetPosition(-distance);
        Right_Motor_Back.setTargetPosition(distance);
        
         Left_Motor_Front.setPower(power);
        Left_Motor_Back.setPower(power);
        Right_Motor_Front.setPower(-power);
        Right_Motor_Back.setPower(power);
        
        Left_Motor_Front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Left_Motor_Back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Right_Motor_Front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Right_Motor_Back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        while(Left_Motor_Front.isBusy() || Left_Motor_Back.isBusy() || Right_Motor_Front.isBusy() || Right_Motor_Back.isBusy()){
            //wait until position is reached
            telemetry.addData("Mecanum Wheels", "Waiting");
            telemetry.update();
        }
        
        
        telemetry.addData("Path", "Complete");
        telemetry.update();
        Left_Motor_Front.setPower(0);
        Left_Motor_Back.setPower(0);
        Right_Motor_Front.setPower(0);
        Right_Motor_Back.setPower(0);
        StopDriving();
    }
    private void ForwardTime(double power, long distance)throws InterruptedException {
        Left_Motor_Front.setPower(power);
        Left_Motor_Back.setPower(power);
        Right_Motor_Front.setPower(-power);
        Right_Motor_Back.setPower(power);
    }
     ashis */
}