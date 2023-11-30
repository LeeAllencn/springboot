package com.rocky.boot.liteflow;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @author : rocky
 * @date : created in 2023/11/30
 */
@Component("c")
public class CCmp extends NodeComponent {

    @Override
    public void process() {
        //do your business
        System.out.println("this is c");
    }
}
