package com.a0000.enums;

import static com.a0000.enums.Outcome.*;
/**
 * Created by 100 on 2015/2/8.
 * Enums using "tables" instead of multiple dispatch.
 */
public enum RoShamBo6 implements Competitor<RoShamBo6> {
    PAPER, SCISSORS, ROCK;
    private static Outcome[][] table = {
            { DRAW, LOSE, WIN}, // PAPER
            { WIN, DRAW, LOSE}, // SCISSORS
            { LOSE, WIN, DRAW}, // ROCK
    };

    @Override
    public Outcome compete(RoShamBo6 other) {
        return table[this.ordinal()][other.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class, 20);
    }
}
