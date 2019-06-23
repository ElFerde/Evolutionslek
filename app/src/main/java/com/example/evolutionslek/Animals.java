package com.example.evolutionslek;

public class Animals {
    int health; //0-100
    int attack; //0-100
    int mass;   //1-100
    boolean herbivore;
}

class Pedros extends Animals {
    public Pedros() {
        health = 60;
        attack = 60;
        mass = 50;
        herbivore = false;
    }
}