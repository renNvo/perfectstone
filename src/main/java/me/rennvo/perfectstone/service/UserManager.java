package me.rennvo.perfectstone.service;

import me.rennvo.perfectstone.model.user.IUser;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {

    private final Map<UUID, IUser> userMap = new ConcurrentHashMap<>();

    public IUser get(UUID uniqueId) {
        return this.userMap.get(uniqueId);
    }

    public Map<UUID, IUser> getUserMap() {
        return userMap;
    }
}
