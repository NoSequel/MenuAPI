package io.github.nosequel.menu.buttons;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * No clue if I'm doing this correctly,
 * I've never used a @Builder annotation for a
 * class before, and it looks rather gay (not homophobic.)
 *
 * I'll make my own builder methods whenever I'm not lazy.
 * @author NV6
 */
@Builder
@Getter
@Setter
public abstract class Button {

    private int index;
    private final Material material;

    private String displayName;
    private String[] lore;

    private Consumer<InventoryClickEvent> clickAction = event -> event.setCancelled(true);

    private int amount = 1;
    private byte data = 0;

    /**
     *
     * @param index    the display slot of the button
     * @param material the icon of the button
     */
    public Button(int index, Material material) {
        this.material = material;
        this.index = index;
    }

    /**
     * Convert the button into an {@link ItemStack}.
     *
     * @return the newly created item stack
     */
    public ItemStack toItemStack() {
        final ItemStack item = new ItemStack(this.getMaterial(), this.getAmount(), this.getData());
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