package com.shanvin.project.service;

import org.cometd.annotation.Service;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.SecurityPolicy;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;

@Service
public class SecurityService implements SecurityPolicy {

    @Override
    public boolean canHandshake(BayeuxServer server, ServerSession session, ServerMessage message) {
        return true;
    }

}
