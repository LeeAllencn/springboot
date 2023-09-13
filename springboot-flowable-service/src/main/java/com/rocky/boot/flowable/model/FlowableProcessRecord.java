package com.rocky.boot.flowable.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 流程记录信息表
 * </p>
 *
 * @author rocky
 * @since 2023-04-14
 */
@Data
@TableName("flowable_process_record")
public class FlowableProcessRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uuid
     */
    @TableId("uuid")
    private String uuid;

    /**
     * 流程实例id
     */
    @TableField("process_instance_id")
    private String processInstanceId;

    /**
     * 流程定义key
     */
    @TableField("process_definition_key")
    private String processDefinitionKey;

    /**
     * 流程定义名称
     */
    @TableField("process_definition_name")
    private String processDefinitionName;

    /**
     * 流程状态
     */
    @TableField("status")
    private String status;

    /**
     * 资源uuid
     */
    @TableField("resource_uuid")
    private String resourceUuid;

    /**
     * 资源类型
     */
    @TableField("resource_type")
    private String resourceType;

    /**
     * 流程开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 流程结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    @TableField("create_user")
    private String createUser;

    @TableField("update_user")
    private String updateUser;

    /**
     * 流程是否存活
     */
    @TableField("active")
    private Boolean active;

    /**
     * 错误信息
     */
    @TableField("message")
    private String message;
}
