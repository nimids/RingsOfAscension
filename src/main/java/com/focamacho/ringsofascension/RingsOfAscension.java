package com.focamacho.ringsofascension;

import com.focamacho.ringsofascension.config.ConfigRingsOfAscension;
import com.focamacho.ringsofascension.handler.LootTablesHandler;
import com.focamacho.ringsofascension.init.ModItems;
import com.focamacho.sealconfig.SealConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.io.File;

public class RingsOfAscension implements ModInitializer {

	private final SealConfig sealConfig = new SealConfig();
	public static ConfigRingsOfAscension config;
	public static final ItemGroup creativeTab = FabricItemGroup.builder(new Identifier("ringsofascension", "rings"))
			.icon(() -> new ItemStack(ModItems.ringGrowth.isEnabled() ? ModItems.ringGrowth : ModItems.allRings.get(0)))
			.build();

	@Override
	public void onInitialize() {
		config = sealConfig.getConfig(new File(FabricLoader.getInstance().getConfigDir().toFile(), "ringsofascension.json"), ConfigRingsOfAscension.class);
		ModItems.init();
		LootTablesHandler.init();
	}

}
