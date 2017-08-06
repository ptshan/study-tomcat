package pers.qianshifengyi.test;

import org.junit.Test;

/**
 * Created by zhangshan193 on 17/4/5.
 */
public class SelectTest {

    @Test
    public void testBinarySearch(){
        int[] intArrs = {5,13,19,21,37,56,64,75,80,88,92};
        int low = 0,high = intArrs.length - 1;
        int mid;
        int k = 92;
        while(low <= high){
            mid = (low + high) / 2 ;
            System.out.println("intArrs["+mid+"]="+intArrs[mid]);
            if(intArrs[mid] == k){
                System.out.println("已经找到,mid="+mid+"    k="+k);
                break;
            }else
            if(intArrs[mid] > k){
                high = mid-1;
            }if(intArrs[mid] < k) {
                low = mid + 1;
            }
        }
        System.out.println("----- over");


    }






}
