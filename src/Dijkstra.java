import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dijkstra {
    /**
     * 迪杰斯特拉 单源最短路径,给定图结构，选中源点，
     * <p>
     * 一跳距离最近的点，然后以改点为起点再去搜索，
     * 例 ABCD 四个点 ，A为起点 A-B =1 A-C = 5 A-D = #  B-C = 3 C-D =3 B-D = 10
     * B C 为一跳可达 B为第一个最近点
     * A - C = min(A-C,A-B-C) = min(5,4) = 4
     * A - D = min(A-D,A-B-D) = min(#,11) = 11
     * <p>
     * C 为第2个最近点
     * A-D = min(A-C-D,A-B-D) = min(8,11) = 8
     * <p>
     * <p>
     * 其实每一次都会算出一个最近点，以及每个当前A到每个点的最小距离，
     * <p>
     * 但是这并不意味着 a-b-c-d 最短时 a-b-c 也是最短，迪杰斯特拉方法也是通过遍历每个最近点到其他点的距离去计算
     * 999表示不可达
     */
    public int[] distance = null;
    public int[][] infro = null;
    public Map<Integer, String> pointMap = null;
    public Map<Integer, String> pathMap = null;

    public void dijkstra_md(int p, int[][] graph) {
        // 常用参数
        int len = graph.length;

        // 初始化 信息
        this.infro = new int[len][3];
        for (int i = 0; i < len; i++) {
            this.infro[i][0] = 0;   //存放是否被选为最短点
            this.infro[i][1] = 999; //初始化最短距离
            this.infro[i][2] = p;   //初始化最短路径，i点的前一跳为p点
        }
        this.infro[p][0] = 1;       //由p点开始
        this.infro[p][1] = 0;       //距离为0
        // n个点只需要 遍历n-1次
        for (int i = 0; i < len - 1; i++) {
            //遍历起点到每个点的距离 和 当前最短点到改点的距离，哪个小使用哪个
            for (int j = 0; j < len; j++) {
                // 自身跳过
                if (p == j) {
                    continue;
                }
                // 已被挑选为最短节点跳过
                if (this.infro[j][0] == 1) {
                    continue;
                }
                // 如果 距离有最短则更新
                if (this.infro[p][1] + graph[p][j] < this.infro[j][1]) {
                    this.infro[j][1] = this.infro[p][1] + graph[p][j]; //更新最短距离
                    this.infro[j][2] = p;   //更新最短上一跳
                }
            }
            // 挑选当前最短点
            Integer temp = null;
            for (int tp = 0; tp < len; tp++) {
                if (this.infro[tp][0] == 1) {
                    continue;
                } else {
                    if (temp == null) {
                        temp = tp;
                    } else if (this.infro[tp][1] < this.infro[temp][1]) {
                        temp = tp;
                    }
                }

            }
            p = temp; //更新最短点
            this.infro[p][0] = 1; // 修改状态
        }

    }

    public void showpath(int end, int i) {
        if (i == end) {
            System.out.print("最短路径为" + Integer.toString(end));
        } else {
            this.showpath(end, this.infro[i][2]);
            System.out.print("-->" + Integer.toString(i));
        }

    }

    public void showDistance(int p) {
        for (int i = 0; i < infro.length; i++) {
            System.out.println(Integer.toString(p) + "到点" + Integer.toString(i) + "最短距离为： " + Integer.toString(this.infro[i][1]));
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 3, 12, 999, 999, 999},
                {999, 0, 9, 3, 999, 999},
                {999, 999, 0, 999, 5, 999},
                {999, 999, 4, 0, 13, 15},
                {999, 999, 999, 999, 0, 4},
                {999, 999, 999, 999, 999, 0}
        };
        int p = 0;
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.dijkstra_md(p, graph);
        dijkstra.showDistance(p);

        for (int i = 0; i < graph.length; i++) {
            dijkstra.showpath(p, i);
            System.out.println();
        }


    }
}
