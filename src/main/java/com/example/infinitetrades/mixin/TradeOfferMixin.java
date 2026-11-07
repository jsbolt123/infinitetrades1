package com.example.infinitetrades.mixin;

import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TradeOffer.class)
public abstract class TradeOfferMixin {

    // Using a trade never increases its use counter, so it can't run out.
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void infinitetrades$neverCountUse(CallbackInfo ci) {
        ci.cancel();
    }

    // Trades that were already used up before the mod was installed count as available.
    @Inject(method = "isDisabled", at = @At("HEAD"), cancellable = true)
    private void infinitetrades$neverDisabled(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
