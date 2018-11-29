import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author eltons,  Date on 2018-11-26.
 */
public class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        double mid;
        int[] arr = new int[nums1.length + nums2.length];
        while (i < nums1.length || j < nums2.length) {
            if (i>=nums1.length){arr[i+j]=nums2[j++]; continue;}
            if (j>=nums2.length){arr[i+j]=nums1[i++]; continue;}
            int temp = Math.min(nums1[i], nums2[j]);
            arr[i+j]=temp;
            int s = nums1[i] < nums2[j] ? i++ : j++;
        }
        if (arr.length%2==1) {
            mid=arr[arr.length/2];
        }else{
            mid=((double) arr[arr.length/2]+(double) arr[arr.length/2-1])/2;
        }
        return mid;

    }

    @Test
    public void test() throws IOException {
        int[] nums1={1,3};
        int[] nums2={2};
        System.out.println(findMedianSortedArrays(nums1,nums2));;

    }


    /**
     * 关于思想：1. 确定了第一个数组中数的位置，就确定了第二个数组中数的位置
     * 2. 使用二分法挑选第一个数组中数的位置与对应的第二个数组中数的位置相比较
     */
    class Solution {
        public double findMedianSortedArrays(int[] A, int[] B) {
            int m = A.length;
            int n = B.length;
            if (m > n) { // to ensure m<=n
                int[] temp = A; A = B; B = temp;
                int tmp = m; m = n; n = tmp;
            }
            int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
            while (iMin <= iMax) {
                int i = (iMin + iMax) / 2;
                int j = halfLen - i;
                if (i < iMax && B[j-1] > A[i]){
                    iMin = i + 1; // i is too small
                }
                else if (i > iMin && A[i-1] > B[j]) {
                    iMax = i - 1; // i is too big
                }
                else { // i is perfect
                    int maxLeft = 0;
                    if (i == 0) { maxLeft = B[j-1]; }
                    else if (j == 0) { maxLeft = A[i-1]; }
                    else { maxLeft = Math.max(A[i-1], B[j-1]); }
                    if ( (m + n) % 2 == 1 ) { return maxLeft; }

                    int minRight = 0;
                    if (i == m) { minRight = B[j]; }
                    else if (j == n) { minRight = A[i]; }
                    else { minRight = Math.min(B[j], A[i]); }

                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0.0;
        }
    }
}
