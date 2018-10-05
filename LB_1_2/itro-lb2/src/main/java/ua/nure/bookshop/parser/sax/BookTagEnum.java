package ua.nure.bookshop.parser.sax;

public enum BookTagEnum {

    BOOK_SHOP("bookShop"),
    BOOK("book"),
    BOOK_TITLE("title"),
    BOOK_CATEGORY("category"),
    BOOK_PRICE("price"),
    BOOK_AUTHOR("author"),
    BOOK_ISBN("ISBN"),
    NULL("");

    private String tagName;
    private String nameSpace;

    public static BookTagEnum getInstance(String tag, String nameSpace) {
        for (BookTagEnum e : BookTagEnum.values()) {
            if (e.getNameSpace().equals(nameSpace) && e.getTagName().equals(tag)) {
                return e;
            }
        }
        return NULL;
    }

    BookTagEnum(String tagName) {
        this.tagName = tagName;
        this.nameSpace = "http://nure.ua/bookShop";
    }

    public boolean isBookShop() {
        return this == BOOK_SHOP;
    }

    public boolean isBook() {
        return this == BOOK;
    }

    public boolean isBookTitle() {
        return this == BOOK_TITLE;
    }

    public boolean isAuthor() {
        return this == BOOK_AUTHOR;
    }

    public boolean isISBN() {
        return this == BOOK_ISBN;
    }

    public boolean isBookCategory() {
        return this == BOOK_CATEGORY;
    }

    public boolean isBookPrice() {
        return this == BOOK_PRICE;
    }

    public String getTagName() {
        return tagName;
    }

    public String getNameSpace() {
        return nameSpace;
    }

}
