package pers.qianshifengyi.test;

import org.junit.Test;

/**
 * Created by zhangshan193 on 17/4/13.
 */
public class SortTest2 {

    @Test
    public void testInsertSort(){
        int[] intArrs = {0,49,38,65,97,76,13,27,49};
        int j;
        for(int i=2;i<intArrs.length;i++){
            if(intArrs[i] < intArrs[i-1]){
                intArrs[0] = intArrs[i];
                j = i-1;
                do{
                    intArrs[j+1] = intArrs[j];
                    j--;
                }while(j>0 && intArrs[0] < intArrs[j]);
                intArrs[j+1]  = intArrs[0];
            }
        }
        System.out.println(SortTest.printArr(intArrs));
    }

    @Test
    public void testSelectSort(){
        int[] intArrs = {49,38,65,97,76,13,27,49};
        int k;
        int temp;
        for(int i=0;i<intArrs.length;i++){
            k = i;
            for(int j=i+1;j<intArrs.length;j++){
                if(intArrs[j] < intArrs[k]){
                    k = j;
                }
            }
            if(k != i){
                temp = intArrs[k];
                intArrs[k] = intArrs[i];
                intArrs[i] = temp;
            }
        }

        System.out.println(SortTest.printArr(intArrs));
    }

    @Test
    public void testBubbleSort(){
        int[] intArrs = {49,38,65,97,76,13,27,49};
        int temp;
        boolean exchange = false;
        for(int i=0;i<intArrs.length;i++){
            for(int j=intArrs.length-1;j>i;j--){
                if(intArrs[i] > intArrs[j]){
                    temp = intArrs[i];
                    intArrs[i] = intArrs[j];
                    intArrs[j] = temp;
                    exchange = true;
                }
                if(exchange == true){
                    return;
                }
            }
        }
        System.out.println(SortTest.printArr(intArrs));
    }

    @Test
    public void testShellSort(){
        int[] intArrs = {0,49,38,65,97,76,13,27,49};
        int increament = intArrs.length ;
        int j;
        do {
            increament = increament/3+1;
            for(int i = increament+1;i<intArrs.length;i = i+1){
                if(intArrs[i]<intArrs[i-increament]){
                    intArrs[0] = intArrs[i];
                    j = i-increament;
                    do{
                        intArrs[j+increament] = intArrs[j];
                        j = j-increament;
                    }while(j>0 && intArrs[0] < intArrs[j]);
                    intArrs[j+increament] = intArrs[0];
                }
            }
        }while (increament > 1);
        System.out.println(SortTest.printArr(intArrs));
    }


}
