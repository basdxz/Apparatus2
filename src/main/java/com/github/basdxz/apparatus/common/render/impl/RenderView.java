package com.github.basdxz.apparatus.common.render.impl;

import com.github.basdxz.apparatus.common.render.IRenderView;

//TODO: Make these more agnostic and less Minecraft-related?
public enum RenderView implements IRenderView {
    ENTITY, EQUIPPED, EQUIPPED_FIRST_PERSON, INVENTORY
}