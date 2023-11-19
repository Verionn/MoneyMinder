package com.minder.MoneyMinder.controllers.list.dto;


public record UpdateListRequestBody(String name, String description
) {
    public UpdateListRequestBody(String name) {
        this(name, "");
    }
}