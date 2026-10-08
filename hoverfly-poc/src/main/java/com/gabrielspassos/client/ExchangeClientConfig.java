package com.gabrielspassos.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.http.HttpClient;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;

@Configuration
public class ExchangeClientConfig {

    @Bean
    RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

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


    @Bean
    RestClient exchangeRestClient(RestClient.Builder builder,
                                  @Value("${exchange.api.url}") String url,
                                  SSLContext sslContext) {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .sslContext(sslContext)
                .build();

        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);
        requestFactory.setReadTimeout(Duration.ofSeconds(5));

        return builder
                .baseUrl(url)
                .requestFactory(requestFactory)
                .build();
    }

}