package net.artienia.rubinated_nether.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = "rubinated_nether")
public class DualEffectEnchantment extends Enchantment {
    public DualEffectEnchantment() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean checkCompatibility(Enchantment other) {
        return other != Enchantments.UNBREAKING && other != Enchantments.BLOCK_FORTUNE && super.checkCompatibility(other);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getMainHandItem();

        if (itemStack != null && EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.DUAL_EFFECT_ENCHANTMENT.get(), itemStack) > 0) {
            BlockState blockState = event.getState();
            Block block = blockState.getBlock();

            // Simulate Unbreaking III effect
            Random random = new Random();
            int unbreakingLevel = 3; // Simulate Unbreaking III
            if (random.nextInt(unbreakingLevel + 1) > 0) {
                // Prevent tool from taking damage when breaking the block
                itemStack.setDamageValue(itemStack.getDamageValue() - 1);
            }

            // Simulate Fortune III effect
            if (block == Blocks.COAL_ORE || block == Blocks.DIAMOND_ORE || block == Blocks.EMERALD_ORE) {
                // Handle loot drops manually
                List<ItemStack> drops = blockState.getBlock().getDrops(blockState, new Random(), 0);
                for (ItemStack drop : drops) {
                    // Apply Fortune effect based on enchantment level
                    int fortuneLevel = 3; // Simulate Fortune III
                    drop.setCount(drop.getCount() * (fortuneLevel + 1));
                }
                // Cancel default drops
                event.setDrops(drops);
            }
        }
    }
}
