package io.github.adamraichu.sw_arcade.registry;

import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import io.github.adamraichu.sw_arcade.StarWarsArcadeMode;
import io.github.adamraichu.sw_arcade.entity.BlasterCloneSquadLeader;
import io.github.adamraichu.sw_arcade.entity.BlasterDroidSquadLeader;
import io.github.adamraichu.sw_arcade.entity.projectile.BlueBlasterBoltEntity;
import io.github.adamraichu.sw_arcade.entity.projectile.BlueCannonBoltEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class EntityRegistry {
    public static final EntityType<BlasterCloneSquadLeader> CLONE_SQUAD_LEADER = registerEntity(
            "blaster_clone_squad_leader", BlasterCloneSquadLeader::new, 0.6f, 1.8f);
    public static final EntityType<BlasterDroidSquadLeader> EVIL_CLONE = registerEntity(
            "blaster_droid_squad_leader", BlasterDroidSquadLeader::new, 0.6f, 1.8f);
    public static final EntityType<BlueBlasterBoltEntity> BLUE_BLASTER_BOLT = registerEntity(
            "blue_blaster_bolt", BlueBlasterBoltEntity::new, 0.125f, 0.125f);
    public static final EntityType<BlueCannonBoltEntity> BLUE_CANNON_BOLT = registerEntity("blue_cannon_bolt",
            BlueCannonBoltEntity::new, 0.25f, 0.25f);

    public static <T extends Entity> EntityType<T> registerEntity(String name, EntityType.EntityFactory<T> entity,
            float width, float height) {
        return Registry.register(net.minecraft.registry.Registries.ENTITY_TYPE,
                new Identifier(StarWarsArcadeMode.MOD_ID, name),
                FabricEntityTypeBuilder.create(SpawnGroup.MISC, entity)
                        .dimensions(EntityDimensions.changing(width, height))
                        .build());
    }
}
