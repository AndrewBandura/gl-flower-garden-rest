package com.flowergarden.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReduceFreshnessResponse {

    public final static String ERROR_MSG = "zero or negative value";

    int id;
    boolean processed;
    String error;
}
