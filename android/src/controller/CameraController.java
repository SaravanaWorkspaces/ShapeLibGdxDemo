package controller;

import com.badlogic.gdx.graphics.PerspectiveCamera;

/**
 * This handles camera event to move up/down/rounds right/rounds left.
 * <p/>
 *
 * @author saravanakumar.chinraj
 * @version 1.0
 * @company Impiger
 * @package CameraController
 * @copyright Copyright (C) 2016 Impiger. All rights reserved.
 */
public class CameraController {

    private final int UP_DOWN = 0;
    private final int ROTATE = 1;

    private float xPosition = 10.0f;
    private float yPosition = 10.0f;
    private float zPosition = 10.0f;

    private boolean isMovingUp = false;

    private PerspectiveCamera mPerspectiveCamera;

    public CameraController(PerspectiveCamera perspectiveCamera) {
        this.mPerspectiveCamera = perspectiveCamera;
        mPerspectiveCamera.position.set(xPosition, yPosition, zPosition);
        mPerspectiveCamera.lookAt(0, 0, 0);
        mPerspectiveCamera.near = 1f;
        mPerspectiveCamera.far = 300f;
        mPerspectiveCamera.update();
    }

    /**
     * updates camera coordinates on the screen.
     */
    public void updateCamera() {
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

}
