package io.github.nosequel.menu.buttons;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Getter
public class Button implements Cloneable {

    private int index;
    private final Material material;

    private String displayName;
    private String[] lore;

    private Consumer<InventoryClickEvent> clickAction = event -> event.setCancelled(true);

    private int amount = 1;
    private byte data = 0;

    /**
     * @param index    the display slot of the button
     * @param material the icon of the button
     */
    public Button(int index, Material material) {
        this.material = material;
        this.index = index;
    }

    @Override
    public Button clone() {
        return new Button(this.index, this.material)
                .setDisplayName(this.getDisplayName())
                .setAmount(this.getAmount())
                .setClickAction(this.getClickAction())
                .setLore(this.getLore())
                .setData(this.getData());
    }

    /**
     * Set the index of the button in the menu.
     *
     * @param index the index to set it to
     * @return the current button instance
     */
    public Button setIndex(int index) {
        this.index = index;
        return this;
    }

    /**
     * Set the display name of the button in the menu.
     *
     * @param displayName the display name to set it to
     * @return the current button instance
     */
    public Button setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Set the lore of the button in the menu.
     *
     * @param lore the lore to set it to
     * @return the current button instance
     */
    public Button setLore(String[] lore) {
        this.lore = lore;
        return this;
    }

    /**
     * Set the click action of the button in the menu.
     *
     * @param clickAction the click action to set it to
     * @return the current button instance
     */
    public Button setClickAction(Consumer<InventoryClickEvent> clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    /**
     * Set the amount of the item stack of the button in the menu.
     *
     * @param amount the amount to set it to
     * @return the current button instance
     */
    public Button setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Set the item stack data of the button in the menu.
     *
     * @param data the itemstack data to set it to
     * @return the current button instance
     */
    public Button setData(byte data) {
        this.data = data;
        return this;
    }

    /**
     * Convert the button into an {@link ItemStack}.
     *
     * @return the newly created item stack
     */
    public ItemStack toItemStack() {
        final ItemStack item = new ItemStack(this.getMaterial(), this.getAmount(), this.getData());
        final ItemMeta meta = item.getItemMeta();

        if (meta != null) {
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