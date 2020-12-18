public class Lab_1 {
    private static final int NO_PARENT = -1;
    // Функция, которая будет выводить короткий путь из вершины до концов, используя Алгоритм Дейкстры.
    // В качестве входных данных - "матрица" в main-e
    private static void dijkstra(int[][] Matrix, int startVertex){
        int Vertices = Matrix[0].length;

        // shortestDistances[i] будет содержать САМЫЕ короткие расстояния
        int[] shortestDistances = new int[Vertices];

        // added[i] будет истина, если вершина "i" будет включена в "дерево кратчайших путей"
        boolean[] added = new boolean[Vertices];

        // Инициализация всех расстояний в бесконечность, а added[] как ложь
        for (int vertexIndex = 0; vertexIndex < Vertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Расстояние из вершины в саму себя равно 0
        shortestDistances[startVertex] = 0;

        // Родительский массив для хранения кратчайших путей
        int[] parents = new int[Vertices];

        // Начальная вершина не имеет родителя
        parents[startVertex] = NO_PARENT;

        // Поиск кратчайшего пути по всем вершинам
        for (int i = 1; i < Vertices; i++) {
            // Выбор вершины с минимальным расстоянием из множества ещё не обработанных вершин.
            // Примечание: nearestVertex всегда равен startNode в первой итерации.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < Vertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
            // Пометка выбранной вершины, как пройденной
            added[nearestVertex] = true;

            // Обновление значения расстояния для смежных вершин от выбранной вершины.
            for (int vertexIndex = 0; vertexIndex < Vertices; vertexIndex++) {
                int edgeDistance = Matrix[nearestVertex][vertexIndex];
                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }
        printSolution(startVertex, shortestDistances, parents);
    }

    // "Служебная" функция для печати
    private static void printSolution(int startVertex, int[] distances, int[] parents) {
        int Vertices = distances.length;
        System.out.print("Путь\t Расстояние\tКратчайший путь");

        for (int vertexIndex = 0; vertexIndex < Vertices; vertexIndex++) {
            if (vertexIndex != startVertex) {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances[vertexIndex] + "\t\t");
                printPath(vertexIndex, parents);
            }
        }
    }

    // Функция для печати кратчайшего пути от источника до "currentVertex" / указанной вершины с использованием родительского массива
    private static void printPath(int currentVertex, int[] parents) {
        // Либо создается ячейка, либо пропуск
        if (currentVertex == NO_PARENT) {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }

    public static void main(String[] args){
        int[][] Matrix = {
                { 0, 5, 2, 4, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 2, 7, 0},
                { 0, 0, 0, 0, 0, 0, 3, 0},
                { 0, 0, 0, 0, 2, 0, 6, 0},
                { 0, 0, 0, 0, 0, 0, 5, 3},
                { 0, 0, 0, 0, 0, 0, 0, 4},
                { 0, 0, 0, 0, 0, 0, 0, 8},
                { 0, 0, 0, 0, 0, 0, 0, 0} };
        dijkstra(Matrix, 0);
    }
}