package net.artienia.rubinated_nether.item;

import net.artienia.rubinated_nether.RubinatedNether;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.minecraft.world.item.BlockItem;


public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RubinatedNether.MOD_ID);

    public static final DeferredItem<Item> RUBY = ITEMS.register("ruby",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> MOLTEN_RUBY = ITEMS.register("molten_ruby",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RUBY_SHARD = ITEMS.register("ruby_shard",
            () -> new RubyCurrency(new Item.Properties()));

    public static final DeferredItem<Item> FROSTED_ICE = ITEMS.register("frosted_ice",
            () -> new BlockItem(Blocks.FROSTED_ICE, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
