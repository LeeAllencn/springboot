package com.rocky.boot.api.service;

/**
 * @author rocky
 */
public interface IMailService {

    /**
     * 发送简单邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送html邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 发送嵌入静态资源的邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     * @param rscPath 静态资源路径
     * @param rscId 静态资源id
     */
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
