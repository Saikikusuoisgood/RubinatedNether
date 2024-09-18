package net.artienia.rubinated_nether.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

public class DualEffectEnchantment extends Enchantment {
    public DualEffectEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[] {EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 1; // Only one level for this enchantment, but you can adjust this.
    }

    @Override
    public boolean checkCompatibility(Enchantment other) {
        return other != Enchantments.UNBREAKING && other != Enchantments.BLOCK_FORTUNE && super.checkCompatibility(other);
    }

    // Handle custom unbreaking effect using block break event
    @SubscribeEvent
    public void onBlockBreak(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        ItemStack itemStack = player.getMainHandItem();
        if (itemStack != null && EnchantmentHelper.getItemEnchantmentLevel(this, itemStack) > 0) {
            Block block = event.getState().getBlock();

            // Simulate the Fortune III effect for ores
            if (block == Blocks.COAL_ORE || block == Blocks.DIAMOND_ORE || block == Blocks.EMERALD_ORE) {
                int fortuneLevel = 3; // Simulate Fortune III
                event.setNewSpeed(event.getNewSpeed() + (fortuneLevel * 0.1f));
            }

            // Simulate the Unbreaking V effect
            Random random = new Random();
            int unbreakingLevel = 5; // Simulate Unbreaking V
            if (random.nextInt(unbreakingLevel + 1) > 0) {
                // Prevent tool from taking damage
                itemStack.setDamageValue(itemStack.getDamageValue() - 1);
            }
        }
    }
}
