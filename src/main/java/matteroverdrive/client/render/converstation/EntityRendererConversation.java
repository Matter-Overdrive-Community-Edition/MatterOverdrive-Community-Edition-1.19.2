package matteroverdrive.client.render.converstation;

import com.mojang.authlib.GameProfile;
import matteroverdrive.client.render.entity.EntityFakePlayer;
import matteroverdrive.core.entities.npc.api.IDialogMessage;
import matteroverdrive.core.entities.npc.api.IDialogShot;
import net.minecraft.client.GuiMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class EntityRendererConversation extends EntityRenderer {
    final Minecraft mc;
    final Random random;
    EntityFakePlayer fakePlayer;

    public EntityRendererConversation(EntityRendererProvider.Context context, Minecraft minecraft) {
        super(context);
        this.mc = minecraft;
        random = new Random();
    }

    /*
    TODO: Missing GuiDialog
    public void renderWorld(float ticks, long time) {
        if (fakePlayer == null) {
            fakePlayer = new EntityFakePlayer(mc.level, new GameProfile(null, "FakePlayer"));
        }

        boolean lastHideGui = mc.options.hideGui;
        Entity lastRenderViewEntity = mc.gameRenderer.getMainCamera().getEntity();

        if (mc.screen instanceof GuiDialog) {
            mc.options.hideGui = true;
            mc.setCameraEntity(fakePlayer);
            GuiDialog guiDialog = (GuiDialog) mc.screen;
            IDialogMessage message = guiDialog.getCurrentMessage();
            if (message != null) {
                random.setSeed(guiDialog.getSeed());
                IDialogShot[] shots = message.getShots(guiDialog.getNpc().getEntity(), mc.player);
                if (shots != null && shots.length > 0) {
                    shots[random.nextInt(shots.length)].positionCamera(guiDialog.getNpc().getEntity(), mc.player, ticks,
                            this);
                } else {
                    DialogShot.WIDE_NORMAL.positionCamera(guiDialog.getNpc().getEntity(), mc.player, ticks, this);
                }
            }
            updateFakePlayerPositions();
        }
        super.renderWorld(ticks, time);
        mc.setCameraEntity(lastRenderViewEntity);
        mc.options.hideGui = lastHideGui;
    }
     */

    public Vec3 getLook(Mob active, Mob other, float ticks) {
        return getPosition(active, ticks).subtract(getPosition(other, ticks));
    }

    public Vec3 getPosition(Mob entityLivingBase, float ticks) {
        Vec3 pos = new Vec3(entityLivingBase.getX(), entityLivingBase.getY(), entityLivingBase.getZ());
        return pos;
    }

    public void rotateCameraYawTo(Vec3 dir, float offset) {
        double yaw = Math.acos(new Vec3(-1, 0, 0).dot(dir));
        Vec3 cross = new Vec3(-1, 0, 0).cross(dir);
        Vec3 up = new Vec3(0, -1, 0);
        if (up.dot(cross) < 0) {
            yaw = -yaw;
        }
        yaw = Math.PI + yaw;
        setCameraYaw((float) Math.toDegrees(yaw) + offset);
    }

    private void rotatePitchToDir(Vec3 dir, float yaw, float offset) {
        setCameraPitch((float) Math.asin(Math.sqrt(dir.x * dir.x + dir.y * dir.y) / dir.z) + offset);
    }

    public void setCameraPosition(float x, float y, float z) {
        fakePlayer.setPos(x, y, z);
    }

    public void setCameraPosition(Vec3 position) {
        fakePlayer.setPos(position.x, position.y, position.z);
    }

    public void setCameraYaw(float angle) {
        fakePlayer.setYRot(angle);
    }

    public void setCameraPitch(float angle) {
        fakePlayer.setXRot(angle);
    }

    /*
    TODO: Missing MOMathHelper
    public void setCameraPositionSmooth(float angle, float speed) {
        fakePlayer.setXRot(MOMathHelper.lerp(fakePlayer.getXRot(), angle, speed));
    }
     */

    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return null;
    }

    private void updateFakePlayerPositions() {
        fakePlayer.xOld = fakePlayer.xo = fakePlayer.getX();
        fakePlayer.yOld = fakePlayer.yo = fakePlayer.getY();
        fakePlayer.zOld = fakePlayer.zo = fakePlayer.getZ();

        fakePlayer.yRotO = fakePlayer.getYRot();
        fakePlayer.xRotO = fakePlayer.getXRot();
    }
}
