package com.rocky.boot.common.constant;

/**
 * 正则表达式常量样例
 * @author : rocky
 * @date : created in 2023/12/22
 */
public interface RegularConstants {

    /**
     * 名称：输入6-63个字符，可以包含小写英文字母、数字和中划线（-），并以小写字母开头，小写字母或者数字结尾。
     */
    String NAME = "^[a-z][a-z0-9\\-]{4,61}[a-z0-9]$";

    /**
     * 中文字符
     */
    String CHINESE_CHARACTERS = "[\\u4e00-\\u9fa5]";

    /**
     * Email地址
     */
    String EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

    /**
     * 网址URL
     */
    String URL = "^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+";

    /**
     * 手机（国内）
     */
    String PHONE = "0?(13|14|15|18|17)[0-9]{9}";

    /**
     * 整数
     */
    String INTEGER = "-?[1-9]\\d*";

    /**
     * 正整数
     */
    String POSITIVE_INTEGER = "[1-9]\\d*";

    /**
     * 负整数
     */
    String NEGATIVE_INTEGER = "-[1-9]\\d*";

    /**
     * 邮政编码
     */
    String POSTAL_CODE = "\\d{6}";

    /**
     * ip
     */
    String IP = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 身份证号
     */
    String ID_NUMBER = "\\d{17}[\\d|x]|\\d{15}";

    /**
     * 用户名
     */
    String USER_NAME = "[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+";
}
