package matteroverdrive.core.entities.npc.api;

import matteroverdrive.core.entities.npc.dialog.DialogMessage;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

/**
 * Created by Simeon on 8/9/2015. Used by Entities that provide Dialog Messages
 */
public interface IDialogNpc {
    /**
     * Get the message that is the root of the conversation based on the player that
     * starts the conversation.
     *
     * @param player The player that starts the conversation.
     * @return The Root message.
     */
    IDialogMessage getStartDialogMessage(Player player);

    /**
     * Gets the current active Dialog Player
     *
     * @return The Dialog Player
     */
    Player getDialogPlayer();

    /**
     * Sets the current Dialog Player the NPC is interacting with.
     *
     * @param player The Player
     */
    void setDialogPlayer(Player player);

    /**
     * Can the NPC talk to the player.
     *
     * @param player The Player.
     * @return Can talk to player.
     */
    boolean canTalkTo(Player player);

    /**
     * Get The Entity that the NPC represents. Used to enable separation of NPC from
     * Entity.
     *
     * @return
     */
    Player getEntity();

    /**
     * Called when the player interacts with the NPC
     *
     * @param player        The Player
     * @param dialogMessage The dialog message the player interacted with.
     *
     */
    void onPlayerInteract(Player player, DialogMessage dialogMessage);
}