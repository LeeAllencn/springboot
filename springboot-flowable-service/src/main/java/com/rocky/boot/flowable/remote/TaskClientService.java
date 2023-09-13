package com.rocky.boot.flowable.remote;

import com.rocky.boot.common.model.BaseResult;
import com.rocky.boot.flowable.model.dto.ExecutionDTO;
import com.rocky.boot.flowable.model.resp.TaskHandleResp;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author : rocky
 * @date : created in 2023/4/20
 */
public interface TaskClientService {

    /**
     * 任务执行
     * @param beanName 对象名称
     * @param dto 参数dto
     * @return TaskHandleResp
     */
    @RequestMapping(value = "/remote/flowable/client/tasks/taskExecute", method = RequestMethod.POST)
    BaseResult<TaskHandleResp> taskExecute(@RequestParam("beanName") String beanName, @RequestBody ExecutionDTO dto);

    /**
     * 任务重试
     * @param beanName 对象名称
     * @param dto 参数dto
     * @return TaskHandleResp
     */
    @RequestMapping(value = "/remote/flowable/client/tasks/taskRetry", method = RequestMethod.POST)
    BaseResult<TaskHandleResp> taskRetry(@RequestParam("beanName") String beanName, @RequestBody ExecutionDTO dto);

    /**
     * 查询任务执行结果
     * @param beanName 对象名称
     * @param isLast 是否最后一次
     * @param dto 参数dto
     * @return TaskHandleResp
     */
    @RequestMapping(value = "/remote/flowable/client/tasks/taskQuery", method = RequestMethod.POST)
    BaseResult<TaskHandleResp> taskQuery(@RequestParam("beanName") String beanName, @RequestParam("isLast") boolean isLast, @RequestBody ExecutionDTO dto);
}
