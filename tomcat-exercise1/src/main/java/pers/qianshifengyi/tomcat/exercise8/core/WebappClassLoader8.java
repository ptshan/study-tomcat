package pers.qianshifengyi.tomcat.exercise8.core;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.loader.Reloader;
import org.apache.catalina.loader.ResourceEntry;
import org.apache.naming.resources.Resource;
import org.apache.naming.resources.ResourceAttributes;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessControlException;
import java.security.Permission;
import java.security.Policy;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by zhangshan on 17/6/5.
 */
public class WebappClassLoader8 extends URLClassLoader implements Reloader, Lifecycle {

    /**
     * The set of trigger classes that will cause a proposed repository not
     * to be added if this class is visible to the class loader that loaded
     * this factory class.  Typically, trigger classes will be listed for
     * components that have been integrated into the JDK for later versions,
     * but where the corresponding JAR files are required to run on
     * earlier versions.
     */
    private static final String[] triggers = {
            "javax.servlet.Servlet"                     // Servlet API
    };


    /**
     * Set of package names which are not allowed to be loaded from a webapp
     * class loader without delegating first.
     */
    private static final String[] packageTriggers = {
            "javax",                                     // Java extensions
            "org.xml.sax",                               // SAX 1 & 2
            "org.w3c.dom",                               // DOM 1 & 2
            "org.apache.xerces",                         // Xerces 1 & 2
            "org.apache.xalan"                           // Xalan
    };

    private ClassLoader parent;

    private ClassLoader system;

    private SecurityManager securityManager;

    private boolean started = false;

    private Map<String,ResourceEntry> resourceEntries = new HashMap<String,ResourceEntry>();

    protected String[] repositories = new String[0];

    protected File[] files = new File[0];

    protected JarFile[] jarFiles = new JarFile[0];

    protected File[] jarRealFiles = new File[0];

    protected String jarPath = null;

    protected String[] jarNames = new String[0];

    protected DirContext resources = null;

    private Permission allPermission = new java.security.AllPermission();

    private long[] lastModifiedDates = new long[0];

    private String[] paths = new String[0];

    private Map<String,String> notFoundResources = new HashMap<String,String>();

    /**
     * entry : 记录；词条；登录；录入
     * public class ResourceEntry{
     *     public long lastModified = -1;
     *     public byte[] binaryContent = null;
     *     public Class loadedClass = null;
     *     public URL source = null;
     *     public URL codeBase = null;
     *     public Manifest manifest = null;
     *     public Certificate[] certificates = null;
     * }
     * @param name
     * @param path
     * @return
     */
    protected ResourceEntry findResourceInternal(String name, String path) throws IOException {
        if(started == false){
            return null;
        }

        if(name == null || path == null){
            return null;
        }

        ResourceEntry resourceEntry = resourceEntries.get(name);
        if(resourceEntry != null){
            return resourceEntry;
        }

        Resource resource = null;
        long contentLength = -1;
        InputStream binaryStream = null;

        for(int i=0;i<repositories.length;i++){
            if(resourceEntry == null){
                String fullPath = repositories[i]+path;
                try {
                    Object lookupResource = resources.lookup(fullPath);
                    if(lookupResource instanceof Resource){
                        resource = (Resource)lookupResource;
                    }

                    resourceEntry = new ResourceEntry();

                    File realFileTemp = new File(files[i],path);
                    try {
                        // 将source和codeBase都指向 realFileTemp.getCanonicalFile().toURL()
                        resourceEntry.source = realFileTemp.getCanonicalFile().toURL();
                        resourceEntry.codeBase = resourceEntry.source;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ResourceAttributes resourceAttributes = (ResourceAttributes)resources.getAttributes(fullPath);
                    contentLength = resourceAttributes.getContentLength();

                    resourceEntry.lastModified = resourceAttributes.getLastModified();

                    if(resource != null){
                        binaryStream = resource.streamContent();
                        synchronized (allPermission){
                            long[] lastModifiedDateTemp = new long[lastModifiedDates.length+1];
                            System.arraycopy(lastModifiedDates,0,lastModifiedDateTemp,0,lastModifiedDates.length);
                            lastModifiedDateTemp[lastModifiedDates.length]=resourceEntry.lastModified;
                            lastModifiedDates = lastModifiedDateTemp;

                            String[] pathsTemp = new String[paths.length+1];
                            System.arraycopy(paths,0,pathsTemp,0,paths.length);
                            pathsTemp[paths.length]=fullPath;
                            paths = pathsTemp;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }else{
                break;
            }
        }

        if(resourceEntry == null && notFoundResources.containsKey(name)){
            return null;
        }

        JarEntry jarEntry = null;
        for(int i=0;i<jarFiles.length;i++){
            if(resourceEntry == null){
                jarEntry = jarFiles[i].getJarEntry(name);
                if(jarEntry != null){
                    resourceEntry = new ResourceEntry();
                    try {
                        resourceEntry.codeBase = jarRealFiles[i].getCanonicalFile().toURL();
                        resourceEntry.source = new URL("jar:"+resourceEntry.codeBase+"!/"+path);
                        contentLength = jarEntry.getSize();
                        resourceEntry.manifest = jarFiles[i].getManifest();
                        binaryStream = jarFiles[i].getInputStream(jarEntry);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                break;
            }
        }
        if(resourceEntry == null){
            synchronized (notFoundResources){
                notFoundResources.put(name,name);
            }
            return null;
        }

        if(binaryStream != null){
            byte[] binaryContent = new byte[(int)contentLength];
            int pos = 0;
            while(true){
                int n = binaryStream.read(binaryContent,pos,(int)(contentLength-pos));
                if(n <= 0){
                    break;
                }
                pos = pos + n;
            }
            binaryStream.close();

            resourceEntry.binaryContent = binaryContent;

            // Certificates  证明书；证券；凭照；(毕业)文凭 v.批准；鉴定
            if(jarEntry != null){
                resourceEntry.certificates = jarEntry.getCertificates();
            }
        }

        synchronized (resourceEntries){
            ResourceEntry entry2 = resourceEntries.get(name);
            if(entry2 == null){
                resourceEntries.put(name,resourceEntry);
            }else{
                resourceEntry = entry2;
            }
        }

        return resourceEntry;
    }


    @Override
    public void addRepository(String repository) {

    }

    @Override
    public String[] findRepositories() {
        return new String[0];
    }

    @Override
    public boolean modified() {
        return false;
    }

    @Override
    public void addLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return new LifecycleListener[0];
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {

    }

    @Override
    public void start() throws LifecycleException {

    }

    @Override
    public void stop() throws LifecycleException {

    }




    public WebappClassLoader8() {
        super(new URL[0]);
        this.parent = getParent();
        system = getSystemClassLoader();
        securityManager = System.getSecurityManager();
        if (securityManager != null) {
            refreshPolicy();
        }
    }


    /**
     * Construct a new ClassLoader with no defined repositories and no
     * parent ClassLoader.
     */
    public WebappClassLoader8(ClassLoader parent) {
        super(new URL[0], parent);
        this.parent = getParent();
        system = getSystemClassLoader();
        securityManager = System.getSecurityManager();
        if (securityManager != null) {
            refreshPolicy();
        }
    }

    /**
     * Refresh the system policy file, to pick up eventual changes.
     */
    protected void refreshPolicy() {
        try {
            Policy policy = Policy.getPolicy();
            policy.refresh();
        } catch (AccessControlException e) {
            e.printStackTrace();
        }
    }

}
