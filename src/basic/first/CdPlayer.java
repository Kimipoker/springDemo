package first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Kimipoker
 */
@Component

public class CdPlayer implements MediaPlayer {

    private CompactDisc popCd;

    @Autowired
    public CdPlayer(CompactDisc popCd) {
        this.popCd = popCd;
    }

    @Override
    public void play() {
        popCd.play();
    }
}
