package net.artienia.rubinated_nether.block.entity;

import net.artienia.rubinated_nether.RubinatedNether;
import net.artienia.rubinated_nether.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.eventbus.api.IEventBus;
import net.neoforged.registries.DeferredRegister;
import net.neoforged.registries.ForgeRegistries;
import net.neoforged.registries.RegistryObject;

public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RubinatedNether.MOD_ID);

    public static final RegistryObject<BlockEntityType<FreezerBlockEntity>> FREEZER = BLOCK_ENTITY_TYPES.register("freezer", () ->
            BlockEntityType.Builder.of(FreezerBlockEntity::new, ModBlocks.FREEZER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }

}
