package example;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class HelloClient {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static UserService userService = null;
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://localhost:9000/UserService?wsdl");
        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
        QName qname = new QName("http://example/", "UserServiceImplService");
        Service service = Service.create(url, qname);
        userService = service.getPort(UserService.class);
        while (true) {
            printMenu();
            switch (br.readLine()) {
                case "1":
                    addUser();
                    break;
                case "2":
                    deleteUser();
                    break;
                case "3":
                    getAllUsers();
                    break;
                case "4":
                    search();
                    break;
                case "5":
                    updateUser();
                    break;
            }
        }
    }

    private static User readUser() throws IOException {
        User user = new User();
        System.out.print("Id: ");
        user.setId(Integer.parseInt(br.readLine()));
        System.out.print("Name: ");
        user.setName(br.readLine());
        System.out.print("Surname: ");
        user.setSurname(br.readLine());
        System.out.print("Email: ");
        user.setEmail(br.readLine());
        return user;
    }
    private static void addUser() throws IOException {
        userService.addUser(readUser());
    }

    private static void deleteUser() throws IOException {
        System.out.println("Id: ");
        userService.delete(Integer.parseInt(br.readLine()));
    }

    private static void getAllUsers() {
        userService.getAll().forEach(System.out::println);
    }

    private static void search() throws IOException {
        System.out.println(userService.search(br.readLine()));
    }

    private static void updateUser() throws IOException {
        userService.update(readUser());
    }

    private static void printMenu() {
        System.out.println("1: Добавить пользователя");
        System.out.println("2: Удалить пользователя");
        System.out.println("3: Вывести всех");
        System.out.println("4: Поиск");
        System.out.println("5: Обновить пользователя");
    }
}