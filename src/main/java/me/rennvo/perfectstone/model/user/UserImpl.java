package me.rennvo.perfectstone.model.user;

import java.util.UUID;

public class UserImpl implements IUser {

    private final UUID uniqueId;

    private int    level;
    private double exp;
    private double need;

    public UserImpl(UUID uniqueId, int level, double exp, double need) {
        this.uniqueId = uniqueId;
        this.level    = level;
        this.exp      = exp;
        this.need     = need;
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public double getExp() {
        return exp;
    }

    @Override
    public void setExp(double exp) {
        this.exp = exp;
    }

    @Override
    public double getNeed() {
        return need;
    }

    @Override
    public void setNeed(double need) {
        this.need = need;
    }

    @Override
    public void addExp(double exp) {
        this.exp += exp;
    }

    @Override
    public void addLevel(int level) {
        this.level += level;
    }
}
