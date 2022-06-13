package com.github.basdxz.apparatus.tiger;

public abstract class ModelAdapter {
    public abstract void render();

    public final static class EmptyModelAdapter extends ModelAdapter {
        @Override
        public void render() {
        }
    }
}
