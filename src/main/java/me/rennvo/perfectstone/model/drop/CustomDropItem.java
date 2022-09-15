package me.rennvo.perfectstone.model.drop;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CustomDropItem implements IDropItem {

    private final String       name;
    private final Material     material;
    private final short        type;
    private final double       chance;
    private final double       exp;
    private final int          xp;
    private final List<String> lore;

    public CustomDropItem(String name, Material material, short type, double chance, double exp, int xp, List<String> lore) {
        this.name     = name;
        this.material = material;
        this.type     = type;
        this.chance   = chance;
        this.exp      = exp;
        this.xp       = xp;
        this.lore     = lore;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public short getType() {
        return type;
    }

    @Override
    public double getChance() {
        return chance;
    }

    @Override
    public double getExp() {
        return exp;
    }

    @Override
    public int getXp() {
        return xp;
    }

    @Override
    public List<String> getLore() {
        return lore;
    }

    @Override
    public ItemStack build(int levelOfFortune) {
        ItemStack itemStack = new ItemStack(material, 1, type); //TODO fortune
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
