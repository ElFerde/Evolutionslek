package com.example.evolutionslek;

import java.lang.Math();

public class Animals {
    int mass;   //1-100
    int defense;//0-10 i början
    int maxHealth; //1-100
    int health; //0-100
    int horns;  //0-10 i början
    int attack; //0-100
    boolean herbivore;
}

class Pedro extends Animals {
    public Pedro() {
        mass = 50;
        defense = 2;
        maxHealth = mass;
        health = maxHealth;
        horns = 4;
        attack = (int) Math.round(Math.sqrt(mass)*horns);
        herbivore = false;
    }
}
