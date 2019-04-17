package com.lv.libsocket;

public enum Action {

    HEARTBEAT("heartbeat", 200, null);

    private String action;
    private int reqEvent;
    private Class respClazz;

    Action(String action, int reqEvent, Class respClazz) {
        this.action = action;
        this.reqEvent = reqEvent;
        this.respClazz = respClazz;
    }

    public String getAction() {
        return action;
    }

    public int getReqEvent() {
        return reqEvent;
    }

    public Class getRespClazz() {
        return respClazz;
    }

}
