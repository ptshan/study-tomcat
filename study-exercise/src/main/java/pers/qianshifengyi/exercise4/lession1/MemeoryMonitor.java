package pers.qianshifengyi.exercise4.lession1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshan on 17/7/4.
 * /Library/Java/JavaVirtualMachines/jdk1.8.0_40.jdk/Contents/Home/bin
 */
public class MemeoryMonitor {

    static class OOMObject{
        public byte[] placeHolder = new byte[64 * 1024];
    }

    /**
     * VM参数: -Xms100m -Xmx100m -XX:+UseSerialGC
     * @param num
     * @throws Exception
     */
    public static void fillHeap(int num)throws Exception{
        List<OOMObject> list = new ArrayList<OOMObject>();
        for(int i=0;i<num;i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception{
        fillHeap(1000);
    }

}
