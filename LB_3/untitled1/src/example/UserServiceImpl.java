package example;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebService(endpointInterface = "example.UserService")
public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        String address = "http://localhost:9000/UserService";
        Endpoint.publish(address, new UserServiceImpl());
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public List<User> search(String value) {
        return users.stream()
                .filter(user -> user.toString().toLowerCase().contains(value.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        users = users.stream()
                .filter(user -> user.getId() != id)
                .collect(Collectors.toList());
    }

    @Override
    public void update(User user) {
        users.forEach(user1 -> {
            if (user1.getId() == user.getId()) {
                if (user.getName() != null) user1.setName(user.getName());
                if (user.getEmail() != null) user1.setEmail(user.getEmail());
                if (user.getSurname() != null) user1.setSurname(user.getSurname());
            }
        });
    }
}
