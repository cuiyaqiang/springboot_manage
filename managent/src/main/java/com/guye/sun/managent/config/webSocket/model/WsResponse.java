package com.guye.sun.managent.config.webSocket.model;

/**
 * WsResponse
 */
public class WsResponse<T> {
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
