package jp.tokyo.leon.hera.dms.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author longtao.guan
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CanalUtils {
    public static String convertTableNameToEntityName(String tableName) {
        return Arrays.stream(tableName.split("_"))
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining());
    }

    public static String convertTableNameToMapperName(String tableName) {
        String mapperNameTmp =  Arrays.stream(tableName.split("_"))
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining("")) + "Mapper";
        return  Character.toLowerCase(mapperNameTmp.charAt(0)) + mapperNameTmp.substring(1);

    }
}
