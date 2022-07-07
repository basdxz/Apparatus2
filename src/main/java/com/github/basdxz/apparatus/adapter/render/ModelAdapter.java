package com.github.basdxz.apparatus.adapter.render;

public abstract class ModelAdapter {
    public abstract void render();

    public final static class EmptyModelAdapter extends ModelAdapter {
        @Override
        public void render() {
        }
    }
}
