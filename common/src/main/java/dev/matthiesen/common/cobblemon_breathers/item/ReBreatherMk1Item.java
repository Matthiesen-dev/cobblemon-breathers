package dev.matthiesen.common.cobblemon_breathers.item;

import net.minecraft.world.effect.MobEffects;

public class ReBreatherMk1Item extends AbstractReBreatherItem {
    public static final int MAX_AIR = 300;

    public ReBreatherMk1Item() {
        super(MAX_AIR, builder -> builder.addEffect(MobEffects.WATER_BREATHING));
    }
}
