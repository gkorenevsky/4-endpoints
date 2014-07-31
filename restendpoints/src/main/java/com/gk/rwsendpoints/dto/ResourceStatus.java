package com.gk.rwsendpoints.dto;

import java.util.Date;

/**
 * Created by greg korenevsky on 8/2/14.
 */
public class ResourceStatus {

    public enum Status {
        OK,
        UNAVAILABLE,
        ERROR
    }

    String resourceName;
    String description;
    String status;
    Date   timeStamp;
    String diagnosticMessage;

    public String getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus.Status status) {
        this.status = status.toString();
    }

    public ResourceStatus withStatus(ResourceStatus.Status status) {
        setStatus(status);
        return this;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ResourceStatus withTimeStamp(Date timeStamp) {
        setTimeStamp(timeStamp);
        return this;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public ResourceStatus withResourceName(String resourceName) {
        setResourceName(resourceName);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResourceStatus withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDiagnosticMessage() {
        return diagnosticMessage;
    }

    public void setDiagnosticMessage(String diagnosticMessage) {
        this.diagnosticMessage = diagnosticMessage;
    }

    public ResourceStatus withDiagnosticMessage(String diagnosticMessage) {
        setDiagnosticMessage(diagnosticMessage);
        return this;
    }

}
