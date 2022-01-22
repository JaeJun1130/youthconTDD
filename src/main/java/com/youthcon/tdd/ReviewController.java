package com.youthcon.tdd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
    @GetMapping("/reviews/{id}")
    public Object getByid(@PathVariable Long id){
        return null;
    }
}
