package com.rocky.boot.flowable.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author : rocky
 * @date : created in 2022/3/26
 */
public class SendRejectionMailDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("Send the rejection mail for employee "
                + execution.getVariable("employee"));
    }
}
