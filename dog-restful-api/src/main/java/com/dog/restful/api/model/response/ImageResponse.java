package com.dog.restful.api.model.response;

import lombok.Data;

import java.util.List;

@Data
public class ImageResponse {
    private List<String> message;
    private String status;
}
