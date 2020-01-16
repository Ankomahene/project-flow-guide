package io.turntabl.orc.jsonToORC.model;

import java.util.List;

public class DeviceList {
    private List<Device> deviceLst;

    public DeviceList() {
    }

    public DeviceList(List<Device> deviceLst) {
        this.deviceLst = deviceLst;
    }

    public List<Device> getDeviceLst() {
        return deviceLst;
    }

    public void setDeviceLst(List<Device> deviceLst) {
        this.deviceLst = deviceLst;
    }
}
