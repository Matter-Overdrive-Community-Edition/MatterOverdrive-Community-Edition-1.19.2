package matteroverdrive.core.entities.npc.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import matteroverdrive.core.entities.npc.api.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DialogMessage implements IDialogMessage, IDialogMessageSeedable {
    protected static Random random = new Random();
    protected String[] messages;
    protected String[] questions;
    protected boolean unlocalized;
    protected IDialogMessage parent;
    protected List<IDialogOption> options;
    @OnlyIn(Dist.CLIENT)
    protected IDialogShot[] shots;
    @OnlyIn(Dist.CLIENT)
    protected String holoIcon;
    protected long seed;

    public DialogMessage(JsonObject object) {
        init();
        this.unlocalized = MOJsonHelper.getBool(object, "unlocalized", false);
        if (object.has("message")) {
            JsonElement messageElement = object.get("message");
            if (messageElement.isJsonArray()) {
                messages = MOJsonHelper.getStringArray(object, "message");
            } else if (messageElement.isJsonPrimitive() && messageElement.getAsJsonPrimitive().isString()) {
                messages = new String[] { messageElement.getAsString() };
            }
        } else {
            throw new MORuntimeException(String.format("Cannot find Message for dialog"));
        }
        if (object.has("question")) {
            JsonElement questionElement = object.get("question");
            if (questionElement.isJsonArray()) {
                questions = MOJsonHelper.getStringArray(object, "message");
            } else if (questionElement.isJsonPrimitive() && questionElement.getAsJsonPrimitive().isString()) {
                questions = new String[] { questionElement.getAsString() };
            }
        }
    }

    public DialogMessage() {
        init();
    }

    public DialogMessage(String message) {
        this(message, message);
    }

    public DialogMessage(String message, String question) {
        this(message != null ? new String[] { message } : null, question != null ? new String[] { question } : null);
    }

    public DialogMessage(String[] messages, String[] questions) {
        this.messages = messages;
        this.questions = questions;
        init();
    }

    private void init() {
        options = new ArrayList<>();
    }

    @Override
    public IDialogMessage getParent(IDialogNpc npc, Player player) {
        return parent;
    }

    @Override
    public List<IDialogOption> getOptions(IDialogNpc npc, Player player) {
        return options;
    }

    @Override
    public String getMessageText(IDialogNpc npc, Player player) {
        if (messages != null && messages.length > 0) {
            int messageIndex = 0;
            if (messages.length > 1) {
                messageIndex = random.nextInt(messages.length);
            }
            return formatMessage(messages[messageIndex], npc, player);
        }
        return "";
    }

    @Override
    public String getQuestionText(IDialogNpc npc, Player player) {
        if (questions != null && questions.length > 0) {
            int questionIndex = 0;
            if (questions.length > 1) {
                questionIndex = random.nextInt(questions.length);
            }
            return formatQuestion(questions[questionIndex], npc, player);
        }
        return "";
    }

    @Override
    public void onOptionsInteract(IDialogNpc npc, Player player, int option) {
        if (option >= 0 && option < options.size()) {
            options.get(option).onInteract(npc, player);
        }
    }

    @Override
    public void onInteract(IDialogNpc npc, Player player) {
        if (npc != null && player != null) {
            if (player.level.isClientSide) {
                if (!MinecraftForge.EVENT_BUS.post(new MOEventDialogInteract(player, npc, this, Dist.CLIENT))) {
                    setAsGuiActiveMessage(npc, player);
                }
            } else {
                if (!MinecraftForge.EVENT_BUS.post(new MOEventDialogInteract(player, npc, this, Dist.DEDICATED_SERVER))) {
                    npc.onPlayerInteract(player, this);
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    protected void setAsGuiActiveMessage(IDialogNpc npc, Player player) {
        Screen currentScreen = Minecraft.getInstance().screen;

        if (currentScreen instanceof GuiDialog) {
            ((GuiDialog) currentScreen).setCurrentMessage(this);
        }
    }

    @Override
    public boolean canInteract(IDialogNpc npc, Player player) {
        return true;
    }

    @Override
    public boolean isVisible(IDialogNpc npc, Player player) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public IDialogShot[] getShots(IDialogNpc npc, Player player) {
        return shots;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getHoloIcon(IDialogNpc npc, Player player) {
        return holoIcon;
    }

    @Override
    public boolean equalsOption(IDialogOption other) {
        return this.equals(other);
    }

    @OnlyIn(Dist.CLIENT)
    public void setShots(IDialogShot... shot) {
        this.shots = shot;
    }

    public void setParent(IDialogMessage parent) {
        this.parent = parent;
    }

    public void addOption(IDialogOption message) {
        this.options.add(message);
    }

    public IDialogOption getOption(int id) {
        return this.options.get(id);
    }

    public List<IDialogOption> getOptions() {
        return options;
    }

    @OnlyIn(Dist.CLIENT)
    public DialogMessage setHoloIcon(String holoIcon) {
        this.holoIcon = holoIcon;
        return this;
    }

    protected String formatMessage(String text, IDialogNpc npc, Player player) {
        if (text != null) {
            return String.format(unlocalized ? MOStringHelper.translateToLocal(text) : text,
                    player.getDisplayName().getVisualOrderText(), npc.getEntity().getDisplayName().getVisualOrderText());
        }
        return null;
    }

    protected String formatQuestion(String text, IDialogNpc npc, Player player) {
        if (text != null) {
            return String.format(unlocalized ? MOStringHelper.translateToLocal(text) : text,
                    player.getDisplayName().getVisualOrderText(), npc.getEntity().getDisplayName().getVisualOrderText());
        }
        return null;
    }

    public DialogMessage setUnlocalized(boolean unlocalized) {
        this.unlocalized = unlocalized;
        return this;
    }

    @Override
    public void setSeed(long seed) {
        this.seed = seed;
        random.setSeed(seed);
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }
