package com.github.basdxz.paratileentity.network;


import lombok.Data;

import java.io.Serializable;

@Data
public class Pos3 implements Serializable {
    private final int posX;
    private final int posY;
    private final int posZ;
}
