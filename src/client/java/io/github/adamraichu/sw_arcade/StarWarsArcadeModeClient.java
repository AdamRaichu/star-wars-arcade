package io.github.adamraichu.sw_arcade;

import com.mojang.brigadier.CommandDispatcher;

import io.github.adamraichu.sw_arcade.client.render.BlueBlasterBoltRenderer;
import io.github.adamraichu.sw_arcade.client.render.BlueCannonBoltRenderer;
import io.github.adamraichu.sw_arcade.client.render.DefaultCloneRenderer;
import io.github.adamraichu.sw_arcade.client.render.BlasterDroidSquadLeaderRenderer;
import io.github.adamraichu.sw_arcade.registry.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.command.CommandRegistryAccess;
import software.bernie.geckolib.network.GeckoLibNetwork;
import io.github.adamraichu.sw_arcade.client.commands.WriteExampleWorldCommand;

public class StarWarsArcadeModeClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as
		// rendering.

		EntityRendererRegistry.register(EntityRegistry.CLONE_SQUAD_LEADER, DefaultCloneRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.DROID_SQUAD_LEADER, BlasterDroidSquadLeaderRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.BLUE_BLASTER_BOLT, BlueBlasterBoltRenderer::new);
		EntityRendererRegistry.register(EntityRegistry.BLUE_CANNON_BOLT, BlueCannonBoltRenderer::new);

		GeckoLibNetwork.registerClientReceiverPackets();

		ClientCommandRegistrationCallback.EVENT.register(StarWarsArcadeModeClient::registerCommands);
	}

	public static void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher,
			CommandRegistryAccess access) {
		WriteExampleWorldCommand.register(dispatcher);
	}
}