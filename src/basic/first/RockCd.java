package first;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 当自动装配无法找到实现待装配接口unique、无歧义的bean的类 ,时Primary指定首选bean
 */
/**
 * @author Kimipoker
 * @program: jyrc
 * @description:
 * @author: Mr.Wang
 * @create: 2018-03-07 16:29
 **/
@Component
@Primary
public class RockCd implements CompactDisc, BasePacketMarker {
    private String content;
    private List<String> tracks;
    public RockCd(String content) {
        this.content = content;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    @Override
    public void play() {
        System.out.println(content);
        for (String track : tracks) {
            System.out.println("play:"+track);
        }
    }

    @Override
    public void playTrack(int i) {
        if(tracks.size()>=i&&i>=1){
            System.out.println(tracks.get(i-1));
        }else{
            System.out.println("Error Track Index");
        }
    }
}
