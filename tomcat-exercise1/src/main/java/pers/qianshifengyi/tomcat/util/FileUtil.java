package pers.qianshifengyi.tomcat.util;

import java.io.*;

/**
 * SocketInputStream 的流不要关闭,关闭流会间接关闭socket,导致socket被关闭异常
  public void close() throws IOException {
 // Prevent recursion. See BugId 4484411
 if (closing)
 return;
 closing = true;
 if (socket != null) {
 if (!socket.isClosed())
 socket.close();
 } else
 impl.close();
 closing = false;
 }
 * Created by zhangshan on 17/5/2.
 */
public class FileUtil {

    /**
     * 根据路径获取文件内容
     * @param path
     * @return
     */
    public static String getFileContent(String path){
        try {
            return getContentFromIS(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFileContent(String baseDir,String path){
        if(StringUtils.isBlank(baseDir)){
            return getFileContent(path);
        }
        else{
            if(!baseDir.endsWith("/")){
                baseDir = baseDir+"/";
            }
            String absPath = baseDir+path;
            return getFileContent(absPath);
        }
    }

    public static String getContentFromIS(InputStream is){
        StringBuilder sb = new StringBuilder();
        String content = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = br.readLine();
            while(line != null && !line.trim().equals("")){
                sb.append(line);
                System.out.println(line);
                line = br.readLine();
            }
            content = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void writeContent2OS(String content,OutputStream os){
        PrintWriter pw = new PrintWriter(os);
        pw.write(content);
    }

    public static void writeFile2OS(String path,OutputStream os){
        try{

            InputStream is = new FileInputStream(path);
            byte[] buf = new byte[2048];
            int i = is.read(buf);
            while(i != -1){
                os.write(buf,0,i);
                i = is.read(buf);
            }
            os.flush();
            closeStream(is);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void closeStream(Closeable ... streams){
        for(Closeable stream :streams){
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
