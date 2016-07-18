package de.tomsu.spring.restservice;

import de.tomsu.spring.model.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * The RESTful WebService Controller.
 * Created by Daniel on 17.07.2016.
 */
@RestController
public class GreetingController {
    private final static String TEMPLATE ="Hello, %s!";
    private final AtomicLong COUNTER = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(COUNTER.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
