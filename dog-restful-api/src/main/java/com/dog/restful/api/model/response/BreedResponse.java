package com.dog.restful.api.model.response;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class BreedResponse {
    private HashMap<String, List<String>> message;
    private String status;
}
