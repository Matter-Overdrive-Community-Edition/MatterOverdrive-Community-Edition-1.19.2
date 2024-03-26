package matteroverdrive.client.render.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EntityFakePlayer extends Player {
    public EntityFakePlayer(Level level, GameProfile gameProfile) {
        super(level, new BlockPos(0, 0, 0), 0, gameProfile, null);
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return false;
    }

    @Override
    public boolean canUseGameMasterBlocks() {
        return false;
    }

    @Override
    public boolean hasPermissions(int level) {
        return false;
    }
}