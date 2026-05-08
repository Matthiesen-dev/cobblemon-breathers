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

    public static void createAirSupplyHUDLayer(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null) return;
        if (!client.player.isUnderWater()) return;

        // Check if the player has any breathers in their inventory, if not then we don't need to render anything
        boolean hasReBreather = client.player.getInventory().contains(ModTags.Items.BREATHERS);
        if (!hasReBreather) return;

        // Check if the player has a re-breather in their inventory, if it is, we don't need to render anything
        boolean reBreatherIsInInventory = client.player.getInventory().items.stream().anyMatch(itemStack -> itemStack.is(ModTags.Items.BREATHERS));
        if (reBreatherIsInInventory) return;

        ItemStack itemToDisplay = null;

        // Check if the player has a re-breather in their armor slot, if it is, then get the item, and set it for display
        boolean reBreatherIsInArmorSlot = client.player.getInventory().armor.get(3).is(ModTags.Items.BREATHERS);
        if (reBreatherIsInArmorSlot) {
            itemToDisplay = client.player.getInventory().armor.get(3);
        }

        // Check if accessories mod is installed, and item is slotted in an accessory slot, if it is then get and set the item for display
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

        // If no item was found in either the armor slot or accessory slot, then we don't need to render anything
        if (itemToDisplay == null) return;

        int x = client.getWindow().getGuiScaledWidth() / 2 - 92;
        int y = client.getWindow().getGuiScaledHeight() - 49;

        // Check Armor Toughness
        if (client.player.getAttributeValue(Attributes.ARMOR) > 0) {
            y -= 10;
        }

        // Render the item
        guiGraphics.renderItem(itemToDisplay, x, y - 4);

        // Render info text
        float scale = 0.75f;
        String text = Component.translatable("airSupply.cobblemon_breathers.hud_display",
                itemToDisplay.getOrDefault(ComponentTypesRegistry.AIR_RESERVE.get(), 0)).getString();

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(x + 18, y, 0);
        guiGraphics.pose().scale(scale, scale, scale);

        guiGraphics.drawString(client.font, text, 0, 0, 0xFFFFFF);
        guiGraphics.pose().popPose();
    }
}
