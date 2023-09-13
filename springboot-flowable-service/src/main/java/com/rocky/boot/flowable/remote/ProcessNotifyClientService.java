package com.rocky.boot.flowable.remote;

import com.rocky.boot.common.model.BaseResult;
import com.rocky.boot.flowable.api.dto.ProcessNotifyDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : rocky
 * @date : created in 2023/4/19
 */
public interface ProcessNotifyClientService {

    @RequestMapping(value = "/remote/flowable/client/process/notify", method = RequestMethod.POST)
    BaseResult<Void> notify(@RequestParam("beanName") String beanName, @RequestBody ProcessNotifyDTO dto);
}
