package com.focamacho.ringsofascension.mixin;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StatusEffectUtil.class)
public class StatusEffectUtilMixin{
    @Inject(at = @At("HEAD"), method = "durationToString", cancellable = true)
    private static void durationToString(StatusEffectInstance effect, float multiplier, CallbackInfoReturnable<String> cir) {
        if (effect.isPermanent()) {
            cir.setReturnValue("**:**");
        }
    }
}
