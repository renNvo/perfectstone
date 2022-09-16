package me.rennvo.perfectstone.model.drop;

import me.rennvo.perfectstone.model.drop.common.Height;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CustomDropItem extends DropItemImpl {

    private final List<String> lore;

    public CustomDropItem(String name, Material material, short type, double chance, double exp, int xp, Height height, List<String> lore) {
        super(name, material, type, chance, exp, xp, height);
        this.lore = lore;
    }

    @Override
    public List<String> getLore() {
        return lore;
    }

    @Override
    public ItemStack build(int levelOfFortune) {
        ItemStack itemStack = new ItemStack(this.getMaterial(), 1, this.getType()); //TODO fortune
        ItemMeta  itemMeta  = itemStack.getItemMeta();

        itemMeta.setDisplayName(this.getName());
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }
}
