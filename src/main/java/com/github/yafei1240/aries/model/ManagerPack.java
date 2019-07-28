package com.github.yafei1240.aries.model;

import com.github.yafei1240.aries.observer.Subject;
import com.github.yafei1240.aries.client.record.CallBack;
import com.github.yafei1240.aries.client.record.CustomerRecord;

public class ManagerPack {
    private CallBack callBack;
    private Subject<CustomerRecord> subject;
    private boolean process;

    public ManagerPack(Subject<CustomerRecord> subject) {
        this.subject = subject;
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public Subject<CustomerRecord> getSubject() {
        return subject;
    }

    public boolean isProcess() {
        return process;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public void setProcess(boolean process) {
        this.process = process;
    }
}
