package ua.nure.publisher.parser;

import ua.nure.publisher.dto.Magazines;

public interface MagazinesMarshaller {

    void marshal(Magazines magazines, String filePath);
}
