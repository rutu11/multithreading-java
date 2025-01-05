package StreamsPkg;

public class Book {
    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
