package module13;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonMapper {
    private JsonMapper() {
    }

    public static <T> T map(String json, Class<T> resultClass) {
        return new Gson().fromJson(json, resultClass);
    }

    public static <T> String toJson(T object) {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(object);
    }

    public static <T> List<T> mapToList(String json, Class<T> resultClass) {
        Type type = TypeToken.getParameterized(List.class, resultClass).getType();
        return new Gson().fromJson(json, type);
    }

}
