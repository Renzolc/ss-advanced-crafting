package dev.renzo.ssadvancedcrafting;

import dev.renzo.ssadvancedcrafting.client.ClientSetup;
import dev.renzo.ssadvancedcrafting.init.ModItems;
import dev.renzo.ssadvancedcrafting.network.ModNetwork;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@Mod(SsAdvancedCraftingMod.MOD_ID)
public class SsAdvancedCraftingMod {
	public static final String MOD_ID = "ss_advanced_crafting";
	public static final Logger LOGGER = LogUtils.getLogger();

	public SsAdvancedCraftingMod(IEventBus modBus) {
		ModItems.register(modBus);
		modBus.addListener(ModNetwork::register);
		if (FMLEnvironment.dist == Dist.CLIENT) {
			ClientSetup.init(modBus);
		}
		LOGGER.info("SS Advanced Crafting loaded");
	}
}
