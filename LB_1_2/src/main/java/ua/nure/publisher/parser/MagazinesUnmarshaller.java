package ua.nure.publisher.parser;

import ua.nure.publisher.dto.Magazines;

public interface MagazinesUnmarshaller {

    Magazines unmarshal(String filePath);
}
