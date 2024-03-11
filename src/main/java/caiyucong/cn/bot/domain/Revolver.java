package caiyucong.cn.bot.domain;

import caiyucong.cn.bot.exception.RevolverException;
import caiyucong.cn.bot.exception.WechatException;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 左轮手枪
 */
@Data
public class Revolver implements Serializable {

    private List<Boolean> magazine;

    private Integer index;

    /**
     * 装载子弹
     * @param number 子弹数
     * @param magazineMax 弹匣最大值
     * @throws WechatException {@link WechatException}
     */
    public void loadBullets(Integer number, Integer magazineMax) throws WechatException {
        if (number > magazineMax) {
            throw new RevolverException("子弹数量不能大于弹匣的容量");
        }
        magazine = new ArrayList<>();
        index = 0;
        for (int i = 0; i < number; i++) {
            magazine.add(Boolean.TRUE);
        }
        for (int i = 0; i < magazineMax - number; i++) {
            magazine.add(Boolean.FALSE);
        }
    }

    /**
     * 打乱子弹的循序
     */
    public void messUp() {
        Collections.shuffle(magazine);
    }

    /**
     * 开枪
     */
    public Boolean shot() {
        Boolean b = magazine.get(index);
        if (b) {
            magazine.set(index, Boolean.FALSE);
            index++;
            return true;
        }
        index++;
        return false;
    }

}
