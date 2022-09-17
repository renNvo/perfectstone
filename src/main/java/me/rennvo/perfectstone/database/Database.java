package me.rennvo.perfectstone.database;

public interface Database<K, T> {

    public T get(K key);

}
