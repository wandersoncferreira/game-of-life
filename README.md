# game-of-life

A Clojure project designed to solve the `Game of Life` kata. 
[Conway'sGame of
Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) it is a
zero-player game, meaning that its evolution is determined by its
initial state, requiring no further input. The universe of Game of
Life is an infinite, two-dimensional orthogonal grid of square cells,
each of which is in one of two possible states: dead or alive.

Every cell interacts with its eight neighbors, which are the cells
that are horizontally, vertically, or diagonally adjacent.

## Rules

Four simple rules to control the behavior of Game of Life.

    1. Any live cell with fewer than two live neighbours dies, as if caused by under-population
    2. Any live cell with two or three neighbours lives on to the next generation
    3. Any live cell with more than three live neighbours dies, as if by overcrowding
    4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction

## Implementations

1. 2020/04/11 - first attempt

## License

Copyright © 2020 Wanderson Ferreira

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
