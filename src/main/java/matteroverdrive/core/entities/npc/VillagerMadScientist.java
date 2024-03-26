/*
   Uncomment code when dialog and quests are implemented
 */

package matteroverdrive.core.entities.npc;

import matteroverdrive.core.entities.npc.api.IDialogMessage;
import matteroverdrive.core.entities.npc.api.IDialogRegistry;
import matteroverdrive.core.entities.npc.dialog.DialogMessage;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;

public class VillagerMadScientist extends Villager {
    private static final EntityDataAccessor<Boolean> VARIANT = SynchedEntityData.defineId(VillagerMadScientist.class,
            EntityDataSerializers.BOOLEAN);
    public static DialogMessage cocktailOfAscensionComplete;
    private static DialogMessage convertMe;
    private static DialogMessage canYouConvert;
    private static DialogMessage whatDidYouDo;
    private static DialogMessage cocktailOfAscension;
    private Player dialogPlayer;
    private IDialogMessage startMessage;

    public VillagerMadScientist(EntityType<? extends VillagerMadScientist> entityType, Level level) {
        super(entityType, level);

        //this.goalSelector.addGoal(1, new DialogGoal(this));
        //this.goalSelector.addGoal(1, new DialogLookAtPlayerGoal(this));
    }

    public static void registerDialogMessages(IDialogRegistry registry, Dist side) {

        convertMe = new DialogMessageQuestOnObjectivesCompleted(null, "dialog.mad_scientist.convert.question",
                new QuestStack(MatterOverdriveQuests.punyHumans), new int[] { 0 }).setUnlocalized(true);
        registry.registerMessage(convertMe);

        canYouConvert = new DialogMessageQuestGive("dialog.mad_scientist.requirements.line",
                "dialog.mad_scientist.requirements.question", new QuestStack(MatterOverdriveQuests.punyHumans));
        registry.registerMessage(canYouConvert);
        canYouConvert.addOption(convertMe);
        canYouConvert.addOption(MatterOverdriveDialogs.backHomeMessage);
        canYouConvert.setUnlocalized(true);

        DialogMessage undo = new DialogMessage("dialog.mad_scientist.undo.line", "dialog.mad_scientist.undo.question");
        registry.registerMessage(undo);
        undo.setUnlocalized(true);
        undo.addOption(MatterOverdriveDialogs.trade);
        undo.addOption(MatterOverdriveDialogs.backHomeMessage);

        whatDidYouDo = new DialogMessageAndroidOnly("dialog.mad_scientist.whatDidYouDo.line",
                "dialog.mad_scientist.whatDidYouDo.question");
        registry.registerMessage(whatDidYouDo);
        whatDidYouDo.setUnlocalized(true);
        whatDidYouDo.addOption(undo);
        whatDidYouDo.addOption(MatterOverdriveDialogs.backHomeMessage);

        DialogMessage acceptCocktail = new DialogMessageQuestGive(null,
                "dialog.mad_scientist.junkie.cocktail_quest.question.accept",
                new QuestStack(MatterOverdriveQuests.cocktailOfAscension)).setReturnToMain(true).setUnlocalized(true);
        registry.registerMessage(acceptCocktail);
        DialogMessage declineCocktail = new DialogMessageBackToMain(null,
                "dialog.mad_scientist.junkie.cocktail_quest.question.decline").setUnlocalized(true);
        registry.registerMessage(declineCocktail);
        DialogMessage[] cocktailQuest = MatterOverdrive.DIALOG_FACTORY.constructMultipleLineDialog(
                DialogMessageQuestStart.class, "dialog.mad_scientist.junkie.cocktail_quest", 8, ". . . . . .");
        ((DialogMessageQuestStart) cocktailQuest[0])
                .setQuest(new QuestStack(MatterOverdriveQuests.cocktailOfAscension));
        cocktailOfAscension = cocktailQuest[0];
        DialogMessage lastLine = cocktailQuest[cocktailQuest.length - 1];
        lastLine.addOption(acceptCocktail);
        lastLine.addOption(declineCocktail);

        cocktailOfAscensionComplete = new DialogMessageQuestOnObjectivesCompleted(
                "dialog.mad_scientist.junkie.cocktail_quest.line",
                "dialog.mad_scientist.junkie.cocktail_quest.complete.question",
                new QuestStack(MatterOverdriveQuests.cocktailOfAscension), new int[] { 0, 1, 2 }).setUnlocalized(true);
        DialogMessage areYouOk = new DialogMessageQuit(null,
                "dialog.mad_scientist.junkie.cocktail_quest.are_you_ok.question").setUnlocalized(true);
        cocktailOfAscensionComplete.addOption(areYouOk);

        if (side == Side.CLIENT) {
            canYouConvert.setShots(DialogShot.closeUp);
            undo.setShots(DialogShot.closeUp);
            whatDidYouDo.setShots(DialogShot.fromBehindLeftClose);
            for (DialogMessage aCocktailQuest : cocktailQuest) {
                MatterOverdrive.DIALOG_FACTORY.addRandomShots(aCocktailQuest);
            }
        }
    }

}
