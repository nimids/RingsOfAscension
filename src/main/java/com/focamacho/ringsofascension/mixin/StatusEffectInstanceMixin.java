package com.focamacho.ringsofascension.mixin;

import com.focamacho.ringsofascension.StatusEffectInstancePermanence;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstanceMixin implements StatusEffectInstancePermanence{
    private boolean permanent;
    @Override
    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }
    @Override
    public boolean isPermanent() {
        return this.permanent;
    }
}
