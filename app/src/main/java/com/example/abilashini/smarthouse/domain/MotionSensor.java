package com.example.abilashini.smarthouse.domain;

/**
 * Created by Abilashini on 1/18/2016.
 */
public class MotionSensor {
    private int motionSensorId;
    private int motionSensorValue;

    public MotionSensor(int motionSensorId, int motionSensorValue) {
        this.motionSensorId = motionSensorId;
        this.motionSensorValue = motionSensorValue;
    }

    public int getMotionSensorId() {
        return motionSensorId;
    }

    public void setMotionSensorId(int motionSensorId) {
        this.motionSensorId = motionSensorId;
    }

    public int getMotionSensorValue() {
        return motionSensorValue;
    }

    public void setMotionSensorValue(int motionSensorValue) {
        this.motionSensorValue = motionSensorValue;
    }
}
