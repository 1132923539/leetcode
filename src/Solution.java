/**
 * @author eltons,  Date on 2018-11-21.
 * 两数之和，给定一个数组，和一个数sum，返回数组中两个相加能得到sum的数的下标
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result=new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                    return result;
                }
            }
        }
        return null;
    }
}