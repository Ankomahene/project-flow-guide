package io.turntabl.orc.jsonToORC.model;

public class Device {
    private String id;
    private String alternateId;
    private Double temperature;
    private Double pressure;
    private String channelId;

    public Device() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlternateId() {
        return alternateId;
    }

    public void setAlternateId(String alternateId) {
        this.alternateId = alternateId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", alternateId='" + alternateId + '\'' +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", channelId='" + channelId + '\'' +
                '}';
    }
}
