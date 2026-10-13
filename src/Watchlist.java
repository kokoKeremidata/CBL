package src;
import java.util.List;
import java.util.ArrayList;

public class Watchlist {
    private String name;
    private List<Anime> anime = new ArrayList<>();
    
    public void Watchlist(String name){
        if (!name.isBlank()) {
            this.name = name;
        }
    }
    
    public void addAnime(Anime anime){
        if (anime != null) {
            if (!this.anime.contains(anime))
                this.anime.add(anime);
            else
                System.out.println("anime already exists");
        } else
            System.out.println("no anime");
    }
    
    public void removeAnime(Anime anime) {
        this.anime.remove(anime);
    }
    
    public Anime searchByName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        for (int i = 0; i < anime.size(); i++) {
            Anime current = anime.get(i);
            for (Lang lang : Lang.values()) {
                String title = current.getName(lang);
                if (title != null &&
                    title.toLowerCase().contains(name.toLowerCase())) {
                    return current;
                }
            }
        }
        
        return null;
    }
    
    public List<Anime> getAnime(){
        return anime;
    }
}
