package de.javafunction.meta.mixin.client;

import de.javafunction.meta.MetaClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    @Inject(method = "sendMessage", at = @At("HEAD"), cancellable = true)
    public void onSendMessage(String message, boolean addToHistory, CallbackInfo ci) {  if (message.startsWith(MetaClient.getInstance().Prefix)) {
            List<String> args = new ArrayList<String>();
            for(int i = 1; i < message.split(" ").length; i++)
                args.add(message.split(" ")[i]);
            MetaClient.getInstance().cmd.onCommand(message.split(" ")[0], args);
            ci.cancel();
        }
    }
}
