package com.rocky.boot.enums;

/**
 * Created by Rocky on 2017-10-31.
 */
public enum AppStatusEnum {
    CREATING("Creating","创建中"),
    RUNNING("Running","运行中"),
    PENDING("Pending","部署中"),
    FAILED("Failed","失败"),
    SUCCEEDED("Succeeded","完成"),
    STOPPING("Stopping","停止中"),
    STOPPED("Stopped","已停止"),
    STARTING("Starting","启动中"),
    MODIFYING("Modifying","修改中"),
    DELETING("Deleting","删除中"),
    UPGRADING("Upgrading","升级中");

    private String key;
    private String value;

    private AppStatusEnum(String key, String value) {
        this.key = key;
        this.setValue(value);
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueByKey(String key) {
        if(key != null) {
            for(AppStatusEnum ase : AppStatusEnum.values()) {
                if(ase.getKey().equals(key)) {
                    return ase.getValue();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(AppStatusEnum.getValueByKey("Creating"));
    }
}
