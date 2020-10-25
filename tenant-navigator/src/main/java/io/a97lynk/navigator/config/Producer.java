package io.a97lynk.navigator.config;//package io.a97lynk.courseservice.config;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class Producer {
//
//    private final RabbitTemplate rabbitTemplate;
//
//    @Value("${course-service.rabbitmq.queue}")
//    String queueName;
//
//    @Value("${course-service.rabbitmq.exchange}")
//    String exchange;
//
//    @Value("${course-service.rabbitmq.routing-key}")
//    private String routingKey;
//
//    public Producer(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }
//
//    public void send(Object data) {
//        rabbitTemplate.convertAndSend(exchange, routingKey, data);
//    }
//
//}
