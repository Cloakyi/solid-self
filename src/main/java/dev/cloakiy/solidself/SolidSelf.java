package dev.cloakiy.solidself;

import com.mojang.blaze3d.platform.InputConstants;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolidSelf implements ClientModInitializer {
    public static final String MOD_ID = "solidself";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final Path CONFIG = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID + ".properties");

    private static boolean enabled = true;

    public static boolean isEnabled() {
        return enabled;
    }

    @Override
    public void onInitializeClient() {
        load();

        KeyMapping.Category category = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, MOD_ID));
        // Unbound by default; players pick their own key in Options > Controls > Key Binds.
        KeyMapping toggleKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping("key.solidself.toggle", InputConstants.Type.KEYSYM, InputConstants.UNKNOWN.getValue(), category));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.consumeClick()) {
                enabled = !enabled;
                save();
                if (client.player != null) {
                    client.player.sendOverlayMessage(enabled
                            ? Component.translatable("message.solidself.enabled").withStyle(ChatFormatting.GREEN)
                            : Component.translatable("message.solidself.disabled").withStyle(ChatFormatting.RED));
                }
            }
        });
    }

    private static void load() {
        if (!Files.exists(CONFIG)) return;
        Properties props = new Properties();
        try (Reader reader = Files.newBufferedReader(CONFIG)) {
            props.load(reader);
            enabled = Boolean.parseBoolean(props.getProperty("enabled", "true"));
        } catch (IOException e) {
            LOGGER.warn("Could not read {}", CONFIG, e);
        }
    }

    private static void save() {
        Properties props = new Properties();
        props.setProperty("enabled", Boolean.toString(enabled));
        try (Writer writer = Files.newBufferedWriter(CONFIG)) {
            props.store(writer, "Solid Self");
        } catch (IOException e) {
            LOGGER.warn("Could not write {}", CONFIG, e);
        }
    }
}
