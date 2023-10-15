package Greedy;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {

    public static class Program{
        public int start;
        public int end;

        public Program(int s, int e){
             start = s;
             end = e;
        }
    }

    public static class ProgramComparator implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    /**
     * 贪心算法安排最多的会议
     * @param programs 所有项目的数组
     * @param timePoint 当前的时间点
     * @return 安排的会议数量
     */
    public int bestArrange(Program[] programs, int timePoint){
        // 根据结束时间从早到晚先排序
        Arrays.sort(programs, new ProgramComparator());
        int result = 0;
        // 遍历所有会议
        for(int i = 0; i < programs.length; i++){
            // 是否可以安排（还未开始）
            if(timePoint <= programs[i].start){
                result++;
                // 来到该会议结束的时间点
                timePoint = programs[i].end;
            }
        }
        return result;
    }
}
