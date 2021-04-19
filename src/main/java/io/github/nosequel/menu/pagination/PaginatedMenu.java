package io.github.nosequel.menu.pagination;

import io.github.nosequel.menu.Menu;
import io.github.nosequel.menu.buttons.Button;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public abstract class PaginatedMenu extends Menu {

    private ItemStack paginationButtonType = new ItemStack(Material.CARPET, 1, DyeColor.GREEN.getWoolData());
    private NavigationPosition navigationPosition = NavigationPosition.TOP;

    private boolean fillNavBar = true;

    private int page = 1;

    /**
     * Constructor to make a new menu object
     *
     * @param player the player to create the menu for
     * @param title  the title to display at the top of the inventory
     * @param size   the size of the inventory
     */
    public PaginatedMenu(Player player, String title, int size) {
        super(player, title, size);
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
     * Get the type of the pagination button's icon
     *
     * @return the icon
     */
    public ItemStack getPaginationButtonType() {
        return paginationButtonType;
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
        for (Button button : this.getButtonsInRange()) {
            if (button.getIndex() == event.getSlot() && button.getClickAction() != null) {
                button.getClickAction().accept(event);
            }
        }
    }

    /**
     * Get the list of buttons in the
     * range of the current page.
     *
     * @return the list of buttons
     */
    public List<Button> getButtonsInRange() {
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
    public List<Button> getNavigationBar() {
        return Arrays.asList(this.navigationPosition.getNavigationButtons(this).clone());
    }
}