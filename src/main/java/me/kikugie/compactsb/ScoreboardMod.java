package me.kikugie.compactsb;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ScoreboardMod implements ModInitializer {
    public static KeyBinding revealKey;

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        revealKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.compactsb.reveal", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_F6, // The keycode of the key
                "category.compactsb.title" // The translation key of the keybinding's category.
        ));
    }
}
