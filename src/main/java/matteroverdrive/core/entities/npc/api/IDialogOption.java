package matteroverdrive.core.entities.npc.api;

import net.minecraft.world.entity.player.Player;

public interface IDialogOption {
    /**
     * Called when the option is chosen from all the option of the patten from
     * {@link IDialogMessage#getOptions(IDialogNpc, Player)}.
     * Not to be confused with
     * {@link IDialogMessage#onOptionsInteract(IDialogNpc, Player, int)}
     * which is called on the parent. This method is called after
     * {@link IDialogMessage#onOptionsInteract(IDialogNpc, Player, int)}.
     *
     * @param npc
     * @param player
     */
    void onInteract(IDialogNpc npc, Player player);

    /**
     * Used to display the question (option) message. This is used when the parent
     * message is active and shows all children from
     * {@link IDialogMessage#getOptions(IDialogNpc, Player)}.
     *
     * @param npc    The NPC Entity.
     * @param player The Player
     * @return The question (option) text.
     */
    String getQuestionText(IDialogNpc npc, Player player);

    /**
     * Can the player interact with this option. For messages means if it can be
     * chosen as the next active message from the parent's options.
     *
     * @param npc    The NPC Entity
     * @param player The Player
     * @return Can the message be clicked (chosen) as an option.
     */
    boolean canInteract(IDialogNpc npc, Player player);

    /**
     * Is the option visible.
     *
     * @param npc    The NPC entity.
     * @param player The Player.
     * @return Is the message visible as an option.
     */
    boolean isVisible(IDialogNpc npc, Player player);

    /**
     * @param npc    the npc
     * @param player the player
     * @return The holo icon, {@code null} if there isn't one
     */
    String getHoloIcon(IDialogNpc npc, Player player);

    boolean equalsOption(IDialogOption other);
}