package com.rocky.boot.flowable.customer.process;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 流程业务逻辑处理
 * @author : rocky
 * @date : created in 2023/4/17
 */
@Slf4j
@Component
public class SampleProcess extends BaseProcessAdapter<SampleVariable> {

    @Override
    public void processNotifyAdapter(ProcessNotifyAdapterDTO<SampleVariable> adapterDTO) throws Exception {
        switch (adapterDTO.getEvent()) {
            case PROCESS_STARTED:
                log.info("流程开始");
                break;
            case PROCESS_COMPLETED:
                log.info("流程结束");
                break;
            case PROCESS_SUSPEND:
                log.info("流程中断");
                break;
            case PROCESS_DELETE:
                log.info("流程删除");
                break;
            case PROCESS_RETRY:
                log.info("流程重试");
                break;
            default:
                log.info("流程默认");
        }
    }
}
