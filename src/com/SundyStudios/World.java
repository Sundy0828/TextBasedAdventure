package com.SundyStudios;

import java.util.ArrayList;
import java.util.List;

public class World {
    String description;
    List<String> items;
    boolean visited;
    boolean up;
    boolean down;

    public World(String Description, List<String> Items, boolean Visited, boolean Up, boolean Down) {
        description = Description;
        items = Items;
        visited = Visited;
        up = Up;
        down = Down;
    }
    public void addItem(String Item) {
        items.add(Item);
    }
    public void setUp() {
        up = true;
    }
    public void setDown() {
        down = true;
    }
    public void setFinish() {
        description = "finish";
    }
    public void setVisited() {
        visited = true;
    }
    public void clearItems() {
        items = new ArrayList();
    }
}
