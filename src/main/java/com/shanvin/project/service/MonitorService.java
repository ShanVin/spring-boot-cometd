package com.shanvin.project.service;

import org.cometd.annotation.Listener;
import org.cometd.annotation.Service;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MonitorService {

    private static final Logger logger = LoggerFactory.getLogger("ApplicationLogger");

    @Listener("/**")
    public void monitor(ServerSession session, ServerMessage message) {
        logger.info("Session: {}, Message{}", session, message);
    }

}
