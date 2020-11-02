package ua.com.alevel.factory;

import ua.com.alevel.config.CoroneConfig;
import ua.com.alevel.type.DbStrategyType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Iehor Funtusov, created 02/11/2020 - 7:53 PM
 */
public class MysqlConnection extends AbstractConnection {
    @Override
    public void connect(CoroneConfig config) {
        DbStrategyType strategyType = DbStrategyType.valueOf(config.getStrategy().toUpperCase());
        System.out.println("strategyType = " + strategyType);
        System.out.println("config = " + config);
        try(Connection connection = DriverManager.getConnection(config.getUrlConnection(), config.getUsername(), config.getPassword());
            Statement statement = connection.createStatement()) {
            switch (strategyType) {
                case NONE: {} break;
                case UPDATE: {} break;
                case CREATE_DROP: {
                    String drop = "DROP SCHEMA if EXISTS " + config.getSchema() + ";";
                    String create = "CREATE SCHEMA " + config.getSchema() + ";";
                    String encoding = "ALTER database " + config.getSchema() + " charset=utf8 collate=utf8_bin;";
                    String grant = "GRANT ALL ON " + config.getSchema() + ".* TO " + config.getUsername() + "@" + config.getHost() + ";";
                    statement.executeUpdate(drop);
                    statement.executeUpdate(create);
                    statement.executeUpdate(encoding);
                    statement.executeUpdate(grant);
                } break;
            }
            readPropertyByEntity();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void readPropertyByEntity() {

    }
}
