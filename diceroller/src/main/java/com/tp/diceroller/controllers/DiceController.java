package com.tp.diceroller.controllers;

import com.tp.diceroller.services.RNG;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiceController {
    //our first controller

    //most common request is GET
    //  when you point your browser to a web page you make a GET request for that page

    // handler for our GET request
    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "<h1>Hello World!</h1>";
    }

    @GetMapping("/d6")
    public int sixSidedDie() {
        return RNG.rollDice(6);
    }

    @GetMapping("/d20")
    public int twentySidedDie() {
        return RNG.rollDice(20);
    }

    @GetMapping("/dn")
    public int nSidedDie(Integer n) {
        return RNG.rollDice(n);
    }

    @GetMapping("/dn/{n}")
    public int nSidedDiePathVariable(@PathVariable Integer n) {
        return RNG.rollDice(n);
    }
}
