package com.rocky.boot.common.enums;

import lombok.Getter;

/**
 * @author rocky
 */
@Getter
public enum AppStatusEnum {

    /**
     *
     */
    CREATING("Creating", "创建中"),
    RUNNING("Running", "运行中"),
    PENDING("Pending", "部署中"),
    FAILED("Failed", "失败"),
    SUCCEEDED("Succeeded", "完成"),
    STOPPING("Stopping", "停止中"),
    STOPPED("Stopped", "已停止"),
    STARTING("Starting", "启动中"),
    MODIFYING("Modifying", "修改中"),
    DELETING("Deleting", "删除中"),
    UPGRADING("Upgrading", "升级中");

    private final String key;
    private final String value;

    AppStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getValueByKey(String key) {
        if (key != null) {
            for (AppStatusEnum item : AppStatusEnum.values()) {
                if (item.getKey().equals(key)) {
                    return item.getValue();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(AppStatusEnum.getValueByKey("Creating"));
    }
}
