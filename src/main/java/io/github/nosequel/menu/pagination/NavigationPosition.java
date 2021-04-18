package io.github.nosequel.menu.pagination;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NavigationPosition {

    TOP(-9),
    BOTTOM(9);

    private final int buttonIndexIncrementation;

}