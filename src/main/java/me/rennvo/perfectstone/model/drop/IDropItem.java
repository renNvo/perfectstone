package me.rennvo.perfectstone.model.drop;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface IDropItem {

    public String getName();

    public Material getMaterial();

    public short getType();

    public double getChance();

    public double getExp(); // plugin exp

    public int getXp(); // minecraft exp

    public List<String> getLore();

    public ItemStack build(int levelOfFortune);

}
