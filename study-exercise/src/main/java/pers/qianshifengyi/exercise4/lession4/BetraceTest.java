package pers.qianshifengyi.exercise4.lession4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by zhangshan on 17/7/4.
 */
public class BetraceTest {

    public int add(int a,int b){
        return a + b;
    }

    public static void main(String[] args)throws Exception {

        BetraceTest betraceTest = new BetraceTest();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<10;i++){
            br.readLine();
            int a = (int) Math.round(Math.random()*1000);
            int b = (int) Math.round(Math.random()*1000);
            System.out.println(betraceTest.add(a,b));
        }


    }



}
