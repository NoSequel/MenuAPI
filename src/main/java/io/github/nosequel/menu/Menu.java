package io.github.nosequel.menu;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.filling.FillingType;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Menu {

    private final List<FillingType> fillers = new ArrayList<>();

    private final Player player;
    public final String title;
    private final int size;

    public Button[] buttons;

    // the inventory to use if the inventory already exists,
    // to avoid re-opening the inventory whenever updating.
    private Inventory inventory;

    private MenuType menuType = MenuType.INVENTORY;

    // the button type used for filling the inventory slots
    private ItemStack fillerType;

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

        this.buttons = new Button[this.size];

        this.registerMenu();
    }

    /**
     * Update the menu for the player
     */
    public void updateMenu() {
        this.updateMenu(this.buttons);
    }

    /**
     * Update the menu for the player
     *
     * @param buttons the list of buttons to update it to
     */
    public void updateMenu(Button[] buttons) {
        final Inventory inventory = this.inventory == null
                ? this.menuType.createInventory(this)
                : this.inventory;

        this.clearMenu(inventory);

        this.tick();

        final Button[] fillerButtons = this.getFillerButtons();

        for (int i = 0; i < fillerButtons.length; i++) {
            if (fillerButtons[i] != null) {
                this.buttons[i] = fillerButtons[i];
            }
        }

        for(int index = 0; index < buttons.length; index++) {
            if (buttons[index] != null) {
                inventory.setItem(index, buttons[index].toItemStack());
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
     * Get the filler buttons for the menu
     *
     * @return the filler buttons
     */
    public Button[] getFillerButtons() {
        final Button[] buttons = new Button[this.size];

        for (FillingType filler : this.fillers) {
            final Button[] fillers = filler.fillMenu(this);

            for (int i = 0; i < fillers.length; i++) {
                if (fillers[i] != null) {
                    this.buttons[i] = fillers[i];
                }
            }
        }

        return buttons;
    }

    /**
     * The method to get the buttons for the current inventory tick
     * <p>
     * Use {@code this.buttons[index] = Button} to assign
     * a button to a slot.
     */
    public abstract void tick();

    /**
     * Register the menu to the menu handler
     */
    public void registerMenu() {
        MenuHandler.getInstance().register(this.player, this);
    }

    /**
     * Redirect the player's menu to a new menu
     *
     * @param menu the menu to redirect it to
     */
    public void redirect(Menu menu) {
        menu.updateMenu();
        this.registerMenu();
    }

    /**
     * Add a new filler type to the list of fillers
     *
     * @param type the new type to add
     */
    public void addFiller(FillingType type) {
        this.fillers.add(type);
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

    /**
     * Handle clicking on a button
     *
     * @param event the event called
     */
    public void click(InventoryClickEvent event) {
        try {
            final Button button = this.buttons[event.getSlot()];

            if (button == null) {
                event.setCancelled(true);
                return;
            }

            if (button.getClickAction() != null) {
                button.getClickAction().accept(event);
            }
        } catch (IndexOutOfBoundsException ignored) {

        }
    }

    /**
     * Handle the player closing the inventory
     *
     * @param event the event called
     */
    public void handleClose(InventoryCloseEvent event) {
        MenuHandler.getInstance().unregister((Player) event.getPlayer());
    }
}