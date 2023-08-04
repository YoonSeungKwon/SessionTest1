package yoon.test.sessionTest1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    @GetMapping({"/index", "/"})
    public ResponseEntity<String> mainPage(){
        return ResponseEntity.ok("Hello World!");
    }

}
