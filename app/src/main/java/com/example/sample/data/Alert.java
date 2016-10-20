package com.example.sample.data;

import java.io.Serializable;

/**
 * Created by manjula on 9/21/16.
 */
public class Alert implements Serializable {
    private String msg_key;
    private int id;
    private int building_id;
    private String msg_value;
    private String msg_description;

    public Alert() {
    }

    public Alert(String msg_key, int id, int building_id, String msg_value, String msg_description) {
        this.msg_key = msg_key;
        this.id = id;
        this.building_id = building_id;
        this.msg_value = msg_value;
        this.msg_description = msg_description;
    }

    public String getMsg_key() {
        return msg_key;
    }

    public void setMsg_key(String msg_key) {
        this.msg_key = msg_key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public String getMsg_value() {
        return msg_value;
    }

    public void setMsg_value(String msg_value) {
        this.msg_value = msg_value;
    }

    public String getMsg_description() {
        return msg_description;
    }

    public void setMsg_description(String msg_description) {
        this.msg_description = msg_description;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "msg_key='" + msg_key + '\'' +
                ", id=" + id +
                ", building_id=" + building_id +
                ", msg_value='" + msg_value + '\'' +
                ", msg_description='" + msg_description + '\'' +
                '}';
    }
}
