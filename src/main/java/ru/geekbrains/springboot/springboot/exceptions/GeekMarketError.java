package ru.geekbrains.springboot.springboot.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class GeekMarketError {
    private int status;
    private String messages;
    private Date timestamp;

    public GeekMarketError(int status, String messages) {
        this.status = status;
        this.messages = messages;
        this.timestamp = new Date();
    }
}
