package com.github.basdxz.apparatus.common.registry;

import com.github.basdxz.apparatus.common.parathing.IParaThing;
import lombok.*;

import java.util.function.Consumer;

public interface IParaFactory {
    void createParaThings(@NonNull IParaRegistry registry, @NonNull Consumer<IParaThing> consumer);
}
