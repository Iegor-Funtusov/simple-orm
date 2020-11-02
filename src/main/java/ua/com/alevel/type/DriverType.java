package ua.com.alevel.type;

import lombok.Getter;

/**
 * @author Iehor Funtusov, created 02/11/2020 - 8:46 PM
 */

@Getter
public enum DriverType {

    WRAPPED_DRIVER_POSTGRESQL("org.postgresql.Driver", DbDatasourceType.POSTGRESQL),
    WRAPPED_DRIVER_MYSQL("com.mysql.cj.jdbc.Driver", DbDatasourceType.MYSQL);

    private final String driverType;
    private final DbDatasourceType dbDataType;

    DriverType(String driverType, DbDatasourceType dbDataType) {
        this.driverType = driverType;
        this.dbDataType = dbDataType;
    }

    public static String checkType(DbDatasourceType dbDataType) {
        switch (dbDataType) {
            case MYSQL: return DriverType.WRAPPED_DRIVER_MYSQL.getDriverType();
            case POSTGRESQL: return DriverType.WRAPPED_DRIVER_POSTGRESQL.getDriverType();
        }
        throw new RuntimeException("driver type not found");
    }
}
