package net.mcreator.example_mod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.WorldServer;
import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.Entity;

import java.util.HashMap;

@Elementsexample_mod.ModElement.Tag
public class MCreatorCraftReinforcedSword extends Elementsexample_mod.ModElement {
	public MCreatorCraftReinforcedSword(Elementsexample_mod instance) {
		super(instance, 4);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorCraftReinforcedSword!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure MCreatorCraftReinforcedSword!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((((itemstack).getDisplayName()).contains("Reinforced Sword")) && (!(((entity instanceof EntityPlayerMP) && (entity.world instanceof WorldServer))
				? ((EntityPlayerMP) entity)
						.getAdvancements()
						.getProgress(
								((WorldServer) entity.world).getAdvancementManager().getAdvancement(
										new ResourceLocation("example_mod:reinforceasword"))).isDone()
				: false))) && (entity instanceof EntityPlayer))) {
			if (entity instanceof EntityPlayerMP)
				MCreatorReinforceASword.trigger.triggerAdvancement((EntityPlayerMP) entity);
		}
	}

	@SubscribeEvent
	public void onItemCrafted(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent event) {
		Entity entity = event.player;
		World world = entity.world;
		int i = (int) entity.posX;
		int j = (int) entity.posY;
		int k = (int) entity.posZ;
		ItemStack itemStack = event.crafting;
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("itemstack", itemStack);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
