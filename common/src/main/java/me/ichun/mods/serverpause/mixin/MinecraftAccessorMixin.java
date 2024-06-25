package me.ichun.mods.serverpause.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Minecraft.class)
public interface MinecraftAccessorMixin
{
    @Accessor
    boolean getPause();

    @Accessor
    void setPause(boolean b);
}
