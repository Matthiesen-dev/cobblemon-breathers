package dev.matthiesen.common.cobblemon_breathers.util;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.ArrayList;
import java.util.List;

public class Effects {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final List<MobEffectInstance> effects = new ArrayList<>();

        public Builder addEffect(Holder<MobEffect> effect) {
            effects.add(new MobEffectInstance(effect));
            return this;
        }

        public List<MobEffectInstance> build() {
            return effects;
        }
    }
}
