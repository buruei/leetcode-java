package cn.drumx;

//盛最多水的容器
public class WaterContainer {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int maxarea = maxArea(height);
        System.out.println(maxarea);
    }

    public static int maxArea(int[] height) {
        int leftpoint = 0;
        int rightpoint = height.length-1;
        int h1 = 0;
        int h2 = 0;
        int area = 0;
        int maxarea = 0;
        int len = 0;
        while (rightpoint - leftpoint > 0){
            h1 = height[leftpoint];
            h2 = height[rightpoint];
            len = rightpoint - leftpoint;
            area = Math.min(h1,h2) * len;
            maxarea = maxarea > area ? maxarea : area;
            if (h1 > h2){
                rightpoint--;
            } else{
                leftpoint++;
            }
        }
        return maxarea;
    }
}