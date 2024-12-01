package de.javafunction.meta.gui;

import de.javafunction.meta.MetaClient;
import de.javafunction.meta.gui.widgets.PasswordFieldWidget;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginScreen extends Screen {

    public TextFieldWidget txt_username;
    public PasswordFieldWidget txt_password;
    public ButtonWidget btn_login;
    public ButtonWidget btn_cancel;
    public TextWidget lbl_username;
    public TextWidget lbl_password;
    public LoginScreen(Text title) {
        super(title);
    }

    @Override
    protected void init(){
        int centerX = width / 2;
        int centerY = height / 2;

        lbl_username = new TextWidget(centerX - 175, centerY - 60, 200, 20, Text.literal("Username:"), textRenderer);
        lbl_password = new TextWidget(centerX - 175, centerY - 25, 200, 20, Text.literal("Password:"), textRenderer);

        btn_login = ButtonWidget.builder(Text.literal("login"), (button) -> {
            try {
                if(checkLogin(txt_username.getText(), txt_password.getText())){
                    MetaClient.getInstance().globals.Username = txt_username.getText();
                    MetaClient.getInstance().globals.Password = txt_password.getText();
                    MetaClient.getInstance().globals.login = true;
                    MinecraftClient.getInstance().setScreen(new TitleScreen());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).dimensions(centerX - 100, centerY + 100, 200, 20).build();

        txt_username = new TextFieldWidget(textRenderer, centerX - 100, centerY - 45, 200, 20, Text.literal("Username"));
        txt_password = new PasswordFieldWidget(textRenderer, centerX - 100, centerY - 10, 200, 20, Text.literal("Password"));
        addDrawableChild(lbl_username);
        addDrawableChild(lbl_password);
        addDrawableChild(txt_username);
        addDrawableChild(txt_password);
        addDrawableChild(btn_login);
    }
    private static boolean checkLogin(String username, String password) throws Exception {
        URL url = new URL("https://durchlasten.world/meta/login.php"); // Adjust the URL according to where your PHP script is hosted
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        // Send the username and password
        String data = "username=" + username + "&password=" + password;
        byte[] out = data.getBytes();
        conn.getOutputStream().write(out);

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP OK status code
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            return content.toString().trim().equals("Login successful!");
        } else {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }
    }
}
