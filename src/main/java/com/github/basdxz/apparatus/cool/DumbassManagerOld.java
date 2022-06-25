package com.github.basdxz.apparatus.cool;

import com.github.basdxz.apparatus.common.parathing.IParaItem;
import com.github.basdxz.apparatus.tiger.ParaItemAdapter;
import lombok.*;

public class DumbassManagerOld {
    public static void register(@NonNull IParaItem paraItem) {
        val item = new ParaItemAdapter(paraItem);
    }
}
