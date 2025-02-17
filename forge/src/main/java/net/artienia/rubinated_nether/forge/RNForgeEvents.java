package net.artienia.rubinated_nether.forge;

import net.artienia.rubinated_nether.RNEvents;
import net.artienia.rubinated_nether.RubinatedNether;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RubinatedNether.MOD_ID)
public final class RNForgeEvents {

    @SubscribeEvent
    public static void interactBlock(PlayerInteractEvent.RightClickBlock event) {
        InteractionResult result = RNEvents.interactBlock(event.getEntity(), event.getLevel(), event.getHand(), event.getHitVec());
        if(result.consumesAction()) {
            event.setCanceled(true);
            event.setCancellationResult(result);
        }
    }
}
