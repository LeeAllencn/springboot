package com.rocky.boot.results;

import java.util.List;

/**
 * Created by Rocky on 2017-10-10.
 */
public class ServiceResult implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private boolean isSuccess;
    private String message;
    private String data;
    private String type;
    private List<?> dataList;

    public ServiceResult(){}
    public ServiceResult(String message){
        this.message = message;
    }
    public ServiceResult(boolean isSuccess, String message){
        this.message = message;
        this.isSuccess = isSuccess;
    }
    public ServiceResult(boolean isSuccess, String message, String data){
        this.message = message;
        this.isSuccess = isSuccess;
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
