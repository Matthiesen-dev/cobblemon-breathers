package dev.matthiesen.neoforge.cobblemon_breathers;

import dev.matthiesen.common.cobblemon_breathers.platform.CobblemonBreathersPlatform;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.registries.callback.AddCallback;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class CobblemonBreathersNeoForgePlatform implements CobblemonBreathersPlatform {
    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(String id, Supplier<BlockEntityType<T>> blockEntityType) {
        return CobblemonBreathersNeoForge.BLOCK_ENTITIES.register(id, blockEntityType);
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String id, Supplier<T> block) {
        return CobblemonBreathersNeoForge.BLOCKS.register(id, block);
    }

    @Override
    public <T extends Item> Supplier<T> registerItem(String id, Supplier<T> item) {
        return CobblemonBreathersNeoForge.ITEMS.register(id, item);
    }

    @Override
    public <T extends SoundEvent> Supplier<T> registerSound(String id, Supplier<T> sound) {
        return CobblemonBreathersNeoForge.SOUND_EVENTS.register(id, sound);
    }

    @Override
    public <T extends CreativeModeTab> Supplier<T> registerCreativeModeTab(String id, Supplier<T> tab) {
        return CobblemonBreathersNeoForge.CREATIVE_TABS.register(id, tab);
    }

    @Override
    public <T extends CriterionTrigger<?>> Supplier<T> registerCriteriaTriggers(String id, Supplier<T> criterionTrigger) {
        return CobblemonBreathersNeoForge.ADVANCEMENT_TRIGGERS.register(id, criterionTrigger);
    }

    @Override
    public <T extends ResourceLocation> Supplier<T> registerStats(String id, Supplier<T> stats) {
        return CobblemonBreathersNeoForge.STATS.register(id, stats);
    }

    @Override
    public <T extends DataComponentType<?>> Supplier<T> registerComponentType(String id, Supplier<T> component) {
        return CobblemonBreathersNeoForge.COMPONENT_TYPES.register(id, component);
    }

    @Override
    public MinecraftServer server() {
        return CobblemonBreathersNeoForge.MC_SERVER;
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLEnvironment.production;
    }

    @Override
    public CreativeModeTab.Builder newCreativeTabBuilder() {
        return CreativeModeTab.builder();
    }

    @Override
    public void addItemRegistryCallback(Consumer<Item> consumer) {
        BuiltInRegistries.ITEM.addCallback((AddCallback<Item>) (registry, i, key, item) -> consumer.accept(item));
    }
}
