package Model;

public class Variables {
    private static String url = "https://api.themoviedb.org/3/movie/";
    private static String urlImage = "http://image.tmdb.org/t/p/w500/";
    private static String public_key = "ebe110c9cd49da156188793c9a2f1b67";

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Variables.url = url;
    }

    public static String getPublic_key() {
        return public_key;
    }

    public static void setPublic_key(String public_key) {
        Variables.public_key = public_key;
    }

    public static String getUrlImage() {
        return urlImage;
    }

    public static void setUrlImage(String urlImage) {
        Variables.urlImage = urlImage;
    }
}
