package sample;

public class Buch implements Comparable<Buch>{

    private final String titel;
    private final String author;
    private final int published;

    public Buch(String titel, String author, String published) {
        this(titel, author, Integer.parseInt(published));
    }
    public Buch(String titel, String author, int published) {
        this.titel = titel;
        this.author = author;
        this.published = published;
    }


    public Buch(String[] buchArray) {
        this(buchArray[0], buchArray[1], buchArray[2]);
    }

    public String getTitel() {
        return titel;
    }
    public String getAuthor() { return author; }
    public String getPublished() { return String.valueOf(published); }

    @Override
    //NO STRING - NO LIST!!1!
    public String toString() {
        return titel + ", " + author + ", " + published;
    }

    @Override
    public int compareTo(Buch o) {
        return 0;
    }
}

