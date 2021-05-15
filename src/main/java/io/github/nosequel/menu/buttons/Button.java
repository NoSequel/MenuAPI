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

    private final Material material;
    private final ItemMeta meta;

    private String displayName;
    private String[] lore;

    private Consumer<InventoryClickEvent> clickAction = event -> event.setCancelled(true);

    private int amount;
    private byte data;

    /**
     * @param material the icon of the button
     */
    public Button(Material material) {
        this(new ItemStack(material));
    }

    /**
     * Make a new {@link Button} object from an {@link ItemStack}
     *
     * @param itemStack the item stack to get it from
     */
    public Button(ItemStack itemStack) {
        this.material = itemStack.getType();
        this.meta = itemStack.getItemMeta();

        this.data = itemStack.getData().getData();
        this.amount = itemStack.getAmount();
    }

    @Override
    public Button clone() {
        return new Button(this.material)
                .setDisplayName(this.getDisplayName())
                .setAmount(this.getAmount())
                .setClickAction(this.getClickAction())
                .setLore(this.getLore())
                .setData(this.getData());
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
        final ItemMeta meta;

        if (this.meta == null) {
            meta = item.getItemMeta();
        } else {
            meta = this.meta;
        }

        if (meta != null) { // it can STILL be null, some items don't have an item meta.
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