package com.tiviacz.travelersbackpack.client.screen.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tiviacz.travelersbackpack.client.screen.TravelersBackpackScreen;
import com.tiviacz.travelersbackpack.config.TravelersBackpackConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;

public class SettingsWidget extends WidgetBase
{
    public SettingsWidget(TravelersBackpackScreen screen, int x, int y, int width, int height)
    {
        super(screen, x, y, width, height);
        showTooltip = true;
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, Minecraft minecraft, int mouseX, int mouseY)
    {
        minecraft.getTextureManager().bind(TravelersBackpackScreen.SETTINGS_TRAVELERS_BACKPACK);

        if(!this.isWidgetActive)
        {
            blit(matrixStack, x, y, 0, 0, width, height);
        }
        else
        {
            blit(matrixStack, x, y, 0, 19, width, height);
        }
    }

    @Override
    public void renderTooltip(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        if(isMouseOver(mouseX, mouseY) && showTooltip)
        {
            if(isWidgetActive())
            {
                screen.renderTooltip(matrixStack, new TranslationTextComponent("screen.travelersbackpack.settings_back"), mouseX, mouseY);
            }
            else
            {
                screen.renderTooltip(matrixStack, new TranslationTextComponent("screen.travelersbackpack.settings"), mouseX, mouseY);
            }
        }
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton)
    {
        if(isMouseOver(pMouseX, pMouseY) && !this.isWidgetActive)
        {
            this.isWidgetActive = true;
            if(!TravelersBackpackConfig.disableCrafting) this.screen.craftingWidget.setVisible(false);
            this.screen.children().stream().filter(w -> w instanceof WidgetBase).filter(w -> ((WidgetBase) w).isSettingsChild()).forEach(w -> ((WidgetBase) w).setVisible(true));
            this.screen.playUIClickSound();
            return true;
        }
        else if(isMouseOver(pMouseX, pMouseY))
        {
            this.isWidgetActive = false;
            if(!TravelersBackpackConfig.disableCrafting) this.screen.craftingWidget.setVisible(true);
            this.screen.children().stream().filter(w -> w instanceof WidgetBase).filter(w -> ((WidgetBase) w).isSettingsChild()).forEach(w -> ((WidgetBase) w).setVisible(false));
            this.screen.playUIClickSound();
            return true;
        }
        return false;
    }

    @Override
    public boolean isSettingsChild()
    {
        return false;
    }
}