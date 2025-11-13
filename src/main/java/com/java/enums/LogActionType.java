package com.java.enums;

import lombok.Getter;

@Getter
public enum LogActionType {

    USER_VIEW("Xem danh sach user"),
    USER_EDIT("Sua danh sach user"),
    USER_CREATE("Tao danh sach user"),
    USER_DELETE("Xoa danh sach user");


    private String actionName;

    LogActionType(String action) {
        this.actionName = action;
    }


}
