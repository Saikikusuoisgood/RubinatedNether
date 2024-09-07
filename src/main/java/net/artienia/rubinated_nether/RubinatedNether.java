package net.artienia.rubinated_nether;

import com.aetherteam.aether.block.AetherBlocks;
import com.mojang.logging.LogUtils;
import net.artienia.rubinated_nether.block.ModBlocks;
import net.artienia.rubinated_nether.block.entity.FreezerBlockEntity;
import net.artienia.rubinated_nether.block.entity.ModBlockEntities;
import net.artienia.rubinated_nether.block.entity.ModBlockEntityTypes;
import net.artienia.rubinated_nether.item.ModItems;
import net.artienia.rubinated_nether.recipe.ModRecipeSerializers;
import net.artienia.rubinated_nether.recipe.ModRecipeTypes;
import net.artienia.rubinated_nether.screen.FreezerScreen;
import net.artienia.rubinated_nether.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import org.slf4j.Logger;

import java.awt.event.InputEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RubinatedNether.MOD_ID)
public class RubinatedNether
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "rubinated_nether";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public RubinatedNether(net.neoforged.bus.api.IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register((modEventBus));
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModBlockEntityTypes.register(modEventBus);
        ModRecipeTypes.register(modEventBus);
        ModRecipeSerializers.register(modEventBus);



        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        this.registerFuels();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.RUBY);
            event.accept(ModItems.MOLTEN_RUBY);
            event.accept(ModItems.RUBY_SHARD);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ModBlocks.MOLTEN_RUBY_BLOCK);
            event.accept(ModBlocks.RUBY_BLOCK);
            event.accept(ModBlocks.BLEEDING_OBSIDIAN);
        }

        if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS){
            event.accept(ModBlocks.MOLTEN_RUBY_BLOCK);
            event.accept(ModBlocks.MOLTEN_RUBY_ORE);
            event.accept(ModBlocks.NETHER_RUBY_ORE);
            event.accept(ModBlocks.RUBINATED_BLACKSTONE);
            event.accept(ModBlocks.BLEEDING_OBSIDIAN);
            event.accept(Blocks.FROSTED_ICE);
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS){
            event.accept(ModBlocks.RUBY_GLASS);
            event.accept(ModBlocks.RUBY_GLASS_PANE);
            event.accept(ModBlocks.RUBY_LAVA_LAMP);
            event.accept(ModBlocks.RUBY_CHANDELIER);
            event.accept(ModBlocks.RUBY_LANTERN);
            event.accept(ModBlocks.FREEZER);
        }
        if(event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS){
            event.accept(ModBlocks.RUBY_LASER);
        }

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.FREEZER_MENU.get(), FreezerScreen::new);
        }
    }

    private void registerFuels() {
        FreezerBlockEntity.addItemFreezingTime(Items.SNOWBALL, 50);
        FreezerBlockEntity.addItemFreezingTime(Blocks.SNOW_BLOCK, 200);
        FreezerBlockEntity.addItemFreezingTime(Blocks.FROSTED_ICE, 400);
        FreezerBlockEntity.addItemFreezingTime(Blocks.ICE, 800);
        FreezerBlockEntity.addItemFreezingTime(Blocks.BLUE_ICE, 1600);
        FreezerBlockEntity.addItemFreezingTime(Blocks.PACKED_ICE, 3200);

        if(ModList.get().isLoaded("aether")){
            FreezerBlockEntity.addItemFreezingTime(AetherBlocks.ICESTONE.get(), 600);
        }
    }
}
