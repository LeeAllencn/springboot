package com.rocky.boot.tools;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * Created by Rocky on 2017-08-16.
 */
public class GenerateDruidPassword {
    public static void main(String[] args) throws Exception {
        String password = "adminpwd";
        String[] arr = ConfigTools.genKeyPair(512);
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        System.out.println("password:" + ConfigTools.encrypt(arr[0], password));
    }
}
