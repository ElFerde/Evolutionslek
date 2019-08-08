package com.example.evolutionslek;

import android.os.Parcel;
import android.os.Parcelable;

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
        maxHealth = 60;
        health = 15;
        claws = 1;
        attack = 1;
        food = mass/2;
        herbivore = true;
        species = "Husmus";
    }
}
class Unicorn extends Animals {
    public Unicorn() {
        mass = 80;
        horns = 1;
        speed = 35;
        defense = 15;
        maxHealth = 80;
        health = maxHealth;
        claws = 0;
        attack = 4;
        food = mass/2;
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
        maxHealth = 30;
        health = maxHealth;
        claws = 7;
        attack = 15;
        food = mass/2;
        herbivore = false;
        species = "Pedro";
    }
}
class Gruffalo extends Animals {
    public Gruffalo() {
        mass = 60;
        horns = 2;
        speed = 25;
        defense = 2;
        maxHealth = 60;
        health = maxHealth;
        claws = 9;
        attack = 20;
        food = mass/2;
        herbivore = false;
        species = "Gruffalo";
    }
}
class Dead extends Animals {
    public Dead() {
        mass = 0;
        horns = 0;
        speed = 0;
        defense = 0;
        maxHealth = 0;
        health = maxHealth;
        claws = 0;
        attack = 0;
        food = mass/2;
        herbivore = false;
        species = "None";
    }
}

