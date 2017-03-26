package com.example.abilashini.smarthouse.domain;

/**
 * Created by Abilashini on 1/18/2016.
 */
public class Door {
    private int doorSensorId;
    private int doorSensorValue;

    public Door(int doorSensorId, int doorSensorValue) {
        this.doorSensorId = doorSensorId;
        this.doorSensorValue = doorSensorValue;
    }

    public int getDoorSensorId() {
        return doorSensorId;
    }

    public void setDoorSensorId(int doorSensorId) {
        this.doorSensorId = doorSensorId;
    }

    public int getDoorSensorValue() {
        return doorSensorValue;
    }

    public void setDoorSensorValue(int doorSensorValue) {
        this.doorSensorValue = doorSensorValue;
    }
}
