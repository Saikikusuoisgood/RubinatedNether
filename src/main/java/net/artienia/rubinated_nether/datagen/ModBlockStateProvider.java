package net.artienia.rubinated_nether.datagen;
import net.artienia.rubinated_nether.RubinatedNether;
import net.artienia.rubinated_nether.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RubinatedNether.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.RUBY_BLOCK);
        blockWithItem(ModBlocks.BLEEDING_OBSIDIAN);
		blockWithItem(ModBlocks.MOLTEN_RUBY_ORE); 
		blockWithItem(ModBlocks.NETHER_RUBY_ORE);
        blockWithItem(ModBlocks.RUBINATED_BLACKSTONE);
		
        // blockWithItem(ModBlocks.RUBY_GLASS);
        paneBlockWithRenderType(ModBlocks.RUBY_GLASS_PANE.get(), modLoc("block/ruby_glass"), modLoc("block/ruby_glass_pane_top"), "minecraft:translucent");
    }

    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}