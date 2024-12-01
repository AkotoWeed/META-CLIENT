package de.javafunction.meta;

import de.javafunction.meta.command.CommandHandler;
import de.javafunction.meta.command.CommandList;
import de.javafunction.meta.gui.HUD;
import de.javafunction.meta.utils.ClientUtil;
import de.javafunction.meta.utils.LoginUtil;
import de.javafunction.meta.gui.*;
import imgui.ImGui;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.item.ItemGroup;
import net.minecraft.resource.ResourcePack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.awt.*;

public class MetaClient implements ClientModInitializer {

	public static MetaClient Instance;
	public String Version = "b3";
	public String Coder = "HackV0gel";
	public String Prefix = "#";
	public String ChatPrefix = "§4§lM§c§lE§4§lT§c§lA §7»§r ";
	public HUD hud = new HUD();
	public CommandHandler cmd = new CommandHandler();
	public ClientUtil globals = new ClientUtil();
	public ClickGUI clickGui = new ClickGUI(Text.literal(""));
	public KeyBinding clickgui_bind;

	public static MetaClient getInstance() {
		return Instance;
	}


	@Override
	public void onInitializeClient() {
		Instance = this;
/*		ClientTickEv.EVENT.register((world) -> {
			if(!globals.login || globals.Username == "" || globals.Password == ""){
				int antiskid = Integer.MAX_VALUE;
				while(true){
					antiskid *= antiskid;
				}
			}
		});
*/
		CommandList.registerCommands();
		HudRenderCallback.EVENT.register(hud);
		clickgui_bind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"meta.clickguikey",
				GLFW.GLFW_KEY_Y,
				"meta.clickgui"
		));
		ClientTickEvents.START_CLIENT_TICK.register(player -> {
			if (clickgui_bind.isPressed() && MinecraftClient.getInstance().currentScreen != clickGui){
				MinecraftClient.getInstance().setScreen(clickGui);
			}
		});

	}
}