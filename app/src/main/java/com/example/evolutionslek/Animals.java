package com.example.evolutionslek;

import java.lang.Math;

public class Animals {
    int mass;   //1-100
    int defense;//0-10 i början
    int maxHealth; //1-100
    int health; //0-100
    int horns;  //0-10 i början
    int attack; //calcuated
    boolean herbivore;
}

class Husmus extends Animals {
    public Husmus() {
        mass = 5;
        defense = 1;
        maxHealth = mass;
        health = maxHealth;
        horns = 1;
        attack = (int) Math.round(Math.sqrt(mass)*horns);
        herbivore = true;
    }
}
class Unicorn extends Animals {
    public Unicorn() {
        mass = 80;
        defense = 4;
        maxHealth = mass;
        health = maxHealth;
        horns = 3;
        attack = (int) Math.round(Math.sqrt(mass)*horns);
        herbivore = true;
    }
}
class Pedro extends Animals {
    public Pedro() {
        mass = 30;
        defense = 2;
        maxHealth = mass;
        health = maxHealth;
        horns = 4;
        attack = (int) Math.round(Math.sqrt(mass)*horns);
        herbivore = false;
    }
}
class Gruffalo extends Animals {
    public Gruffalo() {
        mass = 60;
        defense = 2;
        maxHealth = mass;
        health = maxHealth;
        horns = 4;
        attack = (int) Math.round(Math.sqrt(mass)*horns);
        herbivore = false;
    }
}