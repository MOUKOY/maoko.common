package maoko.common.gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author maoko
 * @date 2019/9/6 14:37
 */
public class GsonParameterizedTypeImpl implements ParameterizedType {
    private Class clazz;

    public GsonParameterizedTypeImpl(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[]{clazz};
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }


}
