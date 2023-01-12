public class Book {
    private int idBook;
    private String titleBook;
    private float priceBook;
    private int amountBook;
    private String imagePathBook;
    private int authorIdBook;

    public Book(int idBook, String titleBook, float priceBook, int amountBook, String imagePathBook, int authorIdBook) {
        this.idBook = idBook;
        this.titleBook = titleBook;
        this.priceBook = priceBook;
        this.amountBook = amountBook;
        this.imagePathBook = imagePathBook;
        this.authorIdBook = authorIdBook;
    }

    public int getIdBook() {
        return idBook;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public float getPriceBook() {
        return priceBook;
    }

    public int getAmountBook() {
        return amountBook;
    }

    public String getImagePathBook() {
        return imagePathBook;
    }

    public int getAuthorIdBook() {
        return authorIdBook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "idBook=" + idBook +
                ", title='" + titleBook + '\'' +
                ", price=" + priceBook +
                ", amount=" + amountBook +
                ", imagePath='" + imagePathBook + '\'' +
                ", authorId=" + authorIdBook +
                '}';
    }
}
