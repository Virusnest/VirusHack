package dev.virusnest.virusclient.module.mods.world;

import dev.virusnest.virusclient.module.Module;
import dev.virusnest.virusclient.module.settings.NumberSetting;
import net.minecraft.block.AirBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class AutoFarm extends Module {
    NumberSetting range = new NumberSetting("Range",2, 10,4,1);
    public AutoFarm(){
        super("AutoFarm","Automaticly Farms crops",Category.WORLD);
        addSetting(range);
    }

    @Override
    public void onTick() {
        super.onTick();
        for (int y = -1;y<=0;y++){
            for (int x = -range.getValueInt();x<=range.getValueInt();x++){
                for (int z = -range.getValueInt() ;z<=range.getValueInt();z++){
                    tryPlant(mc.player.getBlockPos().add(x,y,z));
                }
            }
        }
    }
    void tryPlant(BlockPos pos){
        BlockState blockState = mc.world.getBlockState(pos);
        if(blockState.getBlock() instanceof FarmlandBlock){
            BlockState blockStateUp = mc.world.getBlockState(pos.up());
            if(blockStateUp.getBlock() instanceof AirBlock){
                if(tryUseSeed(pos,Hand.MAIN_HAND)||tryUseSeed(pos,Hand.OFF_HAND)){

                }
            }
        }
    }
    boolean tryUseSeed(BlockPos pos, Hand hand){
        Item item = mc.player.getStackInHand(hand).getItem();
        if(item== Items.WHEAT_SEEDS || item == Items.CARROT||item ==Items.POTATO
        ||item== Items.BEETROOT_SEEDS||item==Items.MELON_SEEDS||item== Items.PUMPKIN_SEEDS){
            Vec3d blockPos = new Vec3d(pos.getX(),pos.getY(), pos.getZ());
            BlockHitResult hit = new BlockHitResult(blockPos, Direction.UP,pos,false);
            mc.interactionManager.interactBlock(mc.player,hand,hit);
        }
        return false;
    }
}
