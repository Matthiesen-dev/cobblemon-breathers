package dev.matthiesen.common.cobblemon_breathers.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class ReBreatherMk3Item extends AbstractReBreatherItem {
    public static final int MAX_AIR = 1200;

    public static final List<MobEffectInstance> EFFECTS = List.of(
            new MobEffectInstance(MobEffects.CONDUIT_POWER, 100, 0, false, false)
    );

    public ReBreatherMk3Item() {
        super(MAX_AIR);
    }

    @Override
    public boolean checkPlayerConditions(Player player) {
        return player.isInWater();
    }

    @Override
    public List<MobEffectInstance> getPlayerEffects() {
        return EFFECTS;
    }

    @Override
    public void runPlayerActions(Player player) {}
}
