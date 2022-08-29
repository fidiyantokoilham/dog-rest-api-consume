package com.dog.restful.api.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubBreedResponse {
    private List<String> message = new ArrayList<>();
    private String status;
}
