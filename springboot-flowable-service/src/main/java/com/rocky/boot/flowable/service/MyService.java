package com.rocky.boot.flowable.service;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/5
 */
@Service
public class MyService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * Exception异常时，数据回滚
     */
    @Transactional(rollbackFor = {Exception.class})
    public void startProcess() {
        runtimeService.startProcessInstanceByKey("oneTaskProcess");
    }

    /**
     * Exception异常时，数据回滚
     */
    @Transactional(rollbackFor = {Exception.class})
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

}
