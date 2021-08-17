package utils;

import java.util.concurrent.CopyOnWriteArraySet;

public class SiteMap {
    private final String url;
    private volatile SiteMap parent;
    private volatile int depth;
    private final CopyOnWriteArraySet<SiteMap> subLinks;

    public SiteMap(String url) {
        this.url = url;
        subLinks = new CopyOnWriteArraySet<>();
        depth = 0;
        parent = null;
    }

    public void addSubLinks(SiteMap subLink) {
        if (!subLinks.contains(subLink) &&
                subLink.getUrl().startsWith(url)) {
            this.subLinks.add(subLink);
            subLink.setParent(this);
        }
    }

    private void setParent(SiteMap siteMap) {
        synchronized (this) {
            this.parent = siteMap;
            this.depth = setDepth();
        }
    }

    public int getDepth() {
        return depth;
    }

    private int setDepth() {
        if (parent == null) {
            return 0;
        }
        return 1 + parent.getDepth();
    }

    public CopyOnWriteArraySet<SiteMap> getSubLinks() {
        return subLinks;
    }

    public String getUrl() {
        return url;
    }
}