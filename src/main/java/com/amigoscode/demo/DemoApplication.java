package com.amigoscode.demo;

import com.amigoscode.demo.advertisement.AdvertisementController;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {
    public static String token;
    public static final String userName = "zjwlk123";
    public static final String password = "123456";
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Scheduled(fixedRate = 300000)
    public static void fetchESLToken() {
        final String uri = "http://esl.ylwlesl.com:9191/V1/Login";

        RestTemplate restTemplate = new RestTemplate();

        String reqBody = String.format("{\n" +
                "    \"username\": \"%s\",\n" +
                "    \"password\": \"%s\"\n" +
                "}", userName, password);
        String result = restTemplate.postForObject(uri, reqBody, String.class);

        // convert your result into json

        JSONObject jsonResponse;
        try {
            jsonResponse = new JSONObject(result);
            String token = jsonResponse.getJSONObject("body").getString("token");
            DemoApplication.token = "Bearer " + token;
            System.out.println(token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //extract a value "name" from your json data:

    }


//    @Bean
//    public TomcatServletWebServerFactory servletContainer() { //springboot2 新变化
//
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//
//            @Override
//            protected void postProcessContext(Context context) {
//
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
//        return tomcat;
//    }
//
//    private Connector initiateHttpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        connector.setPort(8080);
//        connector.setSecure(false);
//        connector.setRedirectPort(8443);
//        return connector;
//    }
}
