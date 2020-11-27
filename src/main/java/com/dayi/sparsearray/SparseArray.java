package com.dayi.sparsearray;

import java.io.*;
import java.util.Vector;

/**
 * 稀疏数组demo，压缩11*11的棋盘
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2020-11-25 14:57
 */
public class SparseArray {

    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11; 0：表示没有棋子，1表示黑子，2表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        // 输出原始的二维数组
        System.out.println("原始的二维数组~~~");
        for (int[] rows : chessArr1) {
            for (int data : rows) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将二维数组 转为 稀疏数组
        // 1.先遍历二维数组，得到原始二维数组中非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (0 != chessArr1[i][j]) {
                    sum++;
                }
            }
        }
        System.out.println("原始二维数据有效数据个数为：" + sum);

        // 2.创建稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;
        // 遍历原始二维数组，将非0值存放到sparseArr中
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (0 != chessArr1[i][j]) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        // 输出稀疏数组
        System.out.println("压缩后的稀疏数组~~~");
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                System.out.printf("%d\t", sparseArr[i][j]);
            }
            System.out.println();
        }

        /*-------------------------------华丽的分割线----------------------------------------*/

        System.out.println("*************拓展**************");
        // 1.拓展：将稀疏数据进行文件存盘持久化
        try {
            FileWriter fileWriter = new FileWriter("d:\\sparseArr.txt");
            for (int i = 0; i < sparseArr.length; i++) {
                for (int j = 0; j < sparseArr[i].length; j++) {
                    // 将int转String
                    String value = String.valueOf(sparseArr[i][j] );
                    fileWriter.write(value + "\t");
                }
                fileWriter.write("\r\n");
            }
            fileWriter.close();
            System.out.println("已将稀疏数组进行存盘持久化");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2.将稀疏数组从磁盘文件中读出，还原为二维数组并显示
        // 存储文件中的每一行
        Vector<String> vectorArr = new Vector<>();
        try {
            FileInputStream inputStream = new FileInputStream("d:\\sparseArr.txt");
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);
            // 按行读取
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (null != line) {
                    vectorArr.add(line);
                }
            }
            bufferedReader.close();
            reader.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 3.构建稀疏数组
        int sparseRows = vectorArr.size();
        int[][] sparseArr2 = new int[sparseRows][3];
        for (int i = 0; i < vectorArr.size(); i++) {
            String[] split = vectorArr.get(i).split("\t");
            for (int j = 0; j < split.length; j++) {
                sparseArr2[i][j] = Integer.parseInt(split[j]);
            }
        }
        // 输出读取出来的稀疏数组
        System.out.println("输出读取出来的稀疏数组...");
        for (int i = 0; i < sparseArr2.length; i++) {
            for (int j = 0; j < sparseArr2[i].length; j++) {
                System.out.printf("%d\t", sparseArr2[i][j]);
            }
            System.out.println();
        }

        /*-------------------------------华丽的分割线----------------------------------------*/
        System.out.println("***********************************");

        // 将稀疏数组转为二维数组
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        for (int i = 1; i < sparseArr2.length; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }

        // 输出解压后的原始二维数组
        System.out.println("解压后的原始二维数组~~~");
        for (int i = 0; i < chessArr2.length; i++) {
            for (int j = 0; j < chessArr2[i].length; j++) {
                System.out.printf("%d\t", chessArr2[i][j]);
            }
            System.out.println();
        }

    }

}
