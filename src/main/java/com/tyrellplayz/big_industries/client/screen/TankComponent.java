package com.tyrellplayz.big_industries.client.screen;

import com.tyrellplayz.zlib.client.screen.component.Component;
import com.tyrellplayz.zlib.util.helpers.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class TankComponent extends Component<TankComponent> {

    private int maxAmount;
    private int stored;

    private ResourceLocation texture;
    private int u;
    private int v;

    public TankComponent(int left, int top, int height, ResourceLocation texture) {
        this(left, top, height,texture,0,16);
    }

    public TankComponent(int left, int top, int height, ResourceLocation texture,int u, int v) {
        super(left, top, 16, height);
        this.texture = texture;
        this.u = u;
        this.v = v;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        Minecraft.getInstance().getTextureManager().bindTexture(COMPONENT_TEXTURES);
        int k = getTankLeftScaled();
        int height = k;
        if(height != 0) height= k+1;
        RenderHelper.drawRectWithTexture(getXPos(), getYPos()+getHeight()-k, u, v, getWidth(), height, getWidth(), height);
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public void setStored(int stored) {
        this.stored = stored;
    }

    public int getTankLeftScaled() {
        int i = maxAmount;
        if (i == 0) {
            return 0;
        }
        return stored * getHeight() / i;
    }

}
