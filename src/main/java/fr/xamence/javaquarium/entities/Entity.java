package fr.xamence.javaquarium.entities;

public abstract class Entity {

    private boolean alive;
    private int pv;
    private int age;

    public Entity() {
        this.alive = true;
        this.pv = 10;
        this.age = 0;

    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public abstract void getEaten();

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;

        this.setAlive(this.getPv() > 0 && this.alive);
    }

    public void turn() {
        this.age += 1;
        this.setAlive(this.age <= 20);
    }

    public int getAge() {
        return age;
    }
}
