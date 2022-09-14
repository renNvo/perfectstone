package me.rennvo.perfectstone.model.user;

import java.util.UUID;

public interface IUser {

    public UUID getUniqueId();

    public int getLevel();

    public void setLevel(int level);

    public double getExp();

    public void setExp(double exp);

    public double getNeed();

    public void setNeed(double need);

    public void addExp(double exp);

    public void addLevel(int level);

}
