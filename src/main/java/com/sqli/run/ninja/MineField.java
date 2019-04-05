package com.sqli.run.ninja;

public class MineField {

    private static final String LEVEL_COMPLETED = "Level completed";
    private StringBuilder       mineField;

    private MineField() {
    }

    public String print() {
        if (isLevelCompleted()) return LEVEL_COMPLETED;
        return mineField.toString();
    }

    private boolean isLevelCompleted() {
        return mineField.indexOf("P") == mineField.length() - 1;
    }

    public StringBuilder getMineField() {
        return mineField;
    }

    public void setMineField(StringBuilder mineField) {
        this.mineField = mineField;
    }

    public static class Builder {

        private static final String SEPARATOR = "-";
        private static final String MINE      = "o";
        private static final String RIVER     = "~~~";

        private StringBuilder       builder;

        public Builder() {
            builder = new StringBuilder();
        }

        public Builder addMines(int size) {
            builder.append(SEPARATOR);
            for (int i = 0; i < size; i++) {
                builder.append(MINE);
            }
            builder.append(SEPARATOR);
            return this;
        }

        public Builder addRiver() {
            builder.append(SEPARATOR);
            builder.append(RIVER);
            builder.append(SEPARATOR);
            return this;
        }

        public MineField build() {
            MineField mineField = new MineField();
            mineField.setMineField(builder);
            return mineField;
        }

    }

}
