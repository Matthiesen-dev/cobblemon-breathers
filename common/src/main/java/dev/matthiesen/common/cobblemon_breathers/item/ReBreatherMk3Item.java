package dev.matthiesen.common.cobblemon_breathers.item;

import net.minecraft.world.effect.MobEffects;

public class ReBreatherMk3Item extends AbstractReBreatherItem {
    public static final int MAX_AIR = 1200;

    public ReBreatherMk3Item() {
        super(MAX_AIR, builder -> builder.addEffect(MobEffects.CONDUIT_POWER));
    }
}
