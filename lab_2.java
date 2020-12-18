import java.util.ArrayList;

class lab2
{
    int  Matrix[][];
     int Matrix_2[][];
    public lab2(int Matrix[][],int Matrix_2[][]) {this.Matrix = Matrix;this.Matrix_2=Matrix_2;}
    int i_temp = 0,j_temp = 0;// темпоральные переменные , хранящие в себе индексы массива , в пределах которых был найден минимальный элемент
    int temp = Integer.MAX_VALUE;//темпоральная переменная , хранящая в себе значение миимального элемента
    ArrayList<Integer> min_element = new ArrayList<Integer>();//массив,хранящий значения минимальных элементов
    public void search_min_element()
    {
        for(int i =0;i<Matrix.length;i++) {//цикл, в котром"Бежим по массиву" и ищем минимальный элемент
            for (int j = 0; j < Matrix[i].length; j++) {
                if (Matrix[i][j] < temp) {
                    temp = Matrix[i][j];
                    i_temp = i;
                    j_temp = j;
                }
            }
        }
        System.out.println();
        min_element.add(temp);
        temp=Integer.MAX_VALUE;
        first_delete_string();
    }
    //Изначально i_temp = 3;j_temp = 4;
    public void first_delete_string()//Метод, который удаляет строки, где находятся минимальные элементы
    {
        for (int j =0;j<Matrix.length;j++)//по алгоритму Прима-Красскала : "удаляем" строки с найденным минимальным эелементами
        {//т.е. присваиваем каждому элементу строк "бесконечность".
            Matrix[i_temp][j] = Integer.MAX_VALUE;
            Matrix[j_temp][j] = Integer.MAX_VALUE;
        }
        first_add_in_second_Matrix();
    }
    public void first_add_in_second_Matrix()// этом методе реализуется первое добавление "выбранных" столбцов из первой матрицы во вспомогательную.
    {
        for (int j=0;j< Matrix_2.length;j++)
            Matrix_2[j][j_temp] = Matrix[j][j_temp];
        for (int i = 0;i< Matrix_2.length;i++)
            Matrix_2[i][i_temp] = Matrix[i][i_temp];
        search_min_in_second_Matrix();
    }
    public void search_min_in_second_Matrix()//метод, в котором ищется минимальный элемент во вспомогательной матрице.
    {
        int counter=0;
        for(int i=0;i< Matrix_2.length;i++)
            for (int j =0;j< Matrix_2[i].length;j++)
                if(Matrix_2[i][j]<temp)
                {
                    temp=Matrix_2[i][j];
                    i_temp = i;
                    j_temp = j;
                    counter++;
                }
        if(temp<Integer.MAX_VALUE)
            min_element.add(temp);
        for(int j=0;j< Matrix_2.length;j++)
            Matrix_2[i_temp][j] =Integer.MAX_VALUE;
        temp=Integer.MAX_VALUE;
        if(counter>=1)
        {
            delete_and_add();
            counter =0;
        }
        else
        {
            System.out.println();
            for(int i=0;i<min_element.size();i++)
                System.out.print(min_element.get(i)+" ");
        }
    }
    public void delete_and_add()// метод, в котором происходит удаление строки первой матрицы, ставится метка на столбец первой матрицы и преперисывание столбца во впомогательную матрицу
    {
        for(int j=0;j< Matrix.length;j++)//"удаляется" строка с минимальным элементом
            Matrix[i_temp][j] = Integer.MAX_VALUE;
        for (int i =0;i<Matrix_2.length;i++)//"помеченный" столбец добавляется во вспомогательную матрицу
            Matrix_2[i][i_temp] = Matrix[i][i_temp];
        search_min_in_second_Matrix();
    }
}
public class lab_2
{
    public static void main(String[] args)
    {
        int  m = Integer.MAX_VALUE;//"бесконечность"
     int  Matrix[][] = {
             {m, 4, m, m, m, 4},
             {4, m, 2, 5, m, 4},
             {m, 2, m, 3, 5, 6},
             {m, 5, 3, m, 1, 4},
             {m, m, 5, 1, m, 3},
             {4, 4, 6, 4, 3, m},
     };
     int  Matrix_2[][]={// Вспомогательная матрица, куда будут записываться "помеченные " столбцы
             {m, m, m, m, m, m},
             {m, m, m, m, m, m},
             {m, m, m, m, m, m},
             {m, m, m, m, m, m},
             {m, m, m, m, m, m},
             {m, m, m, m, m, m}
     };
     lab2 l2 = new lab2(Matrix,Matrix_2);
     l2.search_min_element();
    }
}