package src;
import java.util.List;
import java.util.ArrayList;

public class Watchlist {
    private String name;
    private List<Anime> anime = new ArrayList<>();
    
    public void Watchlist(String name){
        this.name = name;
    }
    public void addAnime(Anime anime){
        this.anime.add(anime);
    }
    public void removeAnime(Anime anime) {
        this.anime.remove(anime);
    }
    public Anime searchByName(String name) {
        for (int i = 0; i < anime.size() - 1; i++) {
            if (anime.get(i).getName(Lang.EN).equalsIgnoreCase(name) ||
                anime.get(i).getName(Lang.JP).equalsIgnoreCase(name) ||
                anime.get(i).getName(Lang.CH).equalsIgnoreCase(name)) {
                return anime.get(i);
            }
        }
        return null;
    }
}
