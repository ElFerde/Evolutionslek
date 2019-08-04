package com.example.evolutionslek;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.Math;

public class Animals implements Parcelable {
    Animals(){}
    double mass, horns, speed, defense, maxHealth, health, claws, attack, food;
    boolean herbivore;
    String species;

    protected Animals(Parcel in) {
        mass = in.readDouble();
        horns = in.readDouble();
        speed = in.readDouble();
        defense = in.readDouble();
        maxHealth = in.readDouble();
        health = in.readDouble();
        claws = in.readDouble();
        attack = in.readDouble();
        food = in.readDouble();
        herbivore = in.readByte() != 0;
        species = in.readString();
    }

    public static final Creator<Animals> CREATOR = new Creator<Animals>() {
        @Override
        public Animals createFromParcel(Parcel in) {
            return new Animals(in);
        }

        @Override
        public Animals[] newArray(int size) {
            return new Animals[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(mass);
        dest.writeDouble(horns);
        dest.writeDouble(speed);
        dest.writeDouble(defense);
        dest.writeDouble(maxHealth);
        dest.writeDouble(health);
        dest.writeDouble(claws);
        dest.writeDouble(attack);
        dest.writeDouble(food);
        dest.writeByte((byte) (herbivore ? 1 : 0));
        dest.writeString(species);
    }
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
        food = 0;
        herbivore = true;
        species = "Husmus";
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
        food = 0;
        herbivore = true;
        species = "Unicorn";
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
        food = 0;
        herbivore = false;
        species = "Pedro";
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
        food = 0;
        herbivore = false;
        species = "Gruffalo";
    }
}
