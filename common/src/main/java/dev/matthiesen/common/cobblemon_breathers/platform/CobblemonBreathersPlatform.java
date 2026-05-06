package dev.matthiesen.common.cobblemon_breathers.platform;

import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface CobblemonBreathersPlatform {
    // Registry Helpers
    <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> blockEntityType);
    <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> block);
    <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item);
    <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound);
    <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab);
    <T extends CriterionTrigger<?>> Supplier<T> registerCriteriaTriggers(String id, Supplier<T> criterionTrigger);
    <T extends ResourceLocation> Supplier<T> registerStats(String id, Supplier<T> stats);
    <T extends DataComponentType<?>> Supplier<T> registerComponentType(String id, Supplier<T> component);
    <T extends MapCodec<? extends EnchantmentEntityEffect>> Supplier<T> registerEntityEffects(String name, Supplier<T> codec);

    // General Utilities
    MinecraftServer server();

    // Loader Specific Utilities
    boolean isModLoaded(String modId);
    boolean isDevelopmentEnvironment();
    CreativeModeTab.Builder newCreativeTabBuilder();
    void addItemRegistryCallback(Consumer<Item> consumer);
}
