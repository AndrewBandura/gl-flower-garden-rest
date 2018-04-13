package com.flowergarden.service;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReduceFreshnessResponse {

    public final static String ERROR_MSG = "zero or negative value";
    @XmlElement
    int id;
    @XmlAttribute
    boolean processed;
    @XmlAttribute
    String error;
}
