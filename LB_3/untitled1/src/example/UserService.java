package example;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;

@WebService()
public interface UserService {

  @WebMethod
  void addUser(User user);

  @WebMethod
  List<User> getAll();

  @WebMethod
  List<User> search(String value);

  @WebMethod void delete(int id);

  @WebMethod void update(User user);
}
