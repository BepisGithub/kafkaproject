package com.store.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.models.OrderEvent;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/order")
public class OrderAPI {

    @Autowired
    private KafkaTemplate<Integer, OrderEvent> template;

    @GetMapping("/place")
    public String placeOrder(){
        int orderNumber = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        this.template.send("orders", orderNumber, new OrderEvent("order", "success", orderNumber));
        return "order " + orderNumber + " placed";
    }
}
