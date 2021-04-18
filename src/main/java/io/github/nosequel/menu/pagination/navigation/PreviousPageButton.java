package io.github.nosequel.menu.pagination.navigation;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.PaginatedMenu;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.material.MaterialData;

import java.util.function.Consumer;

public class PreviousPageButton extends Button {

    private final PaginatedMenu menu;

    public PreviousPageButton(PaginatedMenu menu) {
        this.menu = menu;
    }

    /**
     * Get the index of the button.
     *
     * @return the index slot
     */
    @Override
    public int getIndex() {
        return 0;
    }

    /**
     * Get the material of the button to
     * display in the menu.
     *
     * @return the type of the material
     */
    @Override
    public Material getMaterial() {
        return menu.getPaginationButtonType().getType();
    }

    /**
     * Get the string to set the display name
     * of the item in the menu to.
     *
     * @return the display name
     */
    @Override
    public String getDisplayName() {
        return "&aPrevious Page";
    }

    /**
     * Get the string array to set the lore
     * of the item in the menu to.
     *
     * @return the lore
     */
    @Override
    public String[] getLore() {
        return new String[0];
    }

    /**
     * Get the click action of the button.
     * <p>
     * This will be called whenever the
     * player clicks the button inside of
     * the inventory.
     *
     * @return the click action to call
     */
    @Override
    public Consumer<InventoryClickEvent> getClickAction() {
        return event -> {
            menu.navigatePrevious();
            event.setCancelled(true);
        };
    }

    /**
     * Get the data of the material
     * to display in the inventory.
     *
     * @return the data
     */
    @Override
    public byte getData() {
        final MaterialData data = this.menu.getPaginationButtonType().getData();

        return data == null
                ? super.getData()
                : data.getData();
    }
}