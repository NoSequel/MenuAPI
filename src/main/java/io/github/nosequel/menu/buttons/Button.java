package io.github.nosequel.menu.buttons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class Button {

    /**
     * Get the index of the button.
     *
     * @return the index slot
     */
    public abstract int getIndex();

    /**
     * Get the material of the button to
     * display in the menu.
     *
     * @return the type of the material
     */
    public abstract Material getMaterial();

    /**
     * Get the string to set the display name
     * of the item in the menu to.
     *
     * @return the display name
     */
    public abstract String getDisplayName();

    /**
     * Get the string array to set the lore
     * of the item in the menu to.
     *
     * @return the lore
     */
    public abstract String[] getLore();

    /**
     * Get the click action of the button.
     * <p>
     * This will be called whenever the
     * player clicks the button inside of
     * the inventory.
     *
     * @return the click action to call
     */
    public Consumer<InventoryClickEvent> getClickAction() {
        return event -> event.setCancelled(true);
    }

    /**
     * Convert the button into an {@link ItemStack}.
     *
     * @return the newly created item stack
     */
    public ItemStack toItemStack() {
        final ItemStack item = new ItemStack(this.getMaterial());
        final ItemMeta meta = item.getItemMeta();

        if(meta != null) {
            if (this.getDisplayName() != null) {
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.getDisplayName()));
            }

            if (this.getLore() != null) {
                meta.setLore(Arrays.stream(this.getLore())
                        .map(string -> ChatColor.translateAlternateColorCodes('&', string))
                        .collect(Collectors.toList())
                );
            }

            item.setItemMeta(meta);
        }

        return item;
    }
}