// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class custom_model extends EntityModel<Entity> {
	private final ModelRenderer bb_main;

	public custom_model() {
		textureWidth = 16;
		textureHeight = 16;

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(0, 0).addBox(-2.0F, -10.0F, -8.0F, 4.0F, 4.0F, 16.0F, 0.0F, false);
		bb_main.setTextureOffset(-64, 11).addBox(-40.0F, -9.0F, -1.0F, 38.0F, 2.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(-64, 1).addBox(2.0F, -9.0F, -1.0F, 38.0F, 2.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(2, -24).addBox(-1.0F, -48.0F, -1.0F, 2.0F, 38.0F, 2.0F, 0.0F, false);
		bb_main.setTextureOffset(2, -24).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 38.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}