package com.shanvin.project.service;

import com.shanvin.project.config.CometDConfig;
import org.cometd.annotation.AnnotationCometDServlet;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.oort.OortStaticConfigServlet;
import org.cometd.oort.SetiServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import static org.cometd.oort.OortConfigServlet.*;
import static org.cometd.oort.OortStaticConfigServlet.OORT_CLOUD_PARAM;
import static org.cometd.server.AbstractServerTransport.MAX_INTERVAL_OPTION;
import static org.cometd.server.AbstractServerTransport.TIMEOUT_OPTION;
import static org.cometd.server.BayeuxServerImpl.BROADCAST_TO_PUBLISHER_OPTION;
import static org.cometd.websocket.server.common.AbstractWebSocketTransport.COMETD_URL_MAPPING_OPTION;

@Component
public class CometDInitiator {

    @Autowired
    private CometDConfig cometDConfig;
    @Autowired
    private ServletContext servletContext;

    public void start(ServletContext servletContext) {
        ServletRegistration.Dynamic cometDServlet = servletContext.addServlet("cometd", AnnotationCometDServlet.class);
        cometDServlet.addMapping(cometDConfig.getUrl());
        cometDServlet.setInitParameter(COMETD_URL_MAPPING_OPTION, cometDConfig.getUrl());
        cometDServlet.setInitParameter(TIMEOUT_OPTION, cometDConfig.getBayeux().getTimeout());
        cometDServlet.setInitParameter(MAX_INTERVAL_OPTION, cometDConfig.getBayeux().getMaxInterval());
        cometDServlet.setInitParameter(BROADCAST_TO_PUBLISHER_OPTION, cometDConfig.getBayeux().getBroadcastToPublisher());
        cometDServlet.setInitParameter("services", MonitorService.class.getName());
        cometDServlet.setAsyncSupported(true);
        cometDServlet.setLoadOnStartup(1);

        ServletRegistration.Dynamic oortServlet = servletContext.addServlet("oort", OortStaticConfigServlet.class);
        oortServlet.setInitParameter(OORT_URL_PARAM, cometDConfig.getOort().getUrl());
        oortServlet.setInitParameter(OORT_SECRET_PARAM, cometDConfig.getOort().getSecret());
        oortServlet.setInitParameter(OORT_CLOUD_PARAM, cometDConfig.getOort().getCloud());
        oortServlet.setLoadOnStartup(2);

        ServletRegistration.Dynamic setiServlet = servletContext.addServlet("seti", SetiServlet.class);
        setiServlet.setLoadOnStartup(3);
    }

    public void init() {
        BayeuxServer bayeuxServer = (BayeuxServer) servletContext.getAttribute(BayeuxServer.ATTRIBUTE);
        bayeuxServer.setSecurityPolicy(new SecurityService());
    }

}
