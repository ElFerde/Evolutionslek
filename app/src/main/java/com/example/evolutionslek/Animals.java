package com.example.evolutionslek;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.Math;

public class Animals implements Parcelable {
    Animals(){}
    int mass;   //1-100
    int horns;  //0-10
    int speed;  //0-100
    int defense;//0-10
    int maxHealth; //1-100
    int health; //0-100
    int claws;  //0-10
    int attack; //calcuated
    int food;
    boolean herbivore;

    protected Animals(Parcel in) {
        mass = in.readInt();
        horns = in.readInt();
        speed = in.readInt();
        defense = in.readInt();
        maxHealth = in.readInt();
        health = in.readInt();
        claws = in.readInt();
        attack = in.readInt();
        food = in.readInt();
        herbivore = in.readByte() != 0;
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
        dest.writeInt(mass);
        dest.writeInt(horns);
        dest.writeInt(speed);
        dest.writeInt(defense);
        dest.writeInt(maxHealth);
        dest.writeInt(health);
        dest.writeInt(claws);
        dest.writeInt(attack);
        dest.writeInt(food);
        dest.writeByte((byte) (herbivore ? 1 : 0));
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
    }
}
