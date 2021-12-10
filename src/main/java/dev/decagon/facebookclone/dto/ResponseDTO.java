package dev.decagon.facebookclone.dto;

import dev.decagon.facebookclone.entity.User;



public class ResponseDTO {
    private String message;
    private User data;
    private Boolean status;

    public ResponseDTO() {
    }

    public ResponseDTO(String message, User data, Boolean status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public User getData() {
        return data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(User data) {
        this.data = data;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
