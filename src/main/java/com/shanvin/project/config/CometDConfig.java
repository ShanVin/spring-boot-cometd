package com.shanvin.project.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("cometd")
public class CometDConfig {

    private String url;
    private Bayeux bayeux;
    private Oort oort;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bayeux getBayeux() {
        return bayeux;
    }

    public void setBayeux(Bayeux bayeux) {
        this.bayeux = bayeux;
    }

    public Oort getOort() {
        return oort;
    }

    public void setOort(Oort oort) {
        this.oort = oort;
    }

    public static class Bayeux {
        private String timeout;
        private String maxInterval;
        private String broadcastToPublisher;

        public String getTimeout() {
            return timeout;
        }

        public void setTimeout(String timeout) {
            this.timeout = timeout;
        }

        public String getMaxInterval() {
            return maxInterval;
        }

        public void setMaxInterval(String maxInterval) {
            this.maxInterval = maxInterval;
        }

        public String getBroadcastToPublisher() {
            return broadcastToPublisher;
        }

        public void setBroadcastToPublisher(String broadcastToPublisher) {
            this.broadcastToPublisher = broadcastToPublisher;
        }
    }

    public static class Oort {
        private String url;
        private String secret;
        private String cloud;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }
    }

}
