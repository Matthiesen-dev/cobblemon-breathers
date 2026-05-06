package dev.matthiesen.common.cobblemon_breathers.item;

import net.minecraft.world.effect.MobEffects;

public class ReBreatherMk2Item extends AbstractReBreatherItem {
    public static final int MAX_AIR = 600;

    public ReBreatherMk2Item() {
        super(MAX_AIR, builder -> builder.addEffect(MobEffects.WATER_BREATHING).addEffect(MobEffects.NIGHT_VISION));
    }
}
