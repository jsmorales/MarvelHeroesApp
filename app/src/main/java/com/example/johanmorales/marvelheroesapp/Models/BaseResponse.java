package com.example.johanmorales.marvelheroesapp.Models;

/*
* Este modelo de datos se contruyo a mano
* para genarlo de forma automatica se puede hacer con:
* https://codebeautify.org/json-to-java-converter
* */

public class BaseResponse<T> {

    private Integer code;
    private String status;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
