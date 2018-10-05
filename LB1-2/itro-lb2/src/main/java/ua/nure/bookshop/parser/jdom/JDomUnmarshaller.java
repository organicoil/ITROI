package ua.nure.bookshop.parser.jdom;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;
import ua.nure.bookshop.entity.Book;
import ua.nure.bookshop.entity.BookShop;
import ua.nure.bookshop.entity.Category;
import ua.nure.bookshop.parser.BookUnmarshaller;

import java.io.IOException;

public class JDomUnmarshaller implements BookUnmarshaller {

    public static void main(String[] arg) {
        JDomUnmarshaller parser = new JDomUnmarshaller();
        BookShop bookShop = parser.unmarshal("src/main/resources/xml/bookShop.xml");
        System.out.println(bookShop);
    }

    @Override
    public BookShop unmarshal(String filePath) {
        BookShop bookShop = new BookShop();
        SAXBuilder builder = new SAXBuilder(XMLReaders.NONVALIDATING);
        try {
            Document doc = builder.build(filePath);
            Element bookShopElement = doc.getRootElement();
            for (Element child : bookShopElement.getChildren()) {
                if ("book".equals(child.getName())) {
                    Book book = getBook(child);
                    if (book != null) {
                        bookShop.getBook().add(book);
                    }
                }
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookShop;
    }

    private static Book getBook(Element elem) {
        try {
            Namespace ns = Namespace.getNamespace("book", "http://nure.ua/bookShop");
            Book book = new Book();
            book.setId(elem.getAttribute("id").getIntValue());
            book.setTitle(elem.getChild("title", ns).getText());
            elem.getChildren("author", ns).forEach(elAuthor -> {
                book.addAuthor(elAuthor.getText());
            });
            book.setISBN(elem.getChild("ISBN", ns).getText());
            book.setPrice(Double.parseDouble(elem.getChild("price", ns).getText()));
            book.setCategory(Category.fromValue(elem.getChildText("category", ns)));
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
