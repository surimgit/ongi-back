// package com.ongi.ongi_back.config;

// import org.apache.catalina.connector.Connector;
// import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
// import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// // Toss Payments 실행을 위한 Https 443 포트 추가
// @Configuration
// public class AdditionalPortConfig {

//     @Bean
//     public ServletWebServerFactory servletContainer() {
//         TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//         factory.addAdditionalTomcatConnectors(createStandardConnector());
//         return factory;
//     }

//     private Connector createStandardConnector() {
//         Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//         connector.setPort(443); 
//         return connector;
//     }
// }
