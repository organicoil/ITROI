package ua.nure.publisher.parser.sax.handler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.publisher.dto.Category;
import ua.nure.publisher.dto.Magazine;
import ua.nure.publisher.dto.Magazines;

import java.util.ArrayList;
import java.util.List;

import static ua.nure.publisher.constants.ValueConstants.ID_ATTRIBUTE;

public class SaxMagazinesHandler extends DefaultHandler {

    private Magazines magazines = new Magazines();
    private Magazine currentMagazine;
    private MagazineTagEnum currentTag;
    private List<MagazineTagEnum> tags;

    public Magazines getMagazines() {
        return magazines;
    }

    @Override
    public void startDocument() {
        tags = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        tags.add(MagazineTagEnum.getInstance(localName, uri));
        currentTag = tags.get(tags.size() - 1);
        if (currentTag.isMagazine()) {
            this.currentMagazine = new Magazine();
            currentMagazine.setId(Integer.parseInt(atts.getValue(ID_ATTRIBUTE)));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (currentTag.isMagazine()) {
            this.magazines.getMagazines().add(currentMagazine);
            this.currentMagazine = null;
        }

        tags.remove(tags.size() - 1);
        currentTag = tags.size() > 0 ? tags.get(tags.size() - 1) : null;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length);
        switch (currentTag) {
            case TITLE: {
                currentMagazine.setTitle(str);
                break;
            }
            case DESCRIPTION: {
                currentMagazine.setDescription(str);
                break;
            }
            case PRICE: {
                currentMagazine.setPrice(Double.parseDouble(str));
                break;
            }
            case CATEGORY: {
                currentMagazine.setCategory(Category.fromValue(str));
                break;
            }
        }
    }
}
