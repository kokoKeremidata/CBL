package src;
import java.time.*;
import java.util.Set;
import java.util.HashSet;
import java.nio.file.Path;
import java.io.Serializable;
import java.util.Locale;
enum Lang {
        EN, 
        JP, 
        CH
    }
enum Genre{
        Action,
        Adventure,
        Fantasy,
        Sci_Fi,
        Romance,
        Drama,
        Slice_of_life,
        Isekai,
        Sports,
        Dark_Fantasy,
        Horror,
        Comedy
    }
enum Status{
        NOT_WATCHED,
        COMPLETED,
        WATCHING,        
        PLAN_TO_WATCH,
        ON_HOLD,
        DROPPED
    }

public class Anime implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nameEN;
    private String nameJP;
    private String nameCH;
    private int episodesTotal;
    private int episodesWatched;
    private LocalDate release;
    private int rating;
    private Status status;
    private Set<Genre> genres = new HashSet<>();
    private String imagePath;
    private String studio;
    
    public Anime(String nameEN, String nameJP, String nameCH,
            int episodesTotal, LocalDate release, String studio) {
        this.nameEN = nameEN;
        this.nameJP = nameJP;
        this.nameCH = nameCH;
        this.episodesTotal = episodesTotal;
        this.release = release;
        this.studio = studio;
        this.status = Status.NOT_WATCHED;
        boolean valid_input = true;
        if (rating < 0 || rating > 10)
            valid_input = false;
        if (episodesWatched >= episodesTotal || episodesWatched < 0)
            valid_input = false;
        imagePath = AnimeImageFinder.findImage(nameEN);
    }
    
    public String getName(Lang l) {
        String name = switch (l) {
            case EN -> nameEN;
            case JP -> nameJP;
            case CH -> nameCH;
        };
        //if missing name in language fall back to english
        return name != null ? name : nameEN; 
    }
    
    //Getters
    public int getEpT() {
        return episodesTotal;
    }
    
    public int getEpW() {
        return episodesWatched;
    }
    
    public LocalDate getDate() {
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
    
    public String getStudio(){
        return studio;
    }
    
    public String getPath() {
        return imagePath;
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
    
    public void setEpT(int ep) {
        if (ep >= episodesWatched && ep > 0) {
            episodesTotal = ep;
        }
    }
    public void setEpW(int ep) {
        if (ep <= episodesTotal && ep >= 0) {
            episodesWatched = ep;
        }
        if (episodesWatched == episodesTotal)
            status = Status.COMPLETED;
    }
    public void setStatus(Status status) {
        switch (status) {
            case Status.COMPLETED :
                setEpW(getEpT());
                break;
            case Status.NOT_WATCHED :
                setEpW(0);
                break;
            case Status.PLAN_TO_WATCH :
                setEpW(0);
                break;
        }
        this.status = status;
    }
    
    public void setRating(int rating) {
        if (rating >= 0 && rating <= 10) {
            this.rating = rating;
        }
    }
    
    public void setDate(LocalDate release) {
        this.release = release;
    }
    
    public void setStudio(String studio) {
        this.studio = studio;
    }
    
    public void setPath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public void addGenre(Genre genre) {
        genres.add(genre);
    }
    
    //override methods to prevent duplicates
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Anime)) {
            return false;
        }
        Anime other = (Anime) o;
        return nameEN != null && nameEN.equalsIgnoreCase(other.getName(Lang.EN));
    }
    
    @Override
    public int hashCode() {
        return nameEN == null ? 0 : nameEN.toLowerCase(Locale.ROOT).hashCode();
    }
}
