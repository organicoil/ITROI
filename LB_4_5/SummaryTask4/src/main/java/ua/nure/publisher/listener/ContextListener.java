package ua.nure.publisher.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.publisher.dao.*;
import ua.nure.publisher.service.*;
import ua.nure.publisher.transaction.TransactionPool;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class ContextListener implements ServletContextListener{
    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

      ServletContext sc = servletContextEvent.getServletContext();
       initLog4J(sc);
        try {

            TransactionPool transactionPool = new TransactionPool((DataSource)(new InitialContext().lookup("java:comp/env/jdbc/root")));
            AbstractFactory abstractFactory = FactoryBuilder.getFactory(TypeFactory.MySQL);
            MagazineDAO magazineDAO = abstractFactory.getMagazineDAO();
            MagazineService magazineService = new MagazineServiceImpl(magazineDAO, transactionPool);
            servletContextEvent.getServletContext().setAttribute("magazineService", magazineService);
            UserDAO userDAO = abstractFactory.getUserDAO();
            UserService userService = new UserServiceImpl(userDAO, transactionPool);
            servletContextEvent.getServletContext().setAttribute("userService", userService);
            OrderDAO orderDAO = abstractFactory.getOrderDAO();
            OrderService orderService = new OrderServiceImpl(orderDAO,transactionPool);
            servletContextEvent.getServletContext().setAttribute("orderService",orderService);

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            LOG.error(e.getMessage(),e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
    private void initLog4J(ServletContext servletContext) {
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
            LOG.debug("Log4j has been initialized");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

