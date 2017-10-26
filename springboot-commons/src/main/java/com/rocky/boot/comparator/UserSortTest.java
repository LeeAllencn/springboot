package com.rocky.boot.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rocky on 2017-10-26.
 */
public class UserSortTest {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<User>();
        userList.add(new User("aa", 5));
        userList.add(new User("cc", 10));
        userList.add(new User("ff", 4));
        userList.add(new User("bb", 8));
        userList.add(new User("dd", 7));
        userList.add(new User("ee", 8));

        UserComparator comparator = new UserComparator();
        Collections.sort(userList, comparator);

        for (User user : userList) {
            System.out.println(user.getName() + "-->" + user.getAge());
        }
    }
}
