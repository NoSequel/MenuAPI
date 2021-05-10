package io.github.nosequel.menu.filling;

import io.github.nosequel.menu.Menu;
import io.github.nosequel.menu.buttons.Button;

public enum FillingType {

    BORDER {
        /**
         * Fill the slots inside of the menu with the
         * specified filling type.
         *
         * @param menu the menu to fill
         * @return the filling buttons
         */
        @Override
        public Button[] fillMenu(Menu menu) {
            final Button[] buttons = new Button[menu.getSize()];

            for (int i = 0; i < menu.getSize(); i++) {
                if (i < 9 || i >= menu.getSize() - 9 || i % 9 == 0 || i % 9 == 8) {
                    buttons[i] = new Button(menu.getFillerType().getType())
                            .setData(menu.getFillerType().getData().getData())
                            .setDisplayName(" ")
                            .setClickAction(event -> event.setCancelled(true));
                }
            }

            return buttons;
        }
    },

    EMPTY_SLOTS {
        /**
         * Fill the slots inside of the menu with the
         * specified filling type.
         *
         * @param menu the menu to fill
         * @return the filling buttons
         */
        @Override
        public Button[] fillMenu(Menu menu) {
            final Button[] buttons = new Button[menu.getSize()];

            for (int i = 0; i < menu.getSize(); i++) {
                buttons[i] = new Button(menu.getFillerType().getType())
                        .setData(menu.getFillerType().getData().getData())
                        .setDisplayName(" ")
                        .setClickAction(event -> event.setCancelled(true));

            }

            return buttons;
        }
    };

    /**
     * Fill the slots inside of the menu with the
     * specified filling type.
     *
     * @param menu the menu to fill
     * @return the filling buttons
     */
    public abstract Button[] fillMenu(Menu menu);
}