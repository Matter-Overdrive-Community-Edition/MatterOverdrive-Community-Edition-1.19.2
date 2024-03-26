package matteroverdrive.core.entities.npc.api;

import net.minecraft.world.entity.player.Player;
public interface IDialogQuestGiver {
    void giveQuest(IDialogMessage message, QuestStack questStack, Player entityPlayer);
}
