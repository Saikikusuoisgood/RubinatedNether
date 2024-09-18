package net.artienia.rubinated_nether.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.artienia.rubinated_nether.RubinatedNether;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, RubinatedNether.MOD_ID);

    public static final RegistryObject<Enchantment> DUAL_EFFECT_ENCHANTMENT =
            ENCHANTMENTS.register("dual_effect_enchantment", DualEffectEnchantment::new);
}
