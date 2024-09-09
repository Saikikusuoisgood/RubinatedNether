package net.artienia.rubinated_nether.block;

import net.artienia.rubinated_nether.RubinatedNether;
import net.artienia.rubinated_nether.block.custom.FreezerBlock;
import net.artienia.rubinated_nether.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.BlockGetter;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RubinatedNether.MOD_ID);

    public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock("ruby_block", () -> new Block(BlockBehaviour
    .Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).mapColor(MapColor.FIRE)));

    public static final DeferredBlock<Block> MOLTEN_RUBY_BLOCK = registerBlock("molten_ruby_block", () -> new RotatedPillarBlock(BlockBehaviour
    .Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS)
    .mapColor(MapColor.FIRE)
    .lightLevel((p_220871_) -> {return 15;})
    ));

    public static final DeferredBlock<Block> BLEEDING_OBSIDIAN = registerBlock("bleeding_obsidian", () -> new Block(BlockBehaviour
    .Properties.ofFullCopy(Blocks.CRYING_OBSIDIAN)
    .mapColor(MapColor.FIRE)
    .pushReaction(PushReaction.BLOCK)
    ));
    
    public static final DeferredBlock<StainedGlassBlock> RUBY_GLASS = registerBlock("ruby_glass", () -> new StainedGlassBlock(DyeColor.RED, BlockBehaviour
    .Properties.ofFullCopy(Blocks.GLASS)
    .mapColor(MapColor.FIRE)
    .explosionResistance(100F)
    .noOcclusion()
    .isRedstoneConductor(ModBlocks::never)
    ));
    public static final DeferredBlock<StainedGlassPaneBlock> RUBY_GLASS_PANE = registerBlock("ruby_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.RED, Block
    .Properties.ofFullCopy(Blocks.GLASS)
    .mapColor(MapColor.FIRE)
    .explosionResistance(100F)
    .noOcclusion()
    .isRedstoneConductor(ModBlocks::never)
    .isViewBlocking(ModBlocks::never)
    ));

    public static final DeferredBlock<Block> RUBY_CHANDELIER = registerBlock("ruby_chandelier", () -> new Chandelier(BlockBehaviour
    .Properties.ofFullCopy(Blocks.COPPER_BLOCK)
    .mapColor(MapColor.FIRE)
    .noOcclusion()
    .isViewBlocking(ModBlocks::never)
    .lightLevel((p_220871_) -> {return 15;})
    ));
    public static final DeferredBlock<Block> RUBY_LAVA_LAMP = registerBlock("ruby_lava_lamp", () -> new LavaLamp(BlockBehaviour
    .Properties.ofFullCopy(Blocks.COPPER_BLOCK)
    .mapColor(MapColor.FIRE)
    .noOcclusion()
    .lightLevel((p_220871_) -> {return 15;})
    ));

    public static final DeferredBlock<Block> MOLTEN_RUBY_ORE = registerBlock("molten_ruby_ore", () -> new MagmaXP(BlockBehaviour
    .Properties.ofFullCopy(Blocks.MAGMA_BLOCK)
    .strength(2f)
    .requiresCorrectToolForDrops(), UniformInt.of(4, 8)
    ));
    public static final DeferredBlock<Block> NETHER_RUBY_ORE = registerBlock("nether_ruby_ore", () -> new DropExperienceBlock(
	UniformInt.of(3, 6),
	BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)
    .strength(2f)
    .requiresCorrectToolForDrops()
    ));
    public static final DeferredBlock<Block> RUBINATED_BLACKSTONE = registerBlock("rubinated_blackstone", () -> new DropExperienceBlock(
	UniformInt.of(3, 6),	
	BlockBehaviour.Properties.ofFullCopy(Blocks.GILDED_BLACKSTONE)
    .strength(2f)
    .requiresCorrectToolForDrops()
    ));

    public static final DeferredBlock<Block> FREEZER = registerBlock("freezer", () -> new FreezerBlock(BlockBehaviour
    .Properties.ofFullCopy(Blocks.COPPER_BLOCK)
    .noOcclusion()
    ));
    public static final DeferredBlock<Block> RUBY_LASER = registerBlock("ruby_laser", () -> new ObserverBlock(BlockBehaviour
    .Properties.ofFullCopy(Blocks.COPPER_BLOCK)
    .noOcclusion()
    ));
    public static final DeferredBlock<LanternBlock> RUBY_LANTERN = registerBlock("ruby_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<BlockItem> registerBlockItem(String name, DeferredBlock<T> block) {
        return ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static boolean never(BlockState state, BlockGetter getter, BlockPos pos){
        return false;
    }
}
