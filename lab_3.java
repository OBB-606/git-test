import java.util.ArrayList;
import java.util.Collections;

/*public void print_d_v()
    {
        for(int i=0;i<vertex_degrees.size();i++)
            System.out.print(vertex_degrees.get(i)+" ");
    }*/
/*public void print_array()
    {
        for (int i=0;i<vertex.length;i++)
            System.out.print("vertex ["+i+"] ="+vertex[i]+"\n");
    }*/
public class lab_3
{
    public static void main(String[] args)
    {
        int Matrix[][]= {//Задается матрица смежности
                {0, 1, 0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 0, 0},
                {0, 1, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 0, 1, 0, 0, 1},
                {0, 1, 1, 1, 0, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 1, 1},
                {0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 1, 0, 1, 1, 0}
        };
        Equirhythmic_algorithm e_a = new Equirhythmic_algorithm(Matrix);
        e_a.search_for_degrees();
    }
}
class Equirhythmic_algorithm {
    int color=0;
    int Matrix[][];
    int vertex[] = new int[8];//массив с вершинами( для удобства)
    private int counter_temp;// темпоральная переменная, хранящая в себе степень вершины
    ArrayList<Integer> vertex_degrees = new ArrayList<Integer>();//Список, хранящий в себе степени вершин по порядку
    ArrayList<Integer> temp_temp = new ArrayList<Integer>();

    public Equirhythmic_algorithm(int Matrix[][]) {
        this.Matrix = Matrix;
    }

    public void search_for_degrees()//метод, определяющий степень вершины
    {
        for (int i = 0; i < Matrix.length; i++) {
            for (int j = 0; j < Matrix[i].length; j++)
                if (Matrix[i][j] == 1)
                {
                    temp_temp.add(i);
                    temp_temp.add(j);
                    counter_temp++;
                }
            vertex_degrees.add(counter_temp);//добавление степени вершины в список
            counter_temp = 0;
        }
        initialize_vertex_with_degrees();
    }

    public void initialize_vertex_with_degrees()
    //метод, который по сути дублирует список со степенями вершин просто чтобы к ним удобно было обращаться по индексу
    {
        for (int i = 0; i < vertex_degrees.size(); i++)
            vertex[i] = vertex_degrees.get(i);
        bubble_sort();
    }

    public void bubble_sort() {// пузырьковая сортировка
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < vertex_degrees.size() - 1; i++)
                if (vertex_degrees.get(i) < vertex_degrees.get(i + 1)) {
                    int temp = vertex_degrees.get(i);
                    vertex_degrees.set(i, vertex_degrees.get(i + 1));
                    vertex_degrees.set(i + 1, temp);
                    isSorted = false;
                }
        }
        assignment_vertex_with_degrees();
    }

    public void assignment_vertex_with_degrees()
            //метод, определяющий последовательность вершин и соотнесение их со своими степенями

    {
        ArrayList<Integer> arrayList_helper = new ArrayList<Integer>();
        for(int i=0;i<vertex.length;i++)
            if(vertex[i]==4)
                arrayList_helper.add(i);
        for(int i =0;i<vertex.length;i++)
            if(vertex[i]==3)
                arrayList_helper.add(i);
        for(int i=0;i<temp_temp.size()-2;i+=2) {
            System.out.print(temp_temp.get(i) + 1);
            System.out.print("->");
            System.out.print(temp_temp.get(i+1)+1);
            System.out.println();
        }
            search_and_coloring(arrayList_helper);
    }
    public void search_and_coloring(ArrayList<Integer> arrayList_helper)
            //метод, который ищет и "раскрашивает"
    {
        int g=0,r=0,b=0,w=0,blue=0;
        color++;
        int temp_1 = arrayList_helper.get(1);
        int temp_2 = arrayList_helper.get(2);
        int temp_3 = arrayList_helper.get(3);
        int temp_4 = arrayList_helper.get(4);
        int temp_5 = arrayList_helper.get(5);
        int temp_6 = arrayList_helper.get(6);
        int temp_7 = arrayList_helper.get(7);
        int temp_8 = arrayList_helper.get(8);
        for(int i=0;i<8;i++)
        {
            if(Matrix[temp_1][temp_2]!=1) {
                color++;
                arrayList_helper.remove(i);
            }
            else
                if(Matrix[arrayList_helper.get(i)][arrayList_helper.get(i+2)]!=1)
                {
                    color++;

                }

        }



    }
    public void search_in_Matrix()
    {

    }
}


