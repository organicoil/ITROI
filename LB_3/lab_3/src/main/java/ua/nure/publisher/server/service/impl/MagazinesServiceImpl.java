package ua.nure.publisher.server.service.impl;

import ua.nure.publisher.server.dto.Magazine;
import ua.nure.publisher.server.dto.Magazines;
import ua.nure.publisher.server.dto.ObjectFactory;
import ua.nure.publisher.server.service.MagazinesService;

import javax.jws.WebService;
import java.util.Optional;

@WebService(endpointInterface = "ua.nure.publisher.server.service.MagazinesService")
public class MagazinesServiceImpl implements MagazinesService {

    private Magazines magazines = new ObjectFactory().createMagazines();

    @Override
    public Magazines getMagazines() {
        return magazines;
    }

    @Override
    public void addMagazine(Magazine magazine) {
        magazines.getMagazines().add(magazine);
    }

    @Override
    public Magazine getMagazineById(int id) {
        return getMagazineOrEmptyById(id).orElse(null);
    }

    @Override
    public void removeMagazineById(int id) {
        getMagazineOrEmptyById(id).ifPresent(magazines.getMagazines()::remove);
    }

    @Override
    public void clearMagazines() {
        magazines = new Magazines();
    }

    private Optional<Magazine> getMagazineOrEmptyById(int id) {
        return magazines.getMagazines()
                .stream()
                .filter(magazine -> magazine.getId() == id)
                .findFirst();
    }

}
