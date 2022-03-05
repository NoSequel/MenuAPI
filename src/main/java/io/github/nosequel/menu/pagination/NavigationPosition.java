package io.github.nosequel.menu.pagination;

import io.github.nosequel.menu.buttons.Button;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NavigationPosition {

    TOP {
        /**
         * Get the navigation buttons
         * for the position type.
         *
         * @return the buttons
         */
        @Override
        public Button[] getNavigationButtons(PaginatedMenu menu) {
            final Button[] buttons = new Button[9];

            buttons[0] = menu.getPreviousPageButton().setClickAction(event -> {
                menu.navigatePrevious();
                event.setCancelled(true);
            });

            buttons[8] = menu.getNextPageButton().setClickAction(event -> {
                final int page = menu.getPage();
                final int maxPages = menu.getButtons().length / (menu.getSize() + 9);

                if (page != maxPages) {
                    menu.navigateNext();
                    event.setCancelled(true);
                }
            });

            return buttons;
        }

        /**
         * Get a list of buttons in the range of the
         * current menu's page.
         *
         * @param buttons the list of buttons to get the buttons in range from
         * @param menu    the menu to get the data from
         * @return the buttons in range
         */
        @Override
        public Button[] getButtonsInRange(Button[] buttons, PaginatedMenu menu) {
            final Button[] returningButtons = new Button[menu.getSize()];

            final int size = menu.getSize();
            final int page = menu.getPage();

            final int maxElements = size - 9;

            final int start = ((page - 1) * maxElements);
            final int end = (start + maxElements) - 1;

            for (int index = 0; index < buttons.length; index++) {
                final Button button = buttons[index];

                if (button != null && index >= start && index <= end) {
                    returningButtons[index - ((maxElements) * (page - 1)) + 9] = button;
                }
            }

            final Button[] navigationBar = menu.getNavigationBar();

            for (int index = 0; index < navigationBar.length; index++) {
                final Button button = navigationBar[index];

                if (button != null) {
                    returningButtons[index] = button;
                }
            }

            return returningButtons;
        }
    },

    BOTTOM {
        /**
         * Get the navigation buttons
         * for the position type.
         *
         * @return the buttons
         */
        @Override
        public Button[] getNavigationButtons(PaginatedMenu menu) {
            final Button[] buttons = new Button[menu.getSize()];

            buttons[menu.getSize() - 9] = menu.getPreviousPageButton().setClickAction(event -> {
                menu.navigatePrevious();
                event.setCancelled(true);
            });

            buttons[menu.getSize() - 1] = menu.getNextPageButton().setClickAction(event -> {
                menu.navigateNext();
                event.setCancelled(true);
            });

            return buttons;
        }

        /**
         * Get a list of buttons in the range of the
         * current menu's page.
         *
         * @param buttons the list of buttons to get the buttons in range from
         * @param menu    the menu to get the data from
         * @return the buttons in range
         */
        @Override
        public Button[] getButtonsInRange(Button[] buttons, PaginatedMenu menu) {
            final Button[] returningButtons = new Button[menu.getSize()];

            final int size = menu.getSize();
            final int page = menu.getPage();

            final int maxElements = size - 9;

            final int start = (page - 1) * maxElements;
            final int end = (start + maxElements) - 1;

            for (int index = 0; index < buttons.length; index++) {
                final Button button = buttons[index];

                if (button != null && index >= start && index <= end) {
                    returningButtons[index - ((maxElements) * (page - 1))] = button;
                }
            }

            final Button[] navigationBar = menu.getNavigationBar();

            for (int index = 0; index < navigationBar.length; index++) {
                final Button button = navigationBar[index];

                if (button != null) {
                    returningButtons[index] = button;
                }
            }

            return returningButtons;
        }
    };

    /**
     * Get the navigation buttons
     * for the position type.
     *
     * @param menu the menu to get the data from
     * @return the buttons
     */
    public abstract Button[] getNavigationButtons(PaginatedMenu menu);

    /**
     * Get a list of buttons in the range of the
     * current menu's page.
     *
     * @param buttons the list of buttons to get the buttons in range from
     * @param menu    the menu to get the data from
     * @return the buttons in range
     */
    public abstract Button[] getButtonsInRange(Button[] buttons, PaginatedMenu menu);

}