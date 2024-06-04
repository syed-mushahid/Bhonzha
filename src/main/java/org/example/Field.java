package org.example;
import java.util.ArrayList;
import java.util.List;

public class Field {
    private List<Card> beans;

    public Field() {
        this.beans = new ArrayList<>();
    }

    public void plant(Card card) {
        beans.add(card);
    }

    public void harvest() {
        beans.clear();
    }

    public List<Card> getBeans() {
        return beans;
    }

    @Override
    public String toString() {
        return "Field{" +
                "beans=" + beans +
                '}';
    }
}
