package me.windleafy.gity.java.event;

/**
 * Created by gaokao on 16/8/31.
 */

public class BusEvent<T> {

    public BusEvent(String type) {
        this.type = type;
    }

    public BusEvent(String type, T data) {
        this.type = type;
        this.data = data;
    }

    private String type;

    private T data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
