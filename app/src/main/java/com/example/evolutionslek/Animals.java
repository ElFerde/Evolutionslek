package com.example.evolutionslek;

import java.lang.Math;

public class Animals {
    int mass;   //1-100
    int horns;  //0-10
    int speed;  //0-100
    int defense;//0-10
    int maxHealth; //1-100
    int health; //0-100
    int claws;  //0-10
    int attack; //calcuated
    boolean herbivore;
}

class Husmus extends Animals {
    public Husmus() {
        mass = 15;
        horns = 0;
        speed = 80;
        defense = 1;
        maxHealth = mass;
        health = maxHealth;
        claws = 1;
        attack = (int) Math.round(Math.sqrt(mass)*horns);
        herbivore = true;
    }
}
class Unicorn extends Animals {
    public Unicorn() {
        mass = 80;
        horns = 1;
        speed = 40;
        defense = 4;
        maxHealth = mass;
        health = maxHealth;
        claws = 0;
        attack = (int) Math.round(Math.sqrt(mass)*horns);
        herbivore = true;
    }
}
class Pedro extends Animals {
    public Pedro() {
        mass = 30;
        horns = 0;
        speed = 60;
        defense = 2;
        maxHealth = mass;
        health = maxHealth;
        claws = 7;
        attack = (int) Math.round(Math.sqrt(mass)*horns);
        herbivore = false;
    }
}
class Gruffalo extends Animals {
    public Gruffalo() {
        mass = 60;
        horns = 2;
        speed = 15;
        defense = 2;
        maxHealth = mass;
        health = maxHealth;
        claws = 9;
        attack = (int) Math.round(Math.sqrt(mass)*horns);
        herbivore = false;
    }
}