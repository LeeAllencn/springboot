package com.rocky.boot.flowable.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.flowable.engine.impl.util.CommandContextUtil;

import java.io.IOException;

/**
 * @author : rocky
 * @date : created in 2023/4/18
 */
public class TimerJobHandlerUtil {

    public static final String NOTIFY_EVENT_NAME = "notifyEventName";
    public static final String MESSAGE = "message";

    public static final String TIMER_TASK_ID = "taskId";
    public static final String TIMER_SERVICE_NAME = "serviceName";
    public static final String TIMER_BEAN_NAME = "beanName";

    public static String getNotifyEventName(String configuration) {
        try {
            ObjectNode cfgJson = readJsonValueAsObjectNode(configuration);
            JsonNode jsonNode = cfgJson.get(NOTIFY_EVENT_NAME);
            return jsonNode.textValue();
        } catch (IOException e) {
            return configuration;
        }
    }

    public static String getMessage(String configuration) {
        try {
            ObjectNode cfgJson = readJsonValueAsObjectNode(configuration);
            JsonNode jsonNode = cfgJson.get(MESSAGE);
            return jsonNode.textValue();
        } catch (IOException e) {
            return configuration;
        }
    }

    protected static ObjectNode createObjectNode() {
        if (CommandContextUtil.getProcessEngineConfiguration() == null) {
            return null;
        }
        return CommandContextUtil.getProcessEngineConfiguration().getObjectMapper().createObjectNode();
    }

    protected static ObjectNode readJsonValueAsObjectNode(String config) throws IOException {
        return (ObjectNode) readJsonValue(config);
    }

    protected static JsonNode readJsonValue(String config) throws IOException {
        if (CommandContextUtil.getCommandContext() != null) {
            return CommandContextUtil.getProcessEngineConfiguration().getObjectMapper().readTree(config);
        } else {
            return new ObjectMapper().readTree(config);
        }
    }

    public static String createJobHandlerConfiguration(String notifyEventName, String message) {
        ObjectNode cfgJson = createObjectNode();
        cfgJson.put(NOTIFY_EVENT_NAME, notifyEventName);
        cfgJson.put(MESSAGE, message);
        return cfgJson.toString();
    }

    public static String getTaskId(String configuration) {
        try {
            ObjectNode cfgJson = readJsonValueAsObjectNode(configuration);
            JsonNode jsonNode = cfgJson.get(TIMER_TASK_ID);
            return jsonNode.textValue();
        } catch (IOException ex) {
            return configuration;
        }
    }

    public static String getServiceName(String configuration) {
        try {
            ObjectNode cfgJson = readJsonValueAsObjectNode(configuration);
            JsonNode jsonNode = cfgJson.get(TIMER_SERVICE_NAME);
            return jsonNode.textValue();
        } catch (IOException ex) {
            return configuration;
        }
    }

    public static String getBeanName(String configuration) {
        try {
            ObjectNode cfgJson = readJsonValueAsObjectNode(configuration);
            JsonNode jsonNode = cfgJson.get(TIMER_BEAN_NAME);
            return jsonNode.textValue();
        } catch (IOException ex) {
            return configuration;
        }
    }

    public static String createTimerJobHandlerConfig(String taskId, String serviceName, String beanName) {
        ObjectNode cfgJson = createObjectNode();
        if (cfgJson == null) {
            return null;
        }
        cfgJson.put(TIMER_TASK_ID, taskId);
        cfgJson.put(TIMER_SERVICE_NAME, serviceName);
        cfgJson.put(TIMER_BEAN_NAME, beanName);
        return cfgJson.toString();
    }
}
