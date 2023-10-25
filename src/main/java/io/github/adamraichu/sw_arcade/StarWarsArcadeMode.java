package io.github.adamraichu.sw_arcade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.brigadier.CommandDispatcher;

import io.github.adamraichu.sw_arcade.commands.ConfigurationCommands;
import io.github.adamraichu.sw_arcade.config.JsonGameConfig;
import io.github.adamraichu.sw_arcade.registry.EntityRegistry;
import io.github.adamraichu.sw_arcade.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import software.bernie.geckolib.GeckoLib;

public class StarWarsArcadeMode implements ModInitializer {
	public static final String MOD_ID = "sw_arcade";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		JsonGameConfig.GameConfig config = JsonGameConfig.getConfig();
		LOGGER.info("Config version: " + config.$VERSION);
		new EntityRegistry();
		new ItemRegistry();
		FabricDefaultAttributeRegistry.register(EntityRegistry.CLONE_SQUAD_LEADER, getGenericAttributes());
		FabricDefaultAttributeRegistry.register(EntityRegistry.EVIL_CLONE, getGenericAttributes());
		GeckoLib.initialize();
		CommandRegistrationCallback.EVENT.register(StarWarsArcadeMode::registerCommands);
	}

	private static DefaultAttributeContainer.Builder getGenericAttributes() {
		return PathAwareEntity.createLivingAttributes()
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5)
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D)
				.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.1);
	}

	public static void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher,
			CommandRegistryAccess access,
			RegistrationEnvironment evn) {
		ConfigurationCommands.register(dispatcher);
	}
}