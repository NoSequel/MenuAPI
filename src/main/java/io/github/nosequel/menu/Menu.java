package io.github.nosequel.menu;

import io.github.nosequel.menu.buttons.Button;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.logging.Level;

public abstract class Menu {

    private final Player player;
    private final String title;
    private final int size;

    // the inventory to use if the inventory already exists,
    // to avoid re-opening the inventory whenever updating.
    private Inventory inventory;

    /**
     * Constructor to make a new menu object
     *
     * @param player the player to create the menu for
     * @param title  the title to display at the top of the inventory
     * @param size   the size of the inventory
     */
    public Menu(Player player, String title, int size) {
        this.player = player;
        this.size = size;
        this.title = title;

        this.registerMenu();
    }

    /**
     * Get the buttons to display in the menu.
     * <p>
     * These buttons will be converted
     * into an {@link org.bukkit.inventory.ItemStack}
     * and will be displayed inside of the menu.
     *
     * @return the list of buttons
     */
    public abstract List<Button> getButtons();

    /**
     * Update the menu for the player
     */
    public void updateMenu() {
        this.updateMenu(this.getButtons());
    }

    /**
     * Update the menu for the player
     *
     * @param buttons the list of buttons to update it to
     */
    public void updateMenu(List<Button> buttons) {
        final Inventory inventory = this.inventory == null
                ? Bukkit.createInventory(null, this.size, ChatColor.translateAlternateColorCodes('&', this.title))
                : this.inventory;

        this.clearMenu(inventory);

        for (Button button : buttons) {
            if (button.getIndex() < this.size) {
                inventory.setItem(button.getIndex(), button.toItemStack());
            } else {
                Bukkit.getLogger().log(Level.WARNING, "Button was not added to menu (index was higher than the menu size, index=" + button.getIndex() + ", size=" + this.size + ")");
            }
        }

        if(inventory != this.inventory) {
            this.player.closeInventory();
            this.player.openInventory(inventory);
        } else {
            player.updateInventory();
        }

        this.inventory = inventory;
        this.registerMenu();
    }

    /**
     * Register the menu to the menu handler
     */
    public void registerMenu() {
        MenuHandler.getInstance().register(this.player, this);
    }

    /**
     * Clear player's currently open inventory
     * <p>
     * This is a loop which loops through 0 -> this.size,
     * and will return if the provided inventory is null.
     *
     * @param inventory the inventory to clear
     */
    public void clearMenu(Inventory inventory) {
        if(inventory != null) {
            for(int i = 0; i < this.size; i++) {
                inventory.setItem(i, new ItemStack(Material.AIR));
            }
        }
    }
}