package me.kikugie.compactsb.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.text.NumberFormat;
import java.util.Locale;

import static me.kikugie.compactsb.ScoreboardMod.revealKey;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    private static final NumberFormat formatter = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);

    static {
        formatter.setMaximumFractionDigits(1);
    }

    @Redirect(method = "renderScoreboardSidebar", at = @At(value = "INVOKE", target = "Ljava/lang/Integer;toString(I)Ljava/lang/String;"))
    private String getCompactNumber(int value) {
        if (revealKey.isPressed()) return Integer.toString(value);
        return formatter.format(value);
    }

    @ModifyVariable(method = "renderScoreboardSidebar", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I"), index = 22)
    private String modifyScore(String score) {
        if (revealKey.isPressed()) return score;
        try {
            return Formatting.RED + formatter.format(Integer.parseInt(Formatting.strip(score)));
        } catch (NumberFormatException e) {
            return score;
        }
    }
}
