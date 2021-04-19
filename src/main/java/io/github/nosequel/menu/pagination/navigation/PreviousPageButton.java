package io.github.nosequel.menu.pagination.navigation;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.PaginatedMenu;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.material.MaterialData;

import java.util.function.Consumer;

public class PreviousPageButton extends Button {

    private final PaginatedMenu menu;

    public PreviousPageButton(int index, PaginatedMenu menu) {
        super(index, menu.getPaginationButtonType().getType());
        this.menu = menu;
        this.setDisplayName("&aPrevious Page");
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