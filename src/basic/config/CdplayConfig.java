package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import first.*;
import first2.PopCd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: jyrc
 * @description:
 * @author: Mr.Wang
 * @create: 2018-03-07 16:20
 **/
@Configuration
@PropertySource("classpath:app.properties")
@EnableAspectJAutoProxy
/*@ComponentScan(basePackageClasses = {BasePacketMarker.class, PopCd.class})*/
public class CdplayConfig {
    @Autowired
    Environment en;
    /**
     * 此种配置方式可以不配置ComponentScan范围。也不用在各个要配置的bean类上加@Component注解.加上的也不起作用
     * @return
     */
    @Bean
    public CompactDisc randomCd(){
        int chois = (int) Math.floor(Math.random() * 2);
        if(chois==0 ||chois==1){
            RockCd rcd=new RockCd(en.getProperty("rockcd.content","defaultValue"));
            List<String> tracks=new ArrayList<>();
            tracks.add("don't let me down");
            tracks.add("your smile");
            tracks.add("dusk till down");
            rcd.setTracks(tracks);
            return rcd;
        }else{
            return new PopCd(en.getProperty("popcd.content",Integer.class,10));
        }
    }
    @Bean
    public Performance performance(){
        return new DancePerformer();
    }
    @Bean
    public Audience audience(){
        return new Audience();
    }

    @Bean
    public TrackCounter trackCounter() {
        return new TrackCounter();
    }

    /**
     * 指定为单例模式
     * @param randomCd
     * @return
     */
    @Bean
    @Qualifier("player")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public MediaPlayer mediaPlayer(CompactDisc randomCd){
      return  new CdPlayer(randomCd);
    }


    @Bean
    @Qualifier("hsqldb")
    public DataSource hsqldb(){
        return new EmbeddedDatabaseBuilder().addScript("classpath:t.sql").build();
    }


    @Bean
    @Qualifier("c3p0")
    public DataSource c3p0(){
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/cqms");
        ds.setUser("root");
        ds.setPassword("123456");
        try {
            ds.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        ds.setAcquireIncrement(5);
        ds.setInitialPoolSize(20);
        ds.setMinPoolSize(2);
        ds.setMaxPoolSize(50);
        return ds;
    }
}
