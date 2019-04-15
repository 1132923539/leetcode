import java.util.Arrays;

/**
 * 二分法计算指定数值插入有序数组位置
 */
public class Solution12 {


    public static void main(String[] args) {
        //jdk的实现
        Arrays.binarySearch(new int[]{1,2,3},2);
    }

    public int searchInsert(int[] nums, int target) {
        int low =0;
        int high = nums.length-1;
        int mid = (low+high)/2;

        while(low <= high){
            if(nums[mid] == target) return mid;
            if( target < nums[mid]){
                high = mid -1;
            }else{
                low = mid +1;
            }
            mid = (low+high)/2;
        }
        return low;
    }
}
