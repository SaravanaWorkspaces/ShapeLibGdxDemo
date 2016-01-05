package controller;

import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

import utils.Constants;

/**
 * This handles camera event to move up/down/rotates around the object.
 * <p/>
 *
 * @author saravanakumar.chinraj
 * @version 1.0
 * @company Impiger
 * @package CameraController
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class CameraController {


    private static final String TAG = "CameraController";
    /* Moving camera around the object */
    int camPathAngle = 0;
    /* Rotates around the object */
    private boolean isAround = false;
    /* Camera Positions */
    private float xPosition = 5.0f;
    private float yPosition = 0.0f;
    private float zPosition = 10.0f;

    private PerspectiveCamera mPerspectiveCamera;

    /* Swaps the camera rotation on Every 2 full rotation */
    private int swapCount = 0;

    /* Moves UpDown Camera */
    private boolean isMovingUp = false;

    public CameraController(PerspectiveCamera perspectiveCamera) {
        this.mPerspectiveCamera = perspectiveCamera;
        mPerspectiveCamera.position.set(xPosition, yPosition, zPosition);
        mPerspectiveCamera.lookAt(0, 0, 0);
        mPerspectiveCamera.near = 1f;
        mPerspectiveCamera.far = 300f;
        mPerspectiveCamera.update();
    }

    public void changeCameraRotation() {
        isAround = !isAround;
    }

    /**
     * {@link CameraController#isAround} checks and rotates around the object. If its true.
     */
    public void updateCamera() {
        if (isAround)
            rotateAround();
        else
            changeCameraPosition();

    }

    /**
     * Moving camera position towards up and Down.
     */
    private void changeCameraPosition() {
        yPosition = mPerspectiveCamera.position.y;
        if (isMovingUp) {
            yPosition += 0.1;
            if (yPosition > 20)
                isMovingUp = false;
        } else {
            yPosition -= 0.1;
            if (yPosition < 1)
                isMovingUp = true;
        }

        mPerspectiveCamera.position.set(xPosition, yPosition, zPosition);
        mPerspectiveCamera.update();
    }

    /**
     * Rotating the camera around the object.
     */
    private void rotateAround() {
        if (camPathAngle < Constants.FULL_ROTATION) {
            camPathAngle++;
        } else {
            swapCount++;
            camPathAngle = 0;
        }
        mPerspectiveCamera.position.set(xPosition, yPosition, zPosition);
        if (swapCount % 2 == 0) {
            mPerspectiveCamera.rotate(Vector3.Y, camPathAngle);
        } else {
            mPerspectiveCamera.rotate(Vector3.X, camPathAngle);
        }
        mPerspectiveCamera.lookAt(new Vector3(0, 0, 0));
        mPerspectiveCamera.update();
    }

}
