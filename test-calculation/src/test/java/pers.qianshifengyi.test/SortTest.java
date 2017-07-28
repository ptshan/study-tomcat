package pers.qianshifengyi.test;

import org.junit.Test;

import java.util.Vector;

/**
 * Created by zhangshan193 on 17/3/28.
 */
public class SortTest {

    /**
     * 1、直接插入排序
     */
    @Test
    public void testInsertSort(){
        // intArrs[0] 称为哨兵,起到监视下标j是否越界
        int[] intArrs = {0,49,38,65,97,76,13,27,49};
        int i=0,j=0;
        for(i=2;i <= intArrs.length-1;i++){
            // 如果后面的数比前面的数大,才进入if,否则不处理
            // 因为0是哨兵,所以要从下标 0 1 2的  2开始,因为从2开始才是比较处理第二个和第一个元素
            if(intArrs[i]<intArrs[i-1]){
                // 用哨兵临时存储下标大的数据
                intArrs[0] = intArrs[i];
                // 用j记录较小的数的下标
                j = i - 1;
                do{
                    // 因为是升序排列,大的数字在后面,故需要将最开始的intArrs[i] 和 intArrs[i-1] 交换,由于前面
                    // intArrs[i] 放到了 intArrs[0]哨兵中临时存储,所以此处是将下标小的赋给下标大的
                    // 由于 j = i - 1;所以 j当前为较小下标
                    intArrs[j+1] = intArrs[j];
                    j--;
                }while(intArrs[0]<intArrs[j]);
                intArrs[j+1]=intArrs[0];
            }
            // 打印
            System.out.println("i="+i+"    "+printArr(intArrs));
        }
    }

    /**
     * 2、希尔排序
     * 主要思想:例如有10个数字
     * 1、先分5组,(1,6)、(2,7)、(3,8)、(4,9)、(5,10),然后组内进行直接插入排序
     * 2、再分3组,即(1、4、7、10)、(2、5、8)、(3、6、9),然后组内直接插入排序
     * 3、再分一组,组内直接插入排序
     */
    @Test
    public void testShellPass(){
        int[] intArrs = {0,49,38,65,97,76,13,27,49};

        // 设置增量
        int increment = intArrs.length;
        do{
            increment = increment/3 + 1;

            int j = 0;
            for(int i=increment+1;i<=intArrs.length-1;i++){
                if(intArrs[i]<intArrs[i-increment]){
                    intArrs[0]=intArrs[i];
                    j = i - increment;
                    do{
                        intArrs[j+increment]=intArrs[j];
                        j = j-increment;
                    }while(j>0 && intArrs[0]< intArrs[j]);
                    intArrs[j+increment] = intArrs[0];
                }

                //打印
                System.out.println("i="+i+"    "+printArr(intArrs));
            }

        }while(increment > 1);
        System.out.println(SortTest.printArr(intArrs));
    }

    /**
     * 3、冒泡排序
     */
    @Test
    public void bubbleSortTest(){
        int[] intArrs = {49,38,65,97,76,13,27,49};

        int i,j;
        boolean exchange = false;
        int temp;
        for(i=0;i<intArrs.length-1;i++){
            for(j=intArrs.length-1;j>i;j--){
                if(intArrs[j] < intArrs[j -1]){
                    temp = intArrs[j];
                    intArrs[j] = intArrs[j -1];
                    intArrs[j -1] = temp;
                    exchange = true;
                }
            }
            // 如果未发生过数据交换,则
            if(!exchange){
                return;
            }
            System.out.println("i="+i+"    "+printArr(intArrs));
        }
    }


    /**
     * 4、选择排序
     * 主要是第i次循环选出最小的数和下标i的数交换
     * @return
     */
    @Test
    public void testSelectSort(){
        int[] intArrs = {49,38,65,97,76,13,27,49};

        int i,j,k;
        int temp;
        for(i=0;i<intArrs.length-1;i++){
            // 用于记录最小数的下标,初始值为i
            k = i;
            // j为i+1即为k后面的一个元素
            for(j = i+1;j<=intArrs.length-1 ;j++){
                if(intArrs[j] < intArrs[k]){
                    k = j;
                }
            }
            if(k != i){
                temp = intArrs[i];
                intArrs[i]=intArrs[k];
                intArrs[k]=temp;
            }
            System.out.println("i="+i+"    "+printArr(intArrs));
        }
    }

    /**
     * 归并排序:
     * 针对的是两个已排序的数组将其合并成一个数组
     */
    @Test
    public void testMergeSort(){
        // {11,17,26,35}
        Vector<Integer> v1 = new Vector<Integer>();
        v1.add(11);v1.add(17);v1.add(26);v1.add(35);
        // {15,25,37,42,51}
        Vector<Integer> v2 = new Vector<Integer>();
        v2.add(15);v2.add(25);v2.add(37);v2.add(42);v2.add(51);
        Vector<Integer> v3 = new Vector<Integer>();
        v3.addAll(v1);
        v3.addAll(v2);

        Vector<Integer> v4 = new Vector<Integer>();
        int low = 0,m=3,high=8;
        int i=low,j=m+1;
        // 依次比较v3的v3.get(i)和v3.get(j),取较小的放入v4
        while(i<=m && j<=high){
            v4.add(v3.get(i)<=v3.get(j)?v3.get(i++):v3.get(j++));
        }
        // 将i到m之间的数据全部装入v4
        while(i<=m){
            v4.add(v3.get(i++));
        }
        // 将j到high之间的数据全部装入v4
        while(j<=high){
            v4.add((v3.get(j++)));
        }
        System.out.print("归并后:");
        for(int temp:v4){
            System.out.print("  "+temp);
        }
    }

    @Test
    public void testMergePass(){
        Vector<Integer> v = new Vector();
        v.add(25);v.add(57);v.add(48);v.add(37);v.add(12);v.add(92);v.add(86);
        System.out.print("原数据:");
        for(int temp:v){
            System.out.print("  "+temp);
        }
        System.out.println();
        for(int length = 1; length<v.size(); length = length * 2){
            int i=0;
            for(i = 0;i+2*length-1 < v.size();i = i+2*length){
                Vector<Integer> resultVector = mergeSort(v,i,i+length-1,i+2*length-1);
                System.out.print("length:"+length+" i="+i+" 归并后:");
                for(int temp:resultVector){
                    System.out.print("  "+temp);
                }
                System.out.println();
            }

            if(i+length-1 < v.size()){
                Vector<Integer> resultVector = mergeSort(v,i,i+length-1,v.size()-1);
                System.out.print("--length:"+length+" i="+i+" 归并后:");
                for(int temp:resultVector){
                    System.out.print("  "+temp);
                }
                System.out.println();
            }
        }
    }

    public Vector<Integer> mergeSort(Vector<Integer> v,int low ,int m,int high){
        Vector<Integer> v4 = new Vector<Integer>();
        int i=low,j=m+1;
        // 依次比较v3的v3.get(i)和v3.get(j),取较小的放入v4
        while(i<=m && j<=high){
            v4.add(v.get(i)<=v.get(j)?v.get(i++):v.get(j++));
        }
        // 将i到m之间的数据全部装入v4
        while(i<=m){
            v4.add(v.get(i++));
        }
        // 将j到high之间的数据全部装入v4
        while(j<=high){
            v4.add((v.get(j++)));
        }
        return v4;
    }

    @Test
    public void testQuickSort(){
        int[] intArgs = {49,38,65,97,76,13,27,49};

        QuickSort(intArgs,0,intArgs.length-1);

        System.out.println(printArr(intArgs));
    }

    /**
     * 对数组进行排序,初始时,low为0,high为intArgs.length - 1
     * @param intArgs
     * @param low
     * @param high
     */
    private void QuickSort(int[] intArgs,int low,int high){
        int pivotpos ; // 划分后的基准记录位置
        if(low < high){ // 仅当区间长度大于1才开始排序
            pivotpos = Partition(intArgs,low,high); //对intArgs进行划分
            QuickSort(intArgs,low,pivotpos-1); // 对左区间进行递归排序
            QuickSort(intArgs,pivotpos+1,high); // 对右区间进行递归排序
        }
    }

    /**
     * 区间划分算法
     * 以pivot为基准,从j向左扫描的时候,若intArgs[j]< pivot 则将 intArgs[i++] = intArgs[j]
     * 从i向右扫描,若intArgs[i] > pivot,则将intArgs[j--] = intArgs[i]
     * @param intArgs
     * @param i
     * @param j
     * @return
     */
    private int Partition(int[] intArgs,int i,int j){
        int pivot = intArgs[i]; // 用区间第一个元素作为基准
        while(i < j){ // 从区间两边向中间扫描,直至i=j为止
            while(i<j && pivot <= intArgs[j]){ // pivot相当于在位置i上
                j--; // 从右向左扫描,查找第一个关键字小于pivot的记录intArgs[j]
            }
            if(i < j){ // 表示找到关键字 intArgs[j] 小于 pivot
                intArgs[i++] = intArgs[j]; // 相当于交换intArgs[i]和intArgs[j],交换后i指针加1
            }
            while(i<j && intArgs[i] <= pivot){ // pivot相当于在位置j上
                i++; // 从左向右扫描,查找第一个关键字大于pivot的记录intArgs[i]
            }
            if(i < j){ // 表示找到了intArgs[i],使得intArgs[i] > pivot
                intArgs[j--]=intArgs[i]; //相当于交换intArgs[i]和intArgs[j],交换后,j减1
            }
        }
        intArgs[i] = pivot; // 基准记录已被最后定位
        return i;

    }



    /**
     * 堆排序
     */
    @Test
    public void testHeapSort(){
        int[] intArgs = {42,13,91,23,24,16,5,88};
        BuildHeap(intArgs);
        System.out.println("调整后的大根堆  "+printArr(intArgs));
        int temp;
        for(int i=intArgs.length-1;i>1;i--){
            temp = intArgs[i];
            intArgs[i]=intArgs[0];
            intArgs[0]=temp;
            Heapify(intArgs,0,i-1);
        }
        System.out.println(printArr(intArgs));
    }

    private void BuildHeap(int[] intArgs){
        for(int i = intArgs.length/2;i>=0;i--){
            Heapify(intArgs,i,intArgs.length-1);
        }
    }

    /**
     * 将intArgs从low到high调整为大根堆,除intArgs[low]外,其余结点均满足堆性质
     * @param intArgs
     * @param low
     * @param high
     */
    private void Heapify(int[] intArgs,int low,int high){
        int temp = intArgs[low]; // 暂存调整节点
        // large指向调整结点的左右孩子结点中,关键字较大者
        for(int large = 2 * low;large <= high; large = large * 2){
            // 若large > high则表示intArgs[low]是叶子节点,则本次调整结束,否则先让large指向intArgs[low]的左孩子
            if(large < high && intArgs[large] < intArgs[large + 1]){
                large ++;
            }
            // 现在intArgs[large]是调整结点intArgs[low]的左右孩子结点中关键字较大者
            if( temp > intArgs[large]){
                break;
            }
            intArgs[low] = intArgs[large]; // 相当于交换了intArgs[low] 和 intArgs[large]
            low = large; // 将low指向新的调整节点
        }
        intArgs[low] = temp; // 将被调整结点放入最终位置上

    }


    public static String printArr(int[] intArrs){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<intArrs.length;i++){
            if(i != intArrs.length -1){
                sb.append(intArrs[i]).append(",");
            }else{
                sb.append(intArrs[i]);
            }
        }
        return sb.toString();

    }



}
