package com.example.a71p;

import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private static List<Item> itemList = new ArrayList<>();

    public static void addItem(Item item) {
        itemList.add(item);
    }

    public static List<Item> getItems() {
        return itemList;
    }
}
