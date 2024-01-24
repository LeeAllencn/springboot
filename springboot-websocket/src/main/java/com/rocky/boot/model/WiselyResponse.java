package com.rocky.boot.model;

/**
 * @author rocky
 */
public class WiselyResponse {
    private final String responseMessage;
    public WiselyResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage(){
        return responseMessage;
    }
}