package com.saolghra.bannerheads;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.item.BannerItem;

public class BannerWearFeature {

    public static void registerWearBanner() {
        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (!world.isClient) {
                return wearBannerOnHead(player, hand);
            }
            return TypedActionResult.pass(player.getStackInHand(hand));
        });
    }

    private static TypedActionResult<ItemStack> wearBannerOnHead(PlayerEntity player, Hand hand) {
        ItemStack heldItem = player.getStackInHand(hand);

        if (heldItem.getItem() instanceof BannerItem) {
            ItemStack currentHelmet = player.getEquippedStack(EquipmentSlot.HEAD);
            if (currentHelmet.isEmpty()) {
                ItemStack bannerCopy = heldItem.copy();
                bannerCopy.setCount(1);

                // Equip the banner using EquipmentSlot.HEAD
                player.equipStack(EquipmentSlot.HEAD, bannerCopy);

                // Handle item consumption based on game mode
                if (!player.isCreative()) {
                    heldItem.decrement(1);
                }

                return TypedActionResult.success(heldItem);
            }
        }

        return TypedActionResult.pass(heldItem);
    }
}