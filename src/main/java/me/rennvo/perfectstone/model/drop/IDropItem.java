package me.rennvo.perfectstone.model.drop;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface IDropItem {

    public Material getMaterial();

    public String getName();

    public double getChance();

    public double getExp(); // plugin exp

    public int getXp(); // minecraft exp

    public ItemStack build();

}
