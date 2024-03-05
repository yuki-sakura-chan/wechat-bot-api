package caiyucong.cn.bot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {

    private T data;

    private Boolean success;

    public static <T> R<T> success(T data) {
        return new R<>(data, true);
    }

    public static <T> R<T> error(T data) {
        return new R<>(data, true);
    }

    public static <T> R<T> notRespond(){
        R<T> r = new R<>();
        r.success = false;
        return r;
    }

}
