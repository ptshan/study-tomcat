package pers.qianshifengyi.test;

import org.junit.Test;

/**
 * Created by zhangshan193 on 17/3/30.
 */
public class MergeSortTest {

    /**
     * 合并算法
     * @param array
     * @param low
     * @param mid
     * @param high
     */
    public void Merge(int[] array, int low, int mid, int high) {
        int i = low; // i是第一段序列的下标
        int j = mid + 1; // j是第二段序列的下标
        int k = 0; // k是临时存放合并序列的下标
        int[] array2 = new int[high - low + 1]; // array2是临时合并序列

        // 扫描第一段和第二段序列，直到有一个扫描结束
        while (i <= mid && j <= high) {
            // 判断第一段和第二段取出的数哪个更小，将其存入合并序列，并继续向下扫描
            if (array[i] <= array[j]) {
                array2[k++] = array[i++];
            } else {
                array2[k++] = array[j++];
            }
        }
        // 若第一段序列还没扫描完，将其全部复制到合并序列
        while (i <= mid) {
            array2[k++] = array[i++];
        }
        // 若第二段序列还没扫描完，将其全部复制到合并序列
        while (j <= high) {
            array2[k++] = array[j++];
        }
        // 将合并序列复制到原始序列中
        for (k = 0, i = low; i <= high; i++, k++) {
            array[i] = array2[k];
        }

    }

    public void MergePass(int[] array, int gap, int length) {
        int i = 0;
        // 归并gap长度的两个相邻子表
        for (i = 0; i + 2 * gap - 1 < length; i = i + 2 * gap) {
            Merge(array, i, i + gap - 1, i + 2 * gap - 1);
            print("  gap="+gap+"  i="+i,array);
        }
        // 余下两个子表，后者长度小于gap
        if (i + gap - 1 < length) {
            Merge(array, i, i + gap - 1, length - 1);
            print("--gap="+gap+"  i="+i,array);
        }
    }

    /**
     * 分组算法
     * @return
     */
    @Test
    public void sort() {
        int[] list = {25,57,48,37,12,92,86};
        print("原数据:",list);
        for (int gap = 1; gap < list.length; gap = 2 * gap) {
            MergePass(list, gap, list.length);
        }

        print("排序结果:",list);
    }

    private void print(String str,int[] list){
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        for(int temp:list){
            sb.append("  ").append(temp);
        }
        System.out.println(sb.toString());
    }

}
