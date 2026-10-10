package com.gabrielspassos.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;

@Configuration
public class ClientConfig {

    @Bean
    SSLContext sslContext() throws Exception {

        TrustManager[] trustAll = {
                new X509TrustManager() {

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }

                    @Override
                    public void checkClientTrusted(
                            X509Certificate[] chain,
                            String authType) {
                    }

                    @Override
                    public void checkServerTrusted(
                            X509Certificate[] chain,
                            String authType) {
                    }
                }
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");

        sslContext.init(null, trustAll, new SecureRandom());

        return sslContext;
    }

    @Bean("exchangeRestClientBuilder")
    RestClient.Builder exchangeRestClientBuilder(SSLContext sslContext) {
        return createBuilder(sslContext, 8500);
    }

    @Bean("notificationRestClientBuilder")
    RestClient.Builder notificationRestClientBuilder(SSLContext sslContext) {
        return createBuilder(sslContext, 8501);
    }

    private RestClient.Builder createBuilder(SSLContext sslContext, int proxyPort) {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .sslContext(sslContext)
                .proxy(ProxySelector.of(new InetSocketAddress("localhost", proxyPort)))
                .build();

        JdkClientHttpRequestFactory requestFactory =
                new JdkClientHttpRequestFactory(httpClient);

        requestFactory.setReadTimeout(Duration.ofSeconds(5));

        return RestClient.builder().requestFactory(requestFactory);
    }

}