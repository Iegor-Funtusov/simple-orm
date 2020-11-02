package ua.com.alevel.factory;

import lombok.SneakyThrows;
import net.jodah.typetools.TypeResolver;
import org.apache.commons.collections4.CollectionUtils;
import org.reflections.Reflections;
import ua.com.alevel.config.CoroneConfig;
import ua.com.alevel.dao.CorOneDao;
import ua.com.alevel.type.DbDatasourceType;
import ua.com.alevel.type.DbStrategyType;
import ua.com.alevel.type.DriverType;
import ua.com.alevel.util.ResourceUtil;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

/**
 * @author Iehor Funtusov, created 02/11/2020 - 7:52 PM
 */

public class CoroneDatasourceFactory {

    private Reflections scanner = new Reflections("ua.com.alevel");

    @SneakyThrows
    public void create() {
        Map<String, String> map = ResourceUtil.getResource(this.getClass());
        CoroneConfig config = CoroneConfig.getInstance();
        config.init(map);

        DbDatasourceType dbDatasourceType = getDbDatasourceType(config.getPlatform());
        String driverType = DriverType.checkType(dbDatasourceType);

        Set<Class<? extends CorOneDao>> oneDaos = getSubTypesOfCorOneDaoe();
        if (CollectionUtils.isEmpty(oneDaos)) {
            throw new RuntimeException("dao layers not found");
        }

        oneDaos.forEach(aClass -> {
            System.out.println("class: " + aClass);

            Class<?>[] typeArgs = TypeResolver.resolveRawArguments(aClass.getInterfaces()[0], aClass);
            Class<?> entityClass = typeArgs[0];

            Annotation[] annotations = entityClass.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println("annotation = " + annotation.toString());
            }

            Class<?> idClass = typeArgs[1];
            System.out.println("entity class = " + entityClass.getCanonicalName());
            System.out.println("idClass class = " + idClass.getCanonicalName());

        });

        Class.forName(driverType);

        AbstractConnection connection = null;
        switch (dbDatasourceType) {
            case POSTGRESQL : {
                if (config.getStrategy().equals(DbStrategyType.CREATE_DROP.name())) {
                    throw new RuntimeException("could not create or drop database");
                }
                connection = new PostgesConnection();
            } break;
            case MYSQL : {
                connection = new MysqlConnection();
            }
            if (connection == null) {
                throw new RuntimeException("driver not working");
            }
            connection.connect(config);
        }
    }

    private DbDatasourceType getDbDatasourceType(String platform) {
        return DbDatasourceType.valueOf(platform.toUpperCase());
    }

    private <T> Set<Class<? extends CorOneDao>> getSubTypesOfCorOneDaoe() {
        return scanner.getSubTypesOf(CorOneDao.class);
    }
}
