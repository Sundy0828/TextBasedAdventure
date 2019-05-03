package com.SundyStudios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        World[][][] worldMap = generateWorld(3,3,3);
        Scanner in = new Scanner(System.in);
        long startTime= System.currentTimeMillis();

        // adventure details
        System.out.println("What is your name Ashen One?");
        String name = in.nextLine();
        System.out.println("Hello, " + name + ", are you ready to begin your adventure? (Y/N)");
        String answer = in.nextLine();
        boolean ready = false;
        if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")) {
            ready = true;
        }
        while (!ready) {
            System.out.println("Wait...I'm confused, why are you even here then?\nWell I'll ask again just to be sure.");
            System.out.println(name + ", are you now ready to begin your adventure? (Y/N)");
            answer = in.nextLine();
            if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")) {
                ready = true;
            }
        }
        System.out.println("Would you like to hear the instructions of how begin your adventure? (Y/N)");
        answer = in.nextLine();
        if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")) {
            System.out.println("Long ago, the four nations lived together in harmony.\nThen, everything changed when the Fire Nation attacked.\nOnly the " + name + ", master of adventure, could stop them,\nbut when the world needed him most, he vanished.\nA hundred years passed and you woke up in a world with\nno idea of how to escape.\n");
            System.out.println("The only thing you know is that the controls to this\ngame include using the commands north, south, east,\nwest, up, down, look, take, and inventory.\n");
        }

        // start adventure
        int x = 0, y = 0, z = 0;
        boolean finished = false;
        List inventory = new ArrayList();
        System.out.println(roomDescription(worldMap[x][y][z]));
        while (!finished) {
            System.out.println("Where do you want to go?");
            String direction = in.nextLine();
            switch(direction){
                case "north":
                    if (validMove(worldMap, x, y, z + 1)) {
                        z += 1;
                        if (!worldMap[x][y][z].visited) {
                            worldMap[x][y][z].setVisited();
                            System.out.println(roomDescription(worldMap[x][y][z]));
                        }
                    }else {
                        System.out.println("You ran into a sturdy wall.");
                    }
                    break;
                case "south":
                    if (validMove(worldMap, x, y, z - 1)) {
                        z -= 1;
                        if (!worldMap[x][y][z].visited) {
                            worldMap[x][y][z].setVisited();
                            System.out.println(roomDescription(worldMap[x][y][z]));
                        }
                    }else {
                        System.out.println("You ran into a sturdy wall.");
                    }
                    break;
                case "east":
                    if (validMove(worldMap, x, y + 1, z)) {
                        y += 1;
                        if (!worldMap[x][y][z].visited) {
                            worldMap[x][y][z].setVisited();
                            System.out.println(roomDescription(worldMap[x][y][z]));
                        }
                    }else {
                        System.out.println("You ran into a sturdy wall.");
                    }
                    break;
                case "west":
                    if (validMove(worldMap, x, y - 1, z)) {
                        y -= 1;
                        if (!worldMap[x][y][z].visited) {
                            worldMap[x][y][z].setVisited();
                            System.out.println(roomDescription(worldMap[x][y][z]));
                        }
                    }else {
                        System.out.println("You ran into a sturdy wall.");
                    }
                    break;
                case "down":
                    if (validMove(worldMap, x + 1, y, z)) {
                        if (worldMap[x+1][y][z].up) {
                            x += 1;
                            System.out.println("You traverse down the ladder.");
                            if (!worldMap[x][y][z].visited) {
                                worldMap[x][y][z].setVisited();
                                System.out.println(roomDescription(worldMap[x][y][z]));
                            }
                        }else {
                            System.out.println("You look down and finally realize how stupid you are since there is no way to go up.");
                        }
                    }else {
                        System.out.println("You heard a little voice in your head telling you where to go, but you didn't understand it.");
                    }
                    break;
                case "up":
                    if (validMove(worldMap, x - 1, y, z)) {
                        if (worldMap[x-1][y][z].down) {
                            x -= 1;
                            System.out.println("You traverse up the ladder.");
                            if (!worldMap[x][y][z].visited) {
                                worldMap[x][y][z].setVisited();
                                System.out.println(roomDescription(worldMap[x][y][z]));
                            }
                        }else {
                            System.out.println("You look up and finally realize how stupid you are since there is no way to go up.");
                        }
                    }else {
                        System.out.println("You heard a little voice in your head telling you where to go, but you didn't understand it.");
                    }
                    break;
                case "look":
                    System.out.println(roomDescription(worldMap[x][y][z]));
                    break;
                case "take":
                    if (worldMap[x][y][z].items.size() > 0) {
                        System.out.println("You have taken " + String.join(", ", worldMap[x][y][z].items));
                        for (String item:worldMap[x][y][z].items) {
                            inventory.add(item);
                        }
                        worldMap[x][y][z].clearItems();
                    }else {
                        System.out.println("You can't put nothing into a bag idiot.");
                    }
                    break;
                case "inventory":
                    if (inventory.size() > 0) {
                        for (Object item : inventory) {
                            System.out.println(item);
                        }
                    }else {
                        System.out.println("Your bag is empty.");
                    }
                    break;
                default:
                    System.out.println("You heard a little voice in your head telling you where to go, but you didn't understand it.");
                    break;
            }
            if (worldMap[x][y][z].description.equals("finish")) {
                if (inventory.size() == 3) {
                    finished = true;
                }
            }
        }
        long elapsedTime = System.currentTimeMillis()-startTime;
        long seconds = elapsedTime/1000;
        long secondsDisplay = seconds%60;
        long minutes = seconds/60;
        System.out.println("GG no re, fam " + minutes+" minutes and " + secondsDisplay+" seconds");

    }
    public static boolean validMove(World[][][] worldMap, int x, int y, int z) {
        try {
            World world = worldMap[x][y][z];
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    public static String roomDescription(World world){
        String description;
        switch(world.description.toLowerCase()){
            case "loud":
                description =  "You walk into a room with a humming so loud it is hard to concentrate.";
                break;
            case "room":
                description =  "Just a plain room... Better move on.";
                break;
            case "small":
                description =  "A room with low ceilings and small furniture.\nPerhaps it was made for small people?";
                break;
            case "large":
                description =  "This is a fairly large room with large furniture.\nWhen did you climb the beanstalk?";
                break;
            case "finish":
                description =  "There is an stone slab with inscriptions at the center of the large room\nUpon further inspection the slab reads\n\"Bring the three objects to this room and you shall escape the dungeon\"";
                break;
            default:
                description =  "Uhhhhhhhhh... did it work yet mom?";
                break;
        }
        // get items
        String itemList = "";
        if (world.items.size() > 0) {
            itemList += "\n" + String.join(", ", world.items);
            if (world.items.size() > 1) {
                itemList += " are seen on the ground ";
            }else {
                itemList += " is seen on the ground ";
            }
        }
        // get ladders
        String ladders = "";
        if (world.up && world.down) {
            ladders += "\n" + "A ladder leading up, and a ladder leading down are both seen in the room.";
        }else if (world.up) {
            ladders += "\n" + "A ladder leading up is seen in the room.";
        }else if (world.down) {
            ladders += "\n" + "A ladder leading down is seen in the room.";
        }
        return description  + itemList + ladders;
    }
    public static World[][][] generateWorld(int X, int Y, int Z) {
        String[] roomTypes = {"loud", "room", "small", "large"};
        String[] items = {"orb", "box", "triangle"};
        World[][][] worldMap = new World[X][Y][Z];
        // create map
        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                for (int z = 0; z < Z; z++) {
                    int randomRoom = (int)(Math.random() * roomTypes.length);
                    worldMap[x][y][z] = new World(roomTypes[randomRoom], new ArrayList(), false, false, false);
                }
            }
        }

        int x = (int)(Math.random() * X);
        int y = (int)(Math.random() * Y);
        int z = (int)(Math.random() * Z);
        // set items
        for (String item:items) {
            worldMap[x][y][z].addItem(item);
            x = (int)(Math.random() * X);
            y = (int)(Math.random() * Y);
            z = (int)(Math.random() * Z);
        }
        // set ladders
        for (int i = 0; i < X; i++) {

            if (i > 0) {
                y = (int)(Math.random() * Y);
                z = (int)(Math.random() * Z);
                worldMap[i - 1][y][z].setDown();
                worldMap[i][y][z].setUp();
            }
        }
        // set final destination
        x = (int)(Math.random() * X);
        y = (int)(Math.random() * Y);
        z = (int)(Math.random() * Z);
        worldMap[x][y][z].setFinish();

        /*System.out.println("MAP");
        printMap(worldMap);
        System.out.println("UP");
        printUp(worldMap);
        System.out.println("DOWN");
        printDown(worldMap);*/

        return  worldMap;
    }
    public static void printMap(World[][][] world) {
        for (int x = 0; x < world.length; x++) {
            for (int y = 0; y < world[x].length; y++) {
                for (int z = 0; z < world[x][y].length; z++) {
                    System.out.print(world[x][y][z].description + " ");
                }
                System.out.println();
            }
            System.out.println("-------------------------");
        }
    }
    public static void printUp(World[][][] world) {
        for (int x = 0; x < world.length; x++) {
            for (int y = 0; y < world[x].length; y++) {
                for (int z = 0; z < world[x][y].length; z++) {
                    System.out.print(world[x][y][z].up + " ");
                }
                System.out.println();
            }
            System.out.println("-------------------------");
        }
    }
    public static void printDown(World[][][] world) {
        for (int x = 0; x < world.length; x++) {
            for (int y = 0; y < world[x].length; y++) {
                for (int z = 0; z < world[x][y].length; z++) {
                    System.out.print(world[x][y][z].down + " ");
                }
                System.out.println();
            }
            System.out.println("-------------------------");
        }
    }
}
