package io.a97lynk.base.jpa;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDto<ID extends Number> implements Serializable {

    protected ID id;

}
