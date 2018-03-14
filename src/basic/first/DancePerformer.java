package first;

import first.Performance;
import org.springframework.stereotype.Component;

/**
 * @program: springDemo
 * @description:
 * @author: Mr.Wang
 * @create: 2018-03-11 01:09
 **/
@Component
public class DancePerformer implements Performance {
    @Override
    public void perform() {
        System.out.println("dance performance");
    }
}
