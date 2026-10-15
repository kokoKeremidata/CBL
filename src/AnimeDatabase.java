package src;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Collections;

public class AnimeDatabase implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Anime> allAnime = new ArrayList<>();
    private List<Watchlist> watchlists = new ArrayList<>();
    
    //anime methods
    public List<Anime> getAllAnime() {
        return Collections.unmodifiableList(allAnime);
    }
    
    public List<Anime> searchAnime(String in) {
        List<Anime> results = new ArrayList<>();
        //display all anime if search null
        if (in == null || in.isBlank()) {
            return new ArrayList<>(allAnime);
        }
        //lowercase in root language
        in.toLowerCase(Locale.ROOT).trim();
        //partial search
        for (Anime anime : allAnime) {
            for (Lang lang : Lang.values()) {
                String title = anime.getName(lang);
                if (title != null &&
                    title.toLowerCase(Locale.ROOT).contains(in)) {
                    results.add(anime);
                    break;
                }
            }
        }
        return results;
    }
    
    public List<Anime> getAnimeByStatus(Status status) {
        List<Anime> results = new ArrayList<>();
        for (Anime anime : allAnime) {
            if (anime.getStatus() == status) {
                results.add(anime);
            }
        }
        return results;
    }
    
    public void addAnime(Anime anime) {
        if (anime == null || allAnime.contains(anime)) {
            System.out.println("anime already exists");
            return;
        }
        allAnime.add(anime);
    }
    
    public void removeAnime(Anime anime) {
        if (anime != null && allAnime.remove(anime)) {
            for (Watchlist watchlist : watchlists) {
                watchlist.removeAnime(anime);
            }
        }
    }
    
    //watchlist methods
    public List<Watchlist> getWatchlists() {
        return Collections.unmodifiableList(watchlists);
    }
    
    public void addWatchlist(Watchlist w) {
        if (w == null || watchlists.contains(w)) {
            System.out.println("watchlist already exists");
            return;
        }
        watchlists.add(w);
    }
    
    public void removeWatchlist(Watchlist w) {
        watchlists.remove(w);
    }
    
    public Watchlist findWatchlistByName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        for (Watchlist w : watchlists) {
            if (w.getName().equalsIgnoreCase(name)) {
                return w;
            }
        }
        return null;
    }
}