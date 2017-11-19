//'main' method must be in a class 'Rextester'.
//Compiler version 1.8.0_111

import java.util.*;
import java.lang.*;
import java.io.*;

class RedMartSkiing
{  
    private static Map<String, Integer> mapResult = new HashMap<>();
    private static Map<String, String> mapArr = new HashMap<>();
    private static int[][] mat;

    private static String maxMap(Map<String, Integer> hMap) {
        String str = null;
        int big = -1;
        for (String key : hMap.keySet()) {
            if (big < hMap.get(key)) {
                big = hMap.get(key);
                str = key;
            }
        }
        return str;
    }

    private static int maxArr(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }
    
    private static boolean isInValidRowCol(int row, int col, int newRow, int newCol, Set<String> visited) {
        return newRow < 0 || newRow >= mat.length || newCol < 0 || newCol >= mat.length || visited.contains(""+newRow+newCol) || mat[newRow][newCol] >= mat[row][col];
    }
    
    private static int calc(int row, int col, Set<String> visited) {
        int currRes;
        List<String> curList = new ArrayList<>();
        String key = ""+row+col;
        if (mapResult.containsKey(key)) {
            currRes = mapResult.get(key);
        } else {
            int[] arr = new int[4]; // 4 possible directions
            Map<String, Integer> hMap = new HashMap<>();
            int arrCnt = 0;
            visited.add(key);
            if (!isInValidRowCol(row, col, row+1, col, visited)) {
                int vCalc = calc(row+1, col, visited);
                arr[arrCnt++] = 1 + vCalc;
                hMap.put(""+(row+1)+col, vCalc);
            }
            if (!isInValidRowCol(row, col, row-1, col, visited)) {
                int vCalc = calc(row-1, col, visited);
                arr[arrCnt++] = 1 + vCalc;
                hMap.put(""+(row-1)+col, vCalc);
            }
            if (!isInValidRowCol(row, col, row, col+1, visited)) {
                int vCalc = calc(row, col+1, visited);
                arr[arrCnt++] = 1 + vCalc;
                hMap.put(""+row+(col+1), vCalc);
            }
            if (!isInValidRowCol(row, col, row, col-1, visited)) {
                int vCalc = calc(row, col-1, visited);
                arr[arrCnt++] = 1 + vCalc;
                hMap.put(""+row+(col-1), vCalc);
            }
            if (arrCnt > 0) {
                currRes = maxArr(arr);
                mapArr.put(key, maxMap(hMap));
            } else {
                currRes = 1;
                mapArr.put(key, key);
            }
            mapResult.put(key, currRes);
        }
        return currRes;
    }
    
    private static int matValFromString(String key) {
        String[] rowCol = key.split("");
        return mat[Integer.parseInt(rowCol[0])][Integer.parseInt(rowCol[1])];
    }

    private static void populateMatrix(String fileName) {        
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            boolean fLine = false;
            int r = 0;
            while((line = bufferedReader.readLine()) != null) {
                if (!fLine) {
                    fLine = true;
                    String[] strRCArr = line.split(" ");
                    int row = Integer.parseInt(strRCArr[0]);
                    int col = Integer.parseInt(strRCArr[1]);
                    mat = new int[row][col];            
                } else {
                    String[] inpArr = line.split(" ");
                    for (int j = 0; j < inpArr.length; j++) {
                        mat[r][j] = Integer.parseInt(inpArr[j]);
                    }
                    r++;        
                }
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
            
    public static void main (String[] args) throws java.lang.Exception
    {
        if (args.length < 1) {
            System.out.println("File name parameter missing!\nSample: java RedMartSkiing RedMartTC1.txt");
            return;
        }
        populateMatrix(args[0]);
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[r].length; c++) {
                Set<String> visited = new HashSet<>();
                calc(r, c, visited);
            }
        }
        Map<String, String> maxKeys = new HashMap<>();
        int big = -1;
        for (String key : mapResult.keySet()) {
            int val = mapResult.get(key);
            if (big == val) {
                maxKeys.put(key, "");
            } else if (big < val) {
                big = val;
                maxKeys.clear();
                maxKeys.put(key, "");
            }
        }
        
        for (String hKey : maxKeys.keySet()) {
            String key = hKey;
            String val = mapArr.get(key);
            while (!key.equals(val)) {
                key = val;
                val = mapArr.get(key);
            }
            maxKeys.put(hKey, key);
        }                

        String emailId = null;
        int bigDiff = -1;
        for (String key : maxKeys.keySet()) {
            int matKey = matValFromString(key);
            int matVal = matValFromString(maxKeys.get(key));
            int diff = matKey - matVal;
            if (bigDiff < diff) {
                bigDiff = diff;
                System.out.println("MaxCount: "+mapResult.get(key)+" DropCount: "+diff);
                emailId = ""+mapResult.get(key)+diff;
            }
        }
        emailId += "@redmart.com";
        System.out.println("Email: "+emailId);
    }
}