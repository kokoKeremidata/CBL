package src;

import java.io.IOException;
import java.net.URL;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Locale;
import java.util.stream.Stream;

public class AnimeImageFinder {
    private static String normalize(String text) {
        return text.toLowerCase(Locale.ROOT)
                   .replaceAll("[^a-z0-9]", "");
    }
    
    public static String findImage(String animeName) {
        URL folderURL = AnimeImageFinder.class.getResource("/images");
        
        if (folderURL == null) {
            System.out.println("Images folder not found");
            return null;
        }
        
         try {
            Path folder = Paths.get(folderURL.toURI());

            try (Stream<Path> files = Files.list(folder)) {
                String target = normalize(animeName);

                return files
                    .filter(Files::isRegularFile)
                    .filter(p -> {
                        String filename = p.getFileName().toString();

                        String name = normalize(filename.substring(
                                0, filename.length() - 4
                            )
                        );

                        return name.equals(target)
                            || name.contains(target)
                            || target.contains(name);
                    })
                    .map(p -> "/images/" + p.getFileName())
                    .findFirst()
                    .orElse(null);
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
