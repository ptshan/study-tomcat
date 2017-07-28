package pers.qianshifengyi.test;

import org.junit.Test;

/**
 * Created by zhangshan193 on 17/4/5.
 */
public class SortTest1 {

    /**
     * 冒泡排序
     */
    @Test
    public void testBubbleSort(){
        int[] intArrs = {49,38,65,97,76,13,27,49};
        int temp;
        boolean exchange = false;
        for(int i=0;i<=intArrs.length-1;i++){
            // 每一趟选择出一个最大数
            for(int j=intArrs.length-1;j>i;j--){
                if(intArrs[j]<intArrs[j-1]){
                    temp = intArrs[j];
                    intArrs[j] = intArrs[j-1];
                    intArrs[j-1] = temp;
                    exchange = true;
                }
            }
            if(exchange == true){
                break;
            }
        }
    }

    /**
     * 选择排序
     */
    @Test
    public void testSelectSort(){
        int[] intArrs = {49,38,65,97,76,13,27,49};
        int temp;
        for(int i=0;i<=intArrs.length-1;i++){
            int k = i;
            for(int j=i+1;j<=intArrs.length-1;j++){
                // k记录小的数字的下标
                if(intArrs[j]<intArrs[k]){
                    k = j;
                }
            }
            if(k != i){
                temp = intArrs[k];
                intArrs[k] = intArrs[i];
                intArrs[i] = temp;
            }
        }
    }

    /**
     * 直接插入排序
     */
    @Test
    public void testInsertSort(){
        int[] intArrs = {0,49,38,65,97,76,13,27,49};
        int j;
        for(int i=2;i<=intArrs.length-1;i++){

            if(intArrs[i] < intArrs[i-1]){
                intArrs[0] = intArrs[i];
                j = i-1;
                do{
                    intArrs[j+1] = intArrs[j];
                    j--;
                }while(intArrs[0] > intArrs[j]);
                intArrs[j+1] = intArrs[0];
            }

        }
    }

    @Test
    public void testShellSort(){
        int[] intArrs = {0,49,38,65,97,76,13,27,49};
        int increament = intArrs.length/3+1;
        int j;
        do{
            for(int i=increament+1;i<=intArrs.length-1;i++){
                if(intArrs[i] < intArrs[i-increament]){
                    intArrs[0] = intArrs[i-increament];
                    j = i - increament;
                    do{
                        intArrs[j+increament] = intArrs[j];
                        j = j- increament;
                    }while(j>0&& intArrs[0]<intArrs[j]);
                    intArrs[j+increament] = intArrs[0];
                }


            }



        }while(increament > 1);




    }






}
