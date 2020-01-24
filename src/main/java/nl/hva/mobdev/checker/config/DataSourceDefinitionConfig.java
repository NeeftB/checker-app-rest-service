package nl.hva.mobdev.checker.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;

/**
 * Configuration of the connection with mySQL.
 * Change the user and password so it can connect with your mySQL database.
 *
 * @author NeeftB
 */
@DataSourceDefinition(
        name = "java:app/env/jdbc/CheckerDb",
        className = "com.mysql.cj.jdbc.MysqlXADataSource",
//        url = "jdbc:mysql://localhost:3306/checker?createDatabaseIfNotExist=true&serverTimezone=CET",
        url = "jdbc:mysql://localhost:3306/checker?createDatabaseIfNotExist=true&serverTimezone=CET&useSSL=false&allowPublicKeyRetrieval=true",
        user = "root", //fill in your username of your MySQL server.
        password = "Dr!ger13" //fill in your password of your MySQL server.
)


@Stateless
public class DataSourceDefinitionConfig {
}
