package net.artienia.rubinated_nether.recipe;

import net.artienia.rubinated_nether.RubinatedNether;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, RubinatedNether.MOD_ID);

    public static final DeferredHolder<RecipeType<?>, RecipeType<FreezingRecipe>> FREEZING = RECIPE_TYPES.register("freezing", () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(RubinatedNether.MOD_ID, "freezing")));

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }

}
