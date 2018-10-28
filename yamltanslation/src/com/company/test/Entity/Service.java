package com.company.test.Entity;

public class Service {
    private String apiVersion;
    private String labelsName;
    private String dataName;
    private String dataNameSpace;
    private int port;
    private int targetPort;
    private String selectorName;

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataNameSpace() {
        return dataNameSpace;
    }

    public void setDataNameSpace(String dataNameSpace) {
        this.dataNameSpace = dataNameSpace;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getLabelsName() {
        return labelsName;
    }

    public void setLabelsName(String labelsName) {
        this.labelsName = labelsName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTargetPort() {
        return targetPort;
    }

    public void setTargetPort(int targetPort) {
        this.targetPort = targetPort;
    }

    public String getSelectorName() {
        return selectorName;
    }

    public void setSelectorName(String selectorName) {
        this.selectorName = selectorName;
    }
}
