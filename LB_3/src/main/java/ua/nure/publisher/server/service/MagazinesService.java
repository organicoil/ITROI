package ua.nure.publisher.server.service;

import ua.nure.publisher.server.dto.Magazine;
import ua.nure.publisher.server.dto.Magazines;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MagazinesService {

    @WebMethod
    Magazines getMagazines();

    @WebMethod
    void addMagazine(Magazine magazine);

    @WebMethod
    Magazine getMagazineById(int id);

    @WebMethod
    void removeMagazineById(int id);

    @WebMethod
    void clearMagazines();

}
