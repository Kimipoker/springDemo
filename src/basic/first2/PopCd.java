package first2;

import first.BasePacketMarker;
import first.CompactDisc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * javaConfig如果不设置@ComponentScan默认只扫描其所在的package
 */
@Component

public class PopCd implements CompactDisc, BasePacketMarker{
    private int content;
    /**
     * SpEl  简化开发中逻辑、配置的编写
     */
    @Value("#{systemProperties['user.language']}")
    private String lan;
    public PopCd(Integer content) {
        this.content=content;
    }

    @Override
    public void play() {
        System.out.println(content);
        System.out.println(lan);
    }

    @Override
    public void playTrack(int i) {
      System.out.println("No Track");
    }
}
