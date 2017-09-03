package pers.qianshifengyi.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mountain on 2017/8/30.
 */
@Component
@ConfigurationProperties(prefix="my")
public class ZSConfig {

    private List<String> servers = new ArrayList<String>();

    public List<String> getServers() {
        return this.servers;
    }
}
