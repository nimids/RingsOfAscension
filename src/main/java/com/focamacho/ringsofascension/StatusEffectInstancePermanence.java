package com.focamacho.ringsofascension;

public interface StatusEffectInstancePermanence{
    default void setPermanent(boolean permanent) {
    }
    default boolean isPermanent() {
        return false;
    }
}
