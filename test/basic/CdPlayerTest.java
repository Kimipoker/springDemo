import config.CdplayConfig;
import first.CompactDisc;
import first.MediaPlayer;
import first.Performance;
import first.TrackCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @program: jyrc
 * @description:
 * @author: libq
 * @create: 2018-03-07 16:31
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CdplayConfig.class)
public class CdPlayerTest {

    @Autowired
    private CompactDisc randomCd;
    @Autowired
    @Qualifier("player")
    private MediaPlayer mediaPlayer;
    @Autowired
    private Performance performance;
    @Autowired
    private TrackCounter trackCounter;
    @Autowired
    private DataSource hsqldb;
    @Autowired
    private DataSource c3p0;
    @Test
    public void play() {
       mediaPlayer.play();
       randomCd.playTrack(1);
       randomCd.playTrack(1);
       randomCd.playTrack(1);
       randomCd.playTrack(2);
       randomCd.playTrack(2);
       randomCd.playTrack(3);
       assertEquals(3,trackCounter.getCount(1));
       assertEquals(2,trackCounter.getCount(2));
       assertEquals(1,trackCounter.getCount(3));
       performance.perform();
      // connectC3p0(c3p0);
      // outEnv();
    }

    private void connectHsqldb(DataSource hsqldb){
        try {
            Connection connection = hsqldb.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select *  from STUDENT");
            while(resultSet.next()){
                System.out.println(resultSet.getString("STUDENTID")+" " +resultSet.getString("STUDENTNAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void connectC3p0(DataSource c3p0){
        try {
            Connection connection = c3p0.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select *  from cq01");
            while(resultSet.next()){
                System.out.println(resultSet.getString("cqs001")+" " +resultSet.getString("cqs002")+resultSet.getString("cqs003"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void outEnv(){
        Map m = System.getenv();
        for (Iterator it = m.keySet().iterator(); it.hasNext(); )
        {
            String key = (String ) it.next();
            String value = (String ) m.get(key);
            System.out.println(key +":" +value);
        }
        System.out.println( "--------------------------------------" );
    }
}
