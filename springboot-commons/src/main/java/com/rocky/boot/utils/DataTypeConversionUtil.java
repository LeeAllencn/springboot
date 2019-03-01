package com.rocky.boot.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author rocky
 * @Description: 数据类型转换
 * @Date: Created in 2019/3/1
 */
public class DataTypeConversionUtil {

    /**
     * Map转javabean
     * @param map
     * @param beanClass
     * @return
     */
    public static Object map2Object(Map<String, Object> map, Class<?> beanClass){
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            // 创建一个对象实例
            obj = beanClass.newInstance();
            // 拿到对象所有的属性值
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                // 获取属性的修饰符（如：public static）
                int mod = field.getModifiers();
                if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                    continue;
                }
                // 跳过属性的权限检查
                field.setAccessible(true);
                // 属性默认设置的类型为String，如果需要设置其他类型，自己单独判断后设置
                if (field.getType() == Long.class) {
                    field.set(obj, Long.valueOf(map.get(field.getName()).toString()));
                }else if (field.getType() == Date.class) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    field.set(obj, sdf.parse(map.get(field.getName()).toString()));
                } else {
                    field.set(obj, map.get(field.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
