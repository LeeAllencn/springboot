package com.rocky.boot.utils;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Rocky
 * @date 2017/8/6
 */
@Component
public class TimeUtil implements Serializable {

    private static final long serialVersionUID = 5365373353352343251L;

    public Date now() {
        return new Date();
    }
}
