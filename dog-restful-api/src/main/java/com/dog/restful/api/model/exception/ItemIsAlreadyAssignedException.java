package com.dog.restful.api.model.exception;

import java.text.MessageFormat;

public class ItemIsAlreadyAssignedException extends RuntimeException {

    public ItemIsAlreadyAssignedException(final Long subBreedId, final Long BreedId) {
        super(MessageFormat.format("Item: {0} is already assigned to Breed: {1}", subBreedId, BreedId));

    }
}
