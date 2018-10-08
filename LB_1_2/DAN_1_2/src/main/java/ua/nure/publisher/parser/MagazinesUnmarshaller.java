package ua.nure.publisher.parser;

import ua.nure.publisher.entity.Magazines;

public interface MagazinesUnmarshaller {

    Magazines unmarshal(String filePath);

}
