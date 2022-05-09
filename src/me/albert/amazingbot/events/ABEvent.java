package me.albert.amazingbot.events;

import net.md_5.bungee.api.plugin.Event;

import java.util.concurrent.ConcurrentHashMap;

public class ABEvent extends Event {
    private final transient ConcurrentHashMap<String, Object> metas = new ConcurrentHashMap<>();

    protected long time;

    protected long self_id;

    protected String post_type;

    public ConcurrentHashMap<String, Object> getMetas() {
        return metas;
    }

    public void addMeta(String key, Object object) {
        metas.put(key, object);
    }

    public Object getMeta(String key) {
        if (!hasMeta(key)) return null;
        return metas.get(key);
    }

    public boolean hasMeta(String key) {
        return metas.containsKey(key);
    }

    public String getPostType() {
        return post_type;
    }

    public long getSelfID() {
        return self_id;
    }

    public long getTime() {
        return time;
    }

}
