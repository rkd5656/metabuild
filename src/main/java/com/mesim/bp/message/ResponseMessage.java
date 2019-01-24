package com.mesim.bp.message;

/**
 * Created by MisunKim
 * Date: 2019-01-24 오후 2:02
 */
public class ResponseMessage {

    Object data;
    boolean success; // 성공여부

    public ResponseMessage() {}
    public ResponseMessage(String data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
