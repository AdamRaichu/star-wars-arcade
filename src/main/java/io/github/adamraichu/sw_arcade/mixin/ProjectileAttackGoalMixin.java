package io.github.adamraichu.sw_arcade.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.github.adamraichu.sw_arcade.entity.helper.TeamAwareEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;

@Mixin(ProjectileAttackGoal.class)
public class ProjectileAttackGoalMixin {
  @Shadow
  RangedAttackMob owner;

  @Inject(at = @At("RETURN"), method = "canStart()Z", cancellable = true)
  private void checkHasPassengers(CallbackInfoReturnable<Boolean> ci) {
    if (!(owner instanceof TeamAwareEntity)) {
      // It's not one of my mobs, so ignore it.
      return;
    }
    if (((TeamAwareEntity<?>) owner).hasPassengers()) {
      // Is controlled by a player, so override default behavior.
      ci.setReturnValue(false);
    }
  }
}
