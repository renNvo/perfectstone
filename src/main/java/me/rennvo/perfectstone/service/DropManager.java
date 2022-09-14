package me.rennvo.perfectstone.service;

import me.rennvo.perfectstone.model.drop.IDropItem;

import java.util.ArrayList;
import java.util.List;

public class DropManager {

    private final List<IDropItem> dropItems = new ArrayList<>();

    public List<IDropItem> getDropItems() {
        return dropItems;
    }
}
