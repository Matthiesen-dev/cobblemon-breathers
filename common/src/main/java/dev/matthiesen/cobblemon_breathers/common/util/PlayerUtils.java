package dev.matthiesen.cobblemon_breathers.common.util;

import net.minecraft.world.entity.player.Player;

public final class PlayerUtils {
    public static boolean checkAntiConditions(Player player) {
        return player.isCreative() || player.isSpectator();
    }

    public static boolean checkPlayerConditions(Player player) {
        return !player.isUnderWater() || checkAntiConditions(player);
    }
}
