import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * LeetCode 1504. Count Submatrices With All Ones
 * https://leetcode.com/problems/count-submatrices-with-all-ones/
 */
public class CountSubmatricesWithAllOnes {


    /**
     * Given an m x n binary matrix mat, 
     * return the number of submatrices that have all ones.
     * 
     * Dynamic programming (with dp int[][] array).
     * 
     * Runtime: 8 ms, faster than 85.23% of Java online submissions.
     * Memory Usage: 42.8 MB, less than 43.11% of Java online submissions.
     * 
     * 73 / 73 test cases passed.
     * Status: Accepted
     * Runtime: 8 ms
     * Memory Usage: 42.8 MB
     */
    static public int numSubmat0(int[][] mat) {

        // **** initialization ****
        var num     = 0;
        var rows    = mat.length;
        var cols    = mat[0].length;
        int[][] dp  = new int[rows][cols];
        var score   = 0;

        // **** traverse rows in int[][] mat - 0(n) ****
        for (var r = 0; r < rows; r++) {

            // **** initialize score for this row ****
            score = 0;
            
            // **** traverse columns in this row - O(m) ****
            for (var c = 0; c < cols; c++) {

                // **** update score ****
                if (mat[r][c] == 1) score++;
                else score = 0;

                // **** place score in dp ****
                dp[r][c] = score;
            }
        }

        // ???? ????
        System.out.println("<<< dp: ");
        for (var r = 0; r < rows; r++)
            System.out.println(Arrays.toString(dp[r]));

        // **** rows ****
        for (var r = 0; r < rows; r++) {

            // **** columns ****
            for (var c = 0; c < cols; c++) {

                // **** skip this column ****
                if (dp[r][c] == 0) continue;

                // **** initialize score ****
                score = dp[r][c];
            
                // ???? ????
                System.out.println("<<< score: " + score + " (" + r + "," + c + ")");

                // **** update number ****
                num += score;

                // **** repeat rows ****
                for (var d = r + 1; d < rows; d++) {

                    // **** update score ****
                    score = Math.min(score, dp[d][c]);

                    // ???? ????
                    System.out.println("<<< score: " + score + " (" + d + "," + c + ")");

                    // **** update number ****
                    num += score;
                }
            }
        }

        // **** return number of submatrices ****
        return num;
    }


    /**
     * Given an m x n binary matrix mat, 
     * return the number of submatrices that have all ones.
     * 
     * Dynamic programming (no dp int[][] array).
     * 
     * Runtime: 9 ms, faster than 82.04% of Java online submissions.
     * Memory Usage: 53.2 MB, less than 14.37% of Java online submissions.
     * 
     * 73 / 73 test cases passed.
     * Status: Accepted
     * Runtime: 9 ms
     * Memory Usage: 53.2 MB
     */
    static public int numSubmat(int[][] mat) {

        // **** initialization ****
        var rows    = mat.length;
        var cols    = mat[0].length;
        var num     = 0;

        // **** traverse rows (top to bottom) ****
        for (int r = 0; r < rows; r++) {

            // **** traverse columns (right to left) ****
            for (int c = cols - 2; c >= 0; c--) {
                if (mat[r][c] == 1)
                    mat[r][c] += mat[r][c + 1];
            }
        }
    
        // ???? ????
        System.out.println("<<<  mat:");
        for (int r = 0; r < rows; r++)
            System.out.println(Arrays.toString(mat[r]));

        // **** traverse rows (top to bottom) ****
        for (int r = 0; r < rows; r++) {

            // **** traverse columns (left to right) ****
            for (int c = 0; c < cols; c++) {

                // **** skip this column ****
                if (mat[r][c] == 0) continue;

                // **** initialize score ****
                var score = mat[r][c];

                // ???? ????
                System.out.println("<<< score: " + score + " (" + r + "," + c + ")");

                // **** traverse rows (top to bottom) ****
                for (int d = r; d < rows; d++) {

                    // **** skip this cell ****
                    if (mat[d][c] == 0) break;

                    // **** update score ****
                    score = Math.min(score, mat[d][c]);

                    // ???? ????
                    System.out.println("<<< score: " + score + " (" + d + "," + c + ")");

                    // **** update number ****
                    num += score;
                }
            }
        }

        // **** return number of submatrices ****
        return num;
    }


    /**
     * Test scaffold
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read r and c ****
        int[] rc = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** for ease of use ****
        int rows = rc[0];
        int cols = rc[1];

        // **** create matrix ****
        int[][] mat = new int[rows][];

        // **** populate matrix ****
        for (int r = 0; r < rows; r++)
            mat[r] = Arrays.stream(br.readLine().trim().split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< rows: " + rows);
        System.out.println("main <<< cols: " + cols);
        System.out.println("main <<<  mat: ");
        for (int r = 0; r < rows; r++)
            System.out.println(Arrays.toString(mat[r]));

        // **** call function of interest and display result ****
        System.out.println("main <<< numSubmat0: " + numSubmat0(mat));


        // // ???? ????
        // System.out.println("main <<<  mat: ");
        // for (int r = 0; r < rows; r++)
        //     System.out.println(Arrays.toString(mat[r]));


        // **** call function of interest and display result ****
        System.out.println("main <<<  numSubmat: " + numSubmat(mat));


        // // ???? ????
        // System.out.println("main <<<  mat: ");
        // for (int r = 0; r < rows; r++)
        //     System.out.println(Arrays.toString(mat[r]));
    }

}