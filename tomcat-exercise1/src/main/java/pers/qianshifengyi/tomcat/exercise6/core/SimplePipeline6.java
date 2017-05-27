package pers.qianshifengyi.tomcat.exercise6.core;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangshan on 17/5/22.
 */
public class SimplePipeline6 implements Pipeline,Lifecycle {

    private Valve basic;
    private List<Valve> valves = new ArrayList<Valve>();

    @Override
    public Valve getBasic() {
        return basic;
    }

    @Override
    public void setBasic(Valve valve) {
        this.basic = valve;
    }

    @Override
    public void addValve(Valve valve) {
        synchronized (valves){
            valves.add(valve);
        }
    }

    @Override
    public Valve[] getValves() {
        return (Valve[])valves.toArray();
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        (new SimplePipeline6ValveContext()).invokeNext(request,response);
    }

    @Override
    public void removeValve(Valve valve) {
        synchronized (valves){
            List<Valve> tempValves = new ArrayList<Valve>();
            for(Valve temp:valves){
                if(!temp.equals(valve)){
                    tempValves.add(valve);
                }
            }
            valves = tempValves;
        }

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
        System.out.println("SimplePipeline6 start");
    }

    @Override
    public void stop() throws LifecycleException {
        System.out.println("SimplePipeline6 stop");
    }

    class SimplePipeline6ValveContext implements ValveContext{

        private int stage = 0;

        @Override
        public String getInfo() {
            return "this is SimplePipeline6ValveContext";
        }

        @Override
        public void invokeNext(Request request, Response response) throws IOException, ServletException {
            System.out.println("stage:"+stage);
            int sub = stage;
            stage++;
            if(sub < valves.size()){
                // 多次进入开始位置,参见 ClientIPLoggerValve6 invoke后直接开始调用下一次,所以要stage先自增
                /**
                 * @Override
                public void invoke(Request request, Response response, ValveContext valveContext) throws IOException, ServletException {
                valveContext.invokeNext(request,response);
                ServletRequest sreq = request.getRequest() ;
                ServletResponse sres = response.getResponse() ;

                 */
                valves.get(sub).invoke(request,response,this);
            }else if(sub == valves.size()){
               basic.invoke(request,response,this);
            }else{
                System.out.println("---------- SimplePipeline6ValveContext ********  error sub:"+sub);
                return;
            }


        }
    }
}
