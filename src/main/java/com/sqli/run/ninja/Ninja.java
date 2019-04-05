package com.sqli.run.ninja;

public class Ninja {

    private static final StringBuilder NINJA            = new StringBuilder("P");
    private static final StringBuilder BETWEEN_MINES    = new StringBuilder("--");
    private static final StringBuilder NEXT_RIVER       = new StringBuilder("-~~~-");
    private static final int           MAX_JUMP_ALLOWED = 7;

    private MineField                  mineField;
    private boolean                    canSwimm;

    public void startIn(MineField mineField) {
        this.mineField = mineField;
        mineField.getMineField().insert(0, NINJA.toString());
    }

    public void cross() throws CannotPassException {
        int actualPosition = getNinjaPosition();
        int newPosition = getNewPosition();
        canJump(actualPosition, newPosition);
        mineField.getMineField().insert(newPosition, "P");
        mineField.getMineField().deleteCharAt(getNinjaPosition());
    }

    private void canJump(int actualPosition, int newPosition) throws CannotPassException {
        if (isNextRiver() && !canSwimm) {
            throw new CannotPassException();
        } else if (newPosition - actualPosition >= MAX_JUMP_ALLOWED) {
            throw new CannotPassException();
        }
    }

    private boolean isNextRiver() {
        return mineField.getMineField().indexOf(NEXT_RIVER.toString(), getNinjaPosition()) > 0;
    }

    private int getNewPosition() {
        int newPosition = findNextBetweenMines();
        if (newPosition <= 0) {
            newPosition = mineField.getMineField().length();
        }
        return newPosition;
    }

    private int findNextBetweenMines() {
        return mineField.getMineField().indexOf(BETWEEN_MINES.toString(), getNinjaPosition()) + 1;
    }

    private int getNinjaPosition() {
        return mineField.getMineField().indexOf("P");
    }

    public void learnSwimming() {
        canSwimm = true;
    }

}
