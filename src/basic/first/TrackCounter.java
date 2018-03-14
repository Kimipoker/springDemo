package first;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: springDemo
 * @description:
 * @author: Mr.Wang
 * @create: 2018-03-11 01:43
 **/
@Aspect
public class TrackCounter {
    private Map<Integer,Integer> countMap=new HashMap();
    @Pointcut("execution(* first.CompactDisc.playTrack(int))" +
    "&& args(trackId)")
    public void trackPlayed(int trackId){}
    @Before("trackPlayed(trackId)")
    public void countTrack(int trackId){
        int count=getCount(trackId);
        countMap.put(trackId,count+1);
    }
    public int getCount(int trackId){
       return countMap.containsKey(trackId)?countMap.get(trackId):0;
    }
}
