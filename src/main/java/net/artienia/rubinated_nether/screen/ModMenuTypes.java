package net.artienia.rubinated_nether.screen;

import net.artienia.rubinated_nether.RubinatedNether;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.common.extensions.IForgeMenuType;
import net.neoforged.eventbus.api.IEventBus;
import net.neoforged.network.IContainerFactory;
import net.neoforged.registries.DeferredRegister;
import net.neoforged.registries.ForgeRegistries;
import net.neoforged.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, RubinatedNether.MOD_ID);

    public static final RegistryObject<MenuType<FreezerMenu>> FREEZER_MENU =
            register("freezer_menu", FreezerMenu::new);


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, @SuppressWarnings("SameParameterValue") String name) {
        return MENUS.register(name, ()-> IForgeMenuType.create(factory));
    }

    private static<T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, MenuType.MenuSupplier<T> menu) {
        return MENUS.register(name, () -> new MenuType<>(menu, FeatureFlags.VANILLA_SET));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}