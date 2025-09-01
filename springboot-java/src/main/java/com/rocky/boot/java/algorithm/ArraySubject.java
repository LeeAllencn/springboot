package com.rocky.boot.java.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 数组相关
 * @author : rocky
 * @date : created in 2024/8/29 16:31
 */
public class ArraySubject {

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组
     * 实例 1：输入：nums = [-1,0,1,2,-1,-4]；输出：[[-1,-1,2],[-1,0,1]]
     * 实例 2：输入：nums = [0]；输出：[]
     * @param nums 整数的数组
     * @return 列表组
     */
    public static List<List<Integer>> threeNumSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();

        if (nums == null || nums.length < 3) {
            return Collections.emptyList();
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }

            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(ArraySubject.threeNumSum(nums));
    }
}
