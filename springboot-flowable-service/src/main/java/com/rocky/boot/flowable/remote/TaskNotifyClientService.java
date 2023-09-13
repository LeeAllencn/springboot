package com.rocky.boot.flowable.remote;

import com.rocky.boot.common.model.BaseResult;
import com.rocky.boot.flowable.model.dto.TaskNotifyDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : rocky
 * @date : created in 2023/4/20
 */
public interface TaskNotifyClientService {
    /**
     * 任务通知时间
     * @param beanName 对象名称
     * @param dto dto
     * @return void
     */
    @RequestMapping(value = "/remote/flowable/client/tasks/notify", method = RequestMethod.POST)
    BaseResult<Void> notify(@RequestParam("beanName") String beanName, @RequestBody TaskNotifyDTO dto);
}
