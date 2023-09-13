package com.rocky.boot.flowable.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author rocky
 * @since 2023-04-21
 */
@Data
@TableName("act_ru_timer_job")
public class ActRuTimerJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ID_")
    private String id;

    @TableField("REV_")
    private Integer rev;

    @TableField("CATEGORY_")
    private String category;

    @TableField("TYPE_")
    private String type;

    @TableField("LOCK_EXP_TIME_")
    private Date lockExpTime;

    @TableField("LOCK_OWNER_")
    private String lockOwner;

    @TableField("EXCLUSIVE_")
    private Boolean exclusive;

    @TableField("EXECUTION_ID_")
    private String executionId;

    @TableField("PROCESS_INSTANCE_ID_")
    private String processInstanceId;

    @TableField("PROC_DEF_ID_")
    private String procDefId;

    @TableField("ELEMENT_ID_")
    private String elementId;

    @TableField("ELEMENT_NAME_")
    private String elementName;

    @TableField("SCOPE_ID_")
    private String scopeId;

    @TableField("SUB_SCOPE_ID_")
    private String subScopeId;

    @TableField("SCOPE_TYPE_")
    private String scopeType;

    @TableField("SCOPE_DEFINITION_ID_")
    private String scopeDefinitionId;

    @TableField("CORRELATION_ID_")
    private String correlationId;

    @TableField("RETRIES_")
    private Integer retries;

    @TableField("EXCEPTION_STACK_ID_")
    private String exceptionStackId;

    @TableField("EXCEPTION_MSG_")
    private String exceptionMsg;

    @TableField("DUEDATE_")
    private Date duedate;

    @TableField("REPEAT_")
    private String repeat;

    @TableField("HANDLER_TYPE_")
    private String handlerType;

    @TableField("HANDLER_CFG_")
    private String handlerCfg;

    @TableField("CUSTOM_VALUES_ID_")
    private String customValuesId;

    @TableField("CREATE_TIME_")
    private Date createTime;

    @TableField("TENANT_ID_")
    private String tenantId;
}
