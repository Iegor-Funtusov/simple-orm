package ua.com.alevel.config;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;

import ua.com.alevel.type.DbStrategyType;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Iehor Funtusov, created 02/11/2020 - 8:21 PM
 */

@Getter
@Setter
@ToString
public class CoroneConfig {

    private String username;
    private String password;
    private String platform;
    private String schema;
    private String strategy;
    private String url;
    private String urlConnection;
    private String host;

    private static CoroneConfig instance;

    public CoroneConfig() { }

    public static CoroneConfig getInstance() {
        if (instance == null) {
            instance = new CoroneConfig();
        }
        return instance;
    }

    @SneakyThrows
    public void init(Map<String, String> map) {
        Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String fieldValue = map.get(declaredField.getName());
            if (StringUtils.isNotBlank(fieldValue)) {
                declaredField.setAccessible(true);
                declaredField.set(this, fieldValue);
            }
        }
        if (this.getStrategy().toUpperCase().equals(DbStrategyType.CREATE_DROP.name())) {
            this.urlConnection = "jdbc:" + getPlatform() + "://" + url + "/";
        } else {
            this.urlConnection = "jdbc:" + getPlatform() + "://" + url + "/" + schema;
        }
        this.host = getUrl().split(":")[0];
    }
}
