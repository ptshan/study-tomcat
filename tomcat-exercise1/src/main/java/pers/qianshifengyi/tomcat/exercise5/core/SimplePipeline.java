package pers.qianshifengyi.tomcat.exercise5.core;

import org.apache.catalina.*;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by zhangshan on 17/5/16.
 */
public class SimplePipeline implements Pipeline {

    private Valve basicValve;

    private Valve[] valves = new Valve[0];

    private Container container;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public Valve getBasic() {
        return basicValve;
    }

    @Override
    public void setBasic(Valve valve) {
        this.basicValve = valve;
        if(valve instanceof Contained){
            ((Contained)valve).setContainer(container);
        }
    }

    @Override
    public void addValve(Valve valve) {
        if(valve instanceof Contained){
            ((Contained)valve).setContainer(container);
        }
        synchronized (valves){
            Valve[] tempValveArr = new Valve[valves.length+1];
            System.arraycopy(valves,0,tempValveArr,0,valves.length);
            tempValveArr[valves.length] = valve;
            valves = tempValveArr;
        }

    }

    @Override
    public Valve[] getValves() {
        return valves;
    }

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        new SimplePipelineValveContext().invokeNext(request,response);
    }

    @Override
    public void removeValve(Valve valve) {
        if(valve != null){
            synchronized (valves){
                Valve[] tempValves = new Valve[valves.length-1];
                for(int i=0;i<valves.length;i++){
                    if(!valves[i].equals(valve)) {
                        tempValves[i] = valves[i];
                    }
                }
                valves = tempValves;
            }
        }
    }

    class SimplePipelineValveContext implements ValveContext{

        int stage = 0;

        @Override
        public String getInfo() {
            return "this is SimplePipelineValveContext";
        }

        @Override
        public void invokeNext(Request request, Response response) throws IOException, ServletException {
            int sub = stage;
            stage++;

            synchronized (valves){
                if(sub < valves.length){
                    valves[sub].invoke(request,response,this);
                }else if(sub == valves.length){
                    basicValve.invoke(request,response,this);
                }else{
                    System.out.println("    SimplePipelineValveContext 挂了    ");
                }
            }
        }

    }



}
