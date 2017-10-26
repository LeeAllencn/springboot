package com.rocky.boot.comparator;

import java.util.Comparator;

/**
 * Created by Rocky on 2017-10-26.
 */
public class UserComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        User user1 = (User)o1;
        User user2 = (User)o2;

        //首先比较年龄，若年龄相同，则比较名字
        int flag = user1.getAge().compareTo(user2.getAge());    //默认为升序，若想降序，须将user1和user2的位置互换
        if (flag == 0) {
            return user1.getName().compareTo(user2.getName());
        }
        return flag;
    }
}
