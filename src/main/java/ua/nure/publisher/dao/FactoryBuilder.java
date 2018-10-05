package ua.nure.publisher.dao;

public class FactoryBuilder {

    /**
     * DAO Factory
     * Selects the type of database
     */

    public static AbstractFactory getFactory(TypeFactory type) throws Exception {


        if (type == TypeFactory.MySQL){
            return new MySQLFactory();
        }else {

            throw new Exception();

        }
    }
}
