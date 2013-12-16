// Date: 12/16/2013 12:16:36 AM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package net.minecraft.src;

public class ModelCombatDrone extends ModelBase
{
  //fields
    ModelRenderer body;
    ModelRenderer hover;
    ModelRenderer body2;
    ModelRenderer hood;
    ModelRenderer gunMount;
    ModelRenderer cannon;
    ModelRenderer cannonBarrel;
  
  public ModelCombatDrone()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      body = new ModelRenderer(this, 0, 0);
      body.addBox(-8F, 0F, -8F, 16, 2, 16);
      body.setRotationPoint(0F, 16F, 0F);
      body.setTextureSize(64, 32);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      hover = new ModelRenderer(this, 0, 0);
      hover.addBox(-6F, 0F, -7F, 12, 2, 14);
      hover.setRotationPoint(0F, 18F, 0F);
      hover.setTextureSize(64, 32);
      hover.mirror = true;
      setRotation(hover, 0F, 0F, 0F);
      body2 = new ModelRenderer(this, 0, 0);
      body2.addBox(-6F, 0F, -4F, 12, 4, 12);
      body2.setRotationPoint(0F, 12F, 0F);
      body2.setTextureSize(64, 32);
      body2.mirror = true;
      setRotation(body2, 0F, 0F, 0F);
      hood = new ModelRenderer(this, 0, 0);
      hood.addBox(-6F, -3F, -3F, 12, 6, 3);
      hood.setRotationPoint(0F, 16F, -3F);
      hood.setTextureSize(64, 32);
      hood.mirror = true;
      setRotation(hood, -0.6108652F, 0F, 0F);
      gunMount = new ModelRenderer(this, 0, 0);
      gunMount.addBox(-2F, -1F, -2F, 4, 3, 4);
      gunMount.setRotationPoint(0F, 10F, 2F);
      gunMount.setTextureSize(64, 32);
      gunMount.mirror = true;
      setRotation(gunMount, 0F, 0F, 0F);
      cannon = new ModelRenderer(this, 0, 0);
      cannon.addBox(-2.5F, -2.5F, -5.5F, 5, 5, 10);
      cannon.setRotationPoint(0F, 8.5F, 2F);
      cannon.setTextureSize(64, 32);
      cannon.mirror = true;
      setRotation(cannon, 0F, 0F, 0F);
      cannonBarrel = new ModelRenderer(this, 0, 0);
      cannonBarrel.addBox(-2F, -2F, -9.5F, 4, 4, 7);
      cannonBarrel.setRotationPoint(0F, 8.5F, 2F);
      cannonBarrel.setTextureSize(64, 32);
      cannonBarrel.mirror = true;
      setRotation(cannonBarrel, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    body.render(f5);
    hover.render(f5);
    body2.render(f5);
    hood.render(f5);
    gunMount.render(f5);
    cannon.render(f5);
    cannonBarrel.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5);
  }

}
