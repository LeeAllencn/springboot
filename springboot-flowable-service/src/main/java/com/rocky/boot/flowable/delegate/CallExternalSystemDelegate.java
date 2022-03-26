package com.rocky.boot.flowable.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 * @author : rocky
 * @date : created in 2022/3/26
 */
public class CallExternalSystemDelegate implements JavaDelegate {


    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("Do the external system for employee "
                + execution.getVariable("employee"));
    }
}
