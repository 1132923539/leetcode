public class Solution10 {

    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int i =0;
        int j =1;
        for(j=0;j<nums.length;j++){
            if(nums[i] == nums[j]) {
                continue;
            }
            i = i+1;
            nums[i] = nums[j];
        }
        return i+1;
    }
}
