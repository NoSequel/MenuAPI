package io.github.nosequel.menu.pagination;

import io.github.nosequel.menu.Menu;
import io.github.nosequel.menu.buttons.Button;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

@Getter
@Setter
public abstract class PaginatedMenu extends Menu {

    private NavigationPosition navigationPosition = NavigationPosition.TOP;

    private Button previousPageButton = new Button(Material.MELON)
            .setDisplayName(ChatColor.GREEN + "Previous Page");

    private Button nextPageButton = new Button(Material.MELON)
            .setDisplayName(ChatColor.GREEN + "Next Page");

    private int page = 1;

    /**
     * Constructor to make a new menu object
     *
     * @param player the player to create the menu for
     * @param title  the title to display at the top of the inventory
     * @param size   the size of the inventory
     */
    public PaginatedMenu(Player player, String title, int size, int maxPages) {
        super(player, title, size);
        this.buttons = new Button[size * maxPages];
    }

    /**
     * Navigate to the next menu page
     */
    public void navigateNext() {
        this.page += 1;
        this.updateMenu();
    }

    /**
     * Navigate to the previous menu page
     */
    public void navigatePrevious() {
        this.page = Math.max(1, this.page - 1);
        this.updateMenu();
    }

    /**
     * Update the menu for the player
     */
    @Override
    public void updateMenu() {
        this.updateMenu(this.getButtonsInRange());
    }

    /**
     * Handle clicking on a button
     *
     * @param event the event called
     */
    @Override
    public void click(InventoryClickEvent event) {
        final Button[] buttons = this.getButtonsInRange();
        final Button button = buttons[event.getSlot()];

        if (button == null) {
            event.setCancelled(true);
            return;
        }

        if (button.getClickAction() != null) {
            button.getClickAction().accept(event);
        }
    }

    /**
     * Get the list of buttons in the
     * range of the current page.
     *
     * @return the list of buttons
     */
    public Button[] getButtonsInRange() {
        return this.navigationPosition.getButtonsInRange(this.getButtons(), this);
    }

    /**
     * Get the list of buttons for the navigation bar.
     * <p>
     * These buttons will be displayed independent
     * of the current page of the menu.
     *
     * @return the list of buttons
     */
    public Button[] getNavigationBar() {
        return this.navigationPosition.getNavigationButtons(this).clone();
    }
}