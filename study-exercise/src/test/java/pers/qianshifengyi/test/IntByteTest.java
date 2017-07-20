package pers.qianshifengyi.test;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by zhangshan on 17/7/12.
 */
public class IntByteTest {

    @Test
    public void testStringFormat(){
        for(int i=0;i<=25;i = i + 1){

           // System.out.println(Integer.toHexString(i));
            String hexString = Integer.toHexString(i).toUpperCase();
            String formatStr = "%08d0h";
            if(hexString.contains("[A-F]")){
                hexString = hexString.replaceFirst("[A-F]","");
                formatStr = "%08d0h";
            }
            String result = null;
            //String.format("%08d0h", Integer.parseInt());
            System.out.println(result);
        }

    }

    @Test
    public void testStringFormat2(){

    }

    @Test
    public void testReadClassFile()throws Exception{
        InputStream is = IntByteTest.class.getClassLoader().getResourceAsStream("TestClass.class");
        int i = is.read();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        int offset = 0;

        offset = offset + 10;
        String addr = String.format("%08dh:", offset);
     //   sb.append(addr).append("  ");
        while(i != -1){
            count++;

            String hexStr = Integer.toHexString(i).toUpperCase();
            hexStr = (hexStr.length() == 1) ? ("0"+hexStr) : hexStr;
            sb.append(hexStr).append("  ");
            if(count % 16 == 0){
                sb.append("\r\n");
                offset = offset + 10;
                System.out.println("offset:"+offset);
                addr = String.format("%08dh:", offset);
          //      sb.append(addr).append("  ");
            }
            i = is.read();
        }

        System.out.println(sb);
        is.close();
    }

//    public byte[] parseInt2Byte(int intValue){
//        byte[] intBytes = new byte[4];
//        intBytes[0] = intValue & 0x ;
//        intBytes[1] =  ;
//        intBytes[2] =  ;
//        intBytes[3] =  ;
//    }


    @Test
    public void testClassFile(){

    }

    @Test
    public void testByte2Int(){

    }

    @Test
    public void testInt2Byte(){

    }

}
