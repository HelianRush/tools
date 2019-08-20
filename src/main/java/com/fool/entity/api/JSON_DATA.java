package com.fool.entity.api;

import com.alibaba.fastjson.JSONObject;

public class JSON_DATA {

    private JSON_HEAD head;
    private JSONObject body;

    public JSON_DATA() {
    }

    public JSON_DATA(JSON_HEAD head, JSONObject body) {
        this.head = head;
        this.body = body;
    }

    public JSON_HEAD getHead() {
        return head;
    }

    public void setHead(JSON_HEAD head) {
        this.head = head;
    }

    public JSONObject getBody() {
        return body;
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "JSON_DATA{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }
}
