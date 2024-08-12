package com.siddhantbhardwaj.productSoapConsumeService.config;

import com.siddhantbhardwaj.productSoapConsumeService.wsdl.ProductPort;
import com.siddhantbhardwaj.productSoapConsumeService.wsdl.ProductPortService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class SoapClientConfiguration {

    private static final String WSDL_URL = "http://localhost:8081/ws/products.wsdl";
    private static final String SOAP_ENDPOINT_URL = "http://localhost:8081/ws/products";
    private static final QName SERVICE_QNAME = new QName("http://www.siddhantbhardwaj.com/productSoapService/gen", "productPortService");
    private static final String PORT_NAME = "productPortSoap11";

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri(SOAP_ENDPOINT_URL);
        webServiceTemplate.setMessageSender(httpComponentsMessageSender());
        return webServiceTemplate;
    }

    @Bean
    public WebServiceMessageSender httpComponentsMessageSender() {
        HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();
        // Customize the sender if needed (e.g., set timeouts)
        return messageSender;
    }

    @Bean
    public ProductPortService productPortService() throws MalformedURLException {
        URL wsdlUrl = new URL(WSDL_URL);
        return new ProductPortService(wsdlUrl, SERVICE_QNAME);
    }

    @Bean
    public ProductPort productPort(ProductPortService productPortService) {
        return productPortService.getProductPortSoap11();
    }
}
