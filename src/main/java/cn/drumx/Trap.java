package cn.drumx;

/**
 * @author Mark Zheng drumx.cn @鸡腿毁灭者
 * @description 装雨水 https://leetcode.cn/problems/trapping-rain-water
 * @create 2025-01-15 15:58
 */
public class Trap {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int maxarea = trap(height);
        System.out.println(maxarea);
    }

    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int l_max = 0, r_max = 0;

        int res = 0;
        while (left < right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            // res += min(l_max, r_max) - height[i]
            if (l_max < r_max) {
                res += l_max - height[left];
                left++;
            } else {
                res += r_max - height[right];
                right--;
            }
        }
        return res;
    }
}