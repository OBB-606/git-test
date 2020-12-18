public class lab1
{
    private static final int NO_PARENT = -1;
    private static void dijkstra(int [][] Matrix, int startVertex)/*Метод, который будет выводить кратчайшее расттояние
    от начальной вершины до остальных, изпользуя алгоритм Дейкстры
    */
    {
        int Vertices  = Matrix[0].length;
        int a = 0;

        int [] shortesDistances = new int[Vertices];//содержит кратчайшие расстояния
        boolean[] added = new boolean[Vertices];//added[i] вернет true , если вершина i будет включена в "дерево кратчайших путей"
        for (int vertexIndex  = 0;vertexIndex < Vertices; vertexIndex++)//инициализация всех расстояний как бесконечность
        {
            shortesDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex]=false;
        }
        shortesDistances[startVertex] = 0;// расстояние от вершины до самой себя = 0
        int [] parents = new int [Vertices];//"родительский" массив для храниня кратчайших путей
        parents[startVertex] = NO_PARENT;// начальная вершина не имееет родителя
        for(int i=1;i<Vertices;i++)//Поиск кратчайшего пути
        {
            int nearestVertex = -1;// ближайшая вершина
            int shortesDistance  = Integer.MAX_VALUE;
            for(int vertexIndex = 0; vertexIndex< Vertices; vertexIndex++)
            {
                if(! added[vertexIndex]&& shortesDistances[vertexIndex]<shortesDistance)
                {
                    nearestVertex = vertexIndex;
                    shortesDistance = shortesDistances[vertexIndex];
                }
            }
            added[nearestVertex] = true;//помечаем выбранную вершину как пройденную
            for (int vertexIndex = 0; vertexIndex< Vertices;vertexIndex++)/*обновление значения расстояния
             для смежных вершин от выбранной вершины*/
            {
                int edgeDistance = Matrix[nearestVertex][vertexIndex];//крайняя вершина
                if(edgeDistance>0 && ((shortesDistance+edgeDistance)<shortesDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortesDistances[vertexIndex] = shortesDistance + edgeDistance;
                }
            }

        }
        printSolution(startVertex,shortesDistances,parents);
    }

    private static void printSolution(int startVertex, int[] distances, int[] parents)//метод для печати
    {
        int Vertices = distances.length;
        System.out.print("Путь\tкратчайшее расстояние\tпуть(по вершинам)");
        for (int vertexIndex = 0;vertexIndex<Vertices;vertexIndex++)
        {
            if(vertexIndex!=startVertex)
            {
                System.out.print("\n"+startVertex+" -> ");
                System.out.print(vertexIndex+" \t\t\t\t ");
                System.out.print(distances[vertexIndex]+ "\t\t\t\t");
                printPath(vertexIndex,parents);
            }
        }
    }

    private static void printPath(int currentVertex, int[] parents)//метот, печатающий путь
    {//либо создается ячейка массива, либо пропускаем
        if (currentVertex == NO_PARENT)
            return;
        printPath(parents[currentVertex],parents);
        System.out.print(currentVertex+" ");
    }

    public static void main(String[] args)
    {
        int [][] Matrix =
                {      //0 1 2 3 4 5 6 7
                        {0,5,2,4,0,0,0,0},//0
                        {0,0,0,0,0,2,7,0},//1
                        {0,0,0,0,0,0,3,0},//2
                        {0,0,0,0,2,0,6,0},//3
                        {0,0,0,0,0,0,5,3},//4
                        {0,0,0,0,0,0,0,4},//5
                        {0,0,0,0,0,0,0,8},//6
                        {0,0,0,0,0,0,0,0} //7

                }  ;
        dijkstra(Matrix,0);
    }
}
