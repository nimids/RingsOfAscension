package com.focamacho.ringsofascension.item.rings;

import com.focamacho.ringsofascension.item.ItemRingBase;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemRingSponge extends ItemRingBase {

    public ItemRingSponge(String name, int tier, String tooltip, boolean enabled, String locations) {
        super(name, tier, tooltip, enabled, locations);
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.tick(stack, slot, entity);
        if(entity.world.isClient || entity.isSneaking()) return;

        if(entity instanceof PlayerEntity player) {
            World world = player.world;
            BlockPos entityPos = player.getBlockPos();
            int range = 3;

            for (BlockPos pos : BlockPos.iterate(entityPos.getX() - range, entityPos.getY() - range, entityPos.getZ() - range, entityPos.getX() + range, entityPos.getY() + range, entityPos.getZ() + range)) {
                BlockState state = world.getBlockState(pos);
                FluidState fluid = world.getFluidState(pos);
                Material material = state.getMaterial();

                if (fluid.isIn(FluidTags.WATER)) {
                    if (!(state.getBlock() instanceof FluidDrainable && ((FluidDrainable) state.getBlock()).tryDrainFluid(world, pos, state) != ItemStack.EMPTY)) {
                        if (state.getBlock() instanceof FluidBlock) {
                            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                        } else if (material == Material.UNDERWATER_PLANT || material == Material.REPLACEABLE_UNDERWATER_PLANT) {
                            BlockEntity tileentity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
                            Block.dropStacks(state, world, pos, tileentity);
                            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                        }
                    }
                }
            }
        }
    }

}
