package de.javafunction.meta.gui.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;

import java.util.Objects;

public class PasswordFieldWidget extends TextFieldWidget {
    private static final ButtonTextures TEXTURES = new ButtonTextures(Identifier.of("widget/text_field"), Identifier.of("widget/text_field_highlighted"));
    public PasswordFieldWidget(TextRenderer textRenderer, int x, int y, int width, int height, Text text) {
        super(textRenderer, x, y, width, height, text);
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.isVisible()) {
            if (this.drawsBackground()) {
                Identifier identifier = TEXTURES.get(this.isNarratable(), this.isFocused());
                context.drawGuiTexture(identifier, this.getX(), this.getY(), this.getWidth(), this.getHeight());
            }

            // Replace each character with an asterisk (*) while rendering
            String maskedText = new String(new char[this.getText().length()]).replace('\0', '*');

            if (this.isFocused()) {
                if (this.getCursor() < this.getText().length()) {
                    // Calculate the visible text
                    String visibleText = this.getText().substring(this.getCursor());
                    String maskedVisibleText = new String(new char[visibleText.length()]).replace('\0', '*');
                    String combinedText = maskedText.substring(0, this.getCursor()) + "|" + maskedVisibleText;
                    context.drawText(MinecraftClient.getInstance().textRenderer, combinedText, this.getX() + 5, this.getY() + (this.height - 8) / 2, 0xFFFFFF, false);
                } else {
                    // Draw the cursor at the end
                    String textWithCursor = maskedText + "|";
                    context.drawText(MinecraftClient.getInstance().textRenderer, textWithCursor, this.getX() + 5, this.getY() + (this.height - 8) / 2, 0xFFFFFF, false);
                }
            } else {
                context.drawText(MinecraftClient.getInstance().textRenderer, maskedText, this.getX() + 5, this.getY() + (this.height - 8) / 2, 0xAAAAAA, false);
            }
        }
    }
}