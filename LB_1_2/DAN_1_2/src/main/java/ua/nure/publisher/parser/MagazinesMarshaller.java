package ua.nure.publisher.parser;

import ua.nure.publisher.entity.Magazines;

public interface MagazinesMarshaller {

    void marshal(Magazines magazines, String filePath);
}
