package pers.qianshifengyi.tomcat.exercise8.core;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by zhangshan on 17/5/26.
 */
public class SimplePipeline8 implements Pipeline,Contained,Lifecycle{

    private Container container;

    private Valve basic;

    private Valve[] valves = new Valve[0];

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
    }

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
            Valve[] tempValves = new Valve[valves.length+1];
            for(int i=0;i<valves.length;i++){
                tempValves[i] = valves[i];
            }
            tempValves[valves.length] = valve;
            valves = tempValves;
        }
    }

    @Override
    public Valve[] getValves() {
        synchronized (valves){
            return valves;
        }
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {

    }

    @Override
    public void removeValve(Valve valve) {
        synchronized (valves){
            if(valves.length < 1){
                return;
            }
            boolean equalsFlag = false;
            for(int i=0;i<valves.length;i++){
                if(valve.equals(valves[i])){
                    equalsFlag = true;
                }
            }
            if(equalsFlag == false){
                return;
            }

            Valve[] tempValves = new Valve[valves.length-1];
            int j=0;
            for(int i=0;i<valves.length;i++){
                if(!valve.equals(valves[i])) {
                    tempValves[j++] = valves[i];
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

    }

    @Override
    public void stop() throws LifecycleException {

    }
}
