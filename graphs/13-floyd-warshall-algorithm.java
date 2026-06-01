/*
 * Floyd-Warshall Algorithm
 * Triple loop: for each intermediate node k, update
 * dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]).
 * Skip if either leg is INF to avoid overflow. Modifies the matrix in-place;
 * k must be the outermost loop. Detects negative cycles: if dist[i][i] < 0
 * after the algorithm, a negative cycle exists.
 */

class FloydWarshallAlgorithm {
    private static final int INF = (int) 1e8;

    public void floydWarshall(int[][] dist) {
        int m = dist.length;

        for (int k = 0; k < m; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF) {
                        continue;
                    }
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}
