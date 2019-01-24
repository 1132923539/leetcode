import java.util.Arrays;

/**
 * 归并排序算法
 *
 * @author eltons,  Date on 2018-12-29.
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] a={5,6,3,2,8,5};
        mergeSort(a,0,5);
        System.out.println(Arrays.toString(a));
    }

    //合并算法
    private static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[a.length];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= low && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= high) {
            temp[k++] = a[j++];
        }
        for (int l = 0; l < temp.length; l++) {
            a[low + l] = temp[l];
        }
    }

    private static void mergeSort(int[] a, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(a,low,mid);
            mergeSort(a,mid+1,high);
            merge(a,low,mid,high);
        }
    }

}
