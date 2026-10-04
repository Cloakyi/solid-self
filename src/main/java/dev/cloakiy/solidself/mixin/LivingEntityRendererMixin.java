package dev.cloakiy.solidself.mixin;

import dev.cloakiy.solidself.SolidSelf;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Vanilla renders players with the translucent entity render type. With Iris that
 * draws them after the deferred passes, so shader effects that read the depth
 * buffer there (e.g. world outlines) skip the player and it can look see-through.
 * Swap it for the cutout type so the player is drawn with the other opaque entities.
 */
@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin {
    @Inject(method = "getRenderType", at = @At("RETURN"), cancellable = true)
    private void solidself$useCutout(LivingEntityRenderState state, boolean bodyVisible, boolean translucent,
                                            boolean glowing, CallbackInfoReturnable<RenderType> cir) {
        if (!SolidSelf.isEnabled() || !((Object) this instanceof AvatarRenderer<?>) || !bodyVisible || translucent) return;

        Identifier texture = ((LivingEntityRenderer<?, LivingEntityRenderState, ?>) (Object) this).getTextureLocation(state);
        if (cir.getReturnValue() == RenderTypes.entityTranslucent(texture)) {
            cir.setReturnValue(RenderTypes.entityCutout(texture));
        }
    }
}
