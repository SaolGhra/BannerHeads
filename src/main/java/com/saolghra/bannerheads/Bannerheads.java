package com.saolghra.bannerheads;

import com.saolghra.bannerheads.BannerWearFeature;
import net.fabricmc.api.ModInitializer;


public class Bannerheads implements ModInitializer {

    @Override
    public void onInitialize() {
        System.out.println("BannerHeads initialized!");
        BannerWearFeature.registerWearBanner();
    }
}
