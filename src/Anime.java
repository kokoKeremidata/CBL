package src;
import java.time.*;
import java.util.Set;
import java.util.HashSet;
enum Lang {
        EN, 
        JP, 
        CH
    }
enum Genre{
        Shōnen,
        Shōjo,
        Seinen,
        Action,
        Adventure,
        Fantasy,
        Sci_Fi,
        Romance,
        Drama,
        Slice_of_life,
        Isekai,
        Sports
    }
enum Status{
        COMPLETED,
        WATCHING,        
        PLAN_TO_WATCH,
        ON_HOLD,
        DROPPED
    }

public class Anime {
    private String nameEN;
    private String nameJP;
    private String nameCH;
    private int episodesTotal;
    private int episodesWatched;
    private LocalDate release;
    private int rating;
    private Status status;
    private Set<Genre> genres = new HashSet<>();
    private String img;
    
    public void Anime(String nameEN, String nameJP, String nameCH,
            int episodesTotal, LocalDate release) {
        this.nameEN = nameEN;
        this.nameJP = nameJP;
        this.nameCH = nameCH;
        this.episodesTotal = episodesTotal;
        this.release = release;
        boolean valid_input = true;
        if (rating < 0 && rating > 10)
            valid_input = false;
        if (episodesWatched >= episodesTotal && episodesWatched < 0)
            valid_input = false;
    }
    
    public String getName(Lang l) {
        String name = switch (l) {
            case EN -> nameEN;
            case JP -> nameJP;
            case CH -> nameCH;
        };
        return name;
    }
    
    //Getters
    public int getEpT() {
        return episodesTotal;
    }
    
    public int getEpW() {
        return episodesWatched;
    }
    
    public LocalDate getRelease() {
        return release;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public int getRating() {
        return rating;
    }
    
    public Set<Genre> getGenres(){
        return genres;
    }
    
    //Setters
    public void setNameEN(String n) {
        nameEN = n;
    }
    
    public void setNameJP(String n) {
        nameJP = n;
    }
    public void setNameCH(String n) {
        nameCH = n;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public void addGenre(Genre genre) {
        genres.add(genre);
    }
    
    //Find poster img
    public void assignPoster() {
        
    }
}
