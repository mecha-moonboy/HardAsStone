package net.hard.as.stone.mixin;

import net.hard.as.stone.HardAsStoneConfig;
import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ToolMaterials.class)
public class ToolMaterialsMixin {

    @Shadow @Final @Mutable
    private float miningSpeed;

    @Inject(at = @At("RETURN"), method = "<init>")
    private void overrideVanillaMiningSpeeds(String string, int i, int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier repairIngredient, CallbackInfo ci){

        HardAsStoneConfig config = HardAsStoneConfig.readConfig();

        switch (string) {
            case "WOOD" -> this.miningSpeed = config.woodToolMiningSpeed;
            case "STONE" -> this.miningSpeed = config.stoneToolMiningSpeed;
            case "IRON" -> this.miningSpeed = config.ironToolMiningSpeed;
            case "GOLD" -> this.miningSpeed = config.goldToolMiningSpeed;
            case "DIAMOND" -> this.miningSpeed = config.diamondToolMiningSpeed;
            case "NETHERITE" -> this.miningSpeed = config.netheriteToolMiningSpeed;
        }

    }
}
