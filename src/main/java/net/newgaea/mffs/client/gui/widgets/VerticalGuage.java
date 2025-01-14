package net.newgaea.mffs.client.gui.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IRenderable;
import net.minecraft.util.ResourceLocation;

public class VerticalGuage extends AbstractGui implements IRenderable {

    private ResourceLocation guageImage;
    private int max;
    private int min;
    private int current;

    private int xTexStart;
    private int yTexStart;
    private int xGuageStart;
    private int yGuageStart;
    private int texWidth;
    private int texHeight;

    protected int width;
    protected int height;
    public int x;
    public int y;


    public int getMax() {
        return max;
    }

    public VerticalGuage setMax(int max) {
        this.max = max;
        return this;
    }

    public int getMin() {
        return min;
    }

    public VerticalGuage setMin(int min) {
        this.min = min;
        return this;
    }

    public int getCurrent() {
        return current;
    }

    public VerticalGuage setCurrent(int current) {
        this.current = current;
        return this;
    }


    public float getPercent() {
        return ((current - min) * 100f) / (max - min);
    }

    public VerticalGuage(int xIn, int yIn, int xTexStart, int yTexStart, int xGuageStart, int yGuageStart, ResourceLocation guageImage) {
        this.x = xIn;
        this.y = yIn;
        this.guageImage = guageImage;
        this.xGuageStart = xGuageStart;
        this.yGuageStart = yGuageStart;
        this.xTexStart = xTexStart;
        this.yTexStart = yTexStart;
    }

    public VerticalGuage(int xIn, int yIn, int widthIn, int heightIn, int xTexStart, int yTexStart, int xGuageStart, int yGuageStart, int texWidthIn, int texHeightIn, ResourceLocation guageImage) {
        this.x = xIn;
        this.y = yIn;
        this.width = widthIn;
        this.height = heightIn;

        this.guageImage = guageImage;
        this.xGuageStart = xGuageStart;
        this.yGuageStart = yGuageStart;
        this.xTexStart = xTexStart;
        this.yTexStart = yTexStart;
        this.texHeight = texHeightIn;
        this.texWidth = texWidthIn;
        this.min = 0;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        float curPercent = getPercent();
        int bar2height = (int) (1f + ((curPercent / 100f) * (this.height - 2f)));
        int bar1height = this.height - bar2height;
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(this.guageImage);
        matrixStack.push();
        GlStateManager.disableDepthTest();
        blit(matrixStack,this.x, this.y, this.xTexStart, this.yTexStart, this.width, bar1height, 32, 64);
        blit(matrixStack,this.x, this.y + bar1height, this.xGuageStart, this.yGuageStart + bar1height, this.width, bar2height, 32, 64);
        GlStateManager.enableDepthTest();
        matrixStack.pop();
    }
}
