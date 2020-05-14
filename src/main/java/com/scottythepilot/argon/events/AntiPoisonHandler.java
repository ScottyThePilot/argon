package com.scottythepilot.argon.events;

import com.scottythepilot.argon.ArgonMod;
import com.scottythepilot.argon.ArgonRegistryHandler;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.PotionEvent.PotionAddedEvent;
import net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ArgonMod.MOD_ID, bus = Bus.FORGE)
public class AntiPoisonHandler {
	@SubscribeEvent
	public static void onPotionApplicable(PotionApplicableEvent event) {
		final Effect ANTI_POISON = ArgonRegistryHandler.ANTI_POISON_EFFECT.get();
		LivingEntity entity = event.getEntityLiving();
		Effect potion = event.getPotionEffect().getPotion();
		
		if (potion == Effects.POISON && entity.isPotionActive(ANTI_POISON))
			event.setResult(Result.DENY);
	}
	
	@SubscribeEvent
	public static void onPotionAdded(PotionAddedEvent event) {
		final Effect ANTI_POISON = ArgonRegistryHandler.ANTI_POISON_EFFECT.get();
		LivingEntity entity = event.getEntityLiving();
		Effect potion = event.getPotionEffect().getPotion();
		
		if (potion == ANTI_POISON && entity.isPotionActive(Effects.POISON))
			entity.removePotionEffect(Effects.POISON);
	}
}
