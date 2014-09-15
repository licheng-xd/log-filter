package org.lic.springmvc.log.meta;

import com.alibaba.fastjson.JSON;

/**
 * Created by lc on 14-7-17.
 */
public class ResponseEntry {

    private int code;

    private String result;

    private Object data;

    public ResponseEntry(int code) {
        this.code = code;
    }

    public ResponseEntry(int code, String result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseEntry{" + "code=" + code + ", result='" + result + '\''
            + ", data=" + data + '}';
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }
}
