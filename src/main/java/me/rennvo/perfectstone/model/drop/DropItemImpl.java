package me.rennvo.perfectstone.model.drop;

import me.rennvo.perfectstone.model.drop.common.Height;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DropItemImpl implements IDropItem {

    private final String   name;
    private final Material material;
    private final short    type;
    private final double   chance;
    private final double   exp;
    private final int      xp;
    private final Height   height;

    public DropItemImpl(String name, Material material, short type, double chance, double exp, int xp, Height height) {
        this.name     = name;
        this.material = material;
        this.type     = type;
        this.chance   = chance;
        this.exp      = exp;
        this.xp       = xp;
        this.height   = height;
    }

    @Override
    public Height getHeight() {
        return height;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public ItemStack build(int levelOfFortune) {
        return new ItemStack(this.material, 1, this.type); // TODO
    }
}
