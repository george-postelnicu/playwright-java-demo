package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private String traceId;
    private String title;
    private String detail;
    private String status;
    private List<String> additionalInformation = new ArrayList<>();

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<String> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
