package dev.matthiesen.common.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.compat.accessories.client.AccessoriesCompatClient;
import dev.matthiesen.common.cobblemon_breathers.datagen.ModTags;
import dev.matthiesen.common.cobblemon_breathers.registry.ComponentTypesRegistry;
import io.wispforest.accessories.api.AccessoriesCapability;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

public class CobblemonBreathersClient {
    public static void init() {
        Constants.createInfoLog("Initializing client...");
    }

    public static void registerRenderers() {
        if (CobblemonBreathers.COMMON_PLATFORM.isModLoaded("accessories")) {
            Constants.createInfoLog("Accessories mod detected, initializing client compatibility...");
            AccessoriesCompatClient.init();
        }
    }

    public static void createAirSupplyDisplayLayer(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
            Minecraft client = Minecraft.getInstance();
            if (client.player == null) return;
            if (!client.player.isUnderWater()) return;

            int x = client.getWindow().getGuiScaledWidth() / 2 - 92;
            int y = client.getWindow().getGuiScaledHeight() - 49;

            // Check Armor Toughness
            if (client.player.getAttributeValue(Attributes.ARMOR) > 0) {
                y -= 10;
            }

            boolean hasReBreather = client.player.getInventory().contains(ModTags.Items.BREATHERS);
            if (!hasReBreather) return;

            boolean reBreatherIsInInventory = client.player.getInventory().items.stream().anyMatch(itemStack -> itemStack.is(ModTags.Items.BREATHERS));
            if (reBreatherIsInInventory) return; // If it's in the inventory then it's not being used

            ItemStack itemToDisplay = null;

            boolean reBreatherIsInArmorSlot = client.player.getInventory().armor.get(3).is(ModTags.Items.BREATHERS);

            if (reBreatherIsInArmorSlot) {
                itemToDisplay = client.player.getInventory().armor.get(3);
            }

            if (CobblemonBreathers.COMMON_PLATFORM.isModLoaded("accessories")) {
                var capability = AccessoriesCapability.get(client.player);
                if (capability != null) {
                    var bl = capability.isEquipped(stack -> !stack.isEmpty() && stack.is(ModTags.Items.BREATHERS));
                    if (bl) {
                        var test = capability.getEquipped(stack -> !stack.isEmpty() && stack.is(ModTags.Items.BREATHERS)).getFirst();
                        itemToDisplay = test.stack();
                    }
                }
            }

            if (itemToDisplay == null) return;

            guiGraphics.renderItem(itemToDisplay, x, y - 4);

            float scale = 0.75f; // Adjust between 0.5 and 0.9 for "small"
            String text = Component.translatable("airSupply.cobblemon_breathers.hud_display",
                    itemToDisplay.getOrDefault(ComponentTypesRegistry.AIR_RESERVE.get(), 0)).getString();

            guiGraphics.pose().pushPose();
            guiGraphics.pose().translate(x + 18, y, 0);
            guiGraphics.pose().scale(scale, scale, scale);

            guiGraphics.drawString(client.font, text, 0, 0, 0xFFFFFF);
            guiGraphics.pose().popPose();
    }
}
