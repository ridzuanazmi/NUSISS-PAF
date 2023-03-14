package nusiss.paf.day23workshop.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import nusiss.paf.day23workshop.model.Order;

@Service
public class OrderRestTemplateService {
    
    RestTemplate restTemplate = new RestTemplate();

    private static final String GET_ORDERBYID_ENDPOINT_URL = "http://localhost:8080/api/orders/{id}";

    public List<Order> findOrderById(Integer id) {
        
        ResponseEntity<List<Order>> orderList = restTemplate.exchange(GET_ORDERBYID_ENDPOINT_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {
        }, id);

        return orderList.getBody();
    }
}
