package com.shanvin.project;

import org.cometd.client.BayeuxClient;
import org.cometd.client.transport.ClientTransport;
import org.cometd.client.transport.LongPollingTransport;
import org.cometd.websocket.client.WebSocketTransport;
import org.eclipse.jetty.client.HttpClient;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

public class ClientTest {

    public static void main(String[] args) {

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        ClientTransport wsTransport = new WebSocketTransport(null, null, container);
        HttpClient httpClient = new HttpClient();
        httpClient.addBean(container, true);
        try {
            httpClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClientTransport lpTransport = new LongPollingTransport(null, httpClient);
        BayeuxClient client = new BayeuxClient("http://localhost:9011/cometd", wsTransport, lpTransport);
        client.handshake(message -> {
            if (message.isSuccessful()) {
                System.out.println("handshake successful");
            } else {
                System.out.println("handshake failed");
            }
        });

    }

} 
