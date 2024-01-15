package com.rocky.boot.common.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/12
 */
public class DataTypeConversionUtilTest {

    @Test
    public void map2Object() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "rocky");
        map.put("age", 28);
        User user = (User) DataTypeConversionUtil.map2Object(map, User.class);
        System.out.println(user.toString());
    }
}