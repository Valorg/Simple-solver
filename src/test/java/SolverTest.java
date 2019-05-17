import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void solve() {
        String simple = Solver.Solve("5000+10*25 - 70 / 35");
        assertEquals("253", simple);
        String doub = Solver.Solve("3.4 + 4.6 * 2.0    / 0.643 - 5+2 *2.6-  8/8");
        assertEquals("16.908", doub);
    }

    @Test
    void order() {
        try {
            String solve = Solver.Solve("10 + * 400 + 8");
        } catch (NumberFormatException ex){
            assertEquals("empty String", ex.getMessage());
        }

        try {
            String solve = Solver.Solve("10 +* 400 + 8");
        } catch (NumberFormatException ex){
            assertEquals("For input string: \"*\"", ex.getMessage());
        }
    }

    @Test
    void space() {
        try {
            String solve = Solver.Solve("10 00 + 8");
        } catch (NumberFormatException ex){
            assertEquals("For input string: \"10 00\"", ex.getMessage());
        }
    }

    @Test
    void dots() {
        try {
            String solve = Solver.Solve("100. + 8");
        } catch (NumberFormatException ex){
            assertEquals("100. is not valid double", ex.getMessage());
        }
        try {
            String solve = Solver.Solve(".100 + 8");
        } catch (NumberFormatException ex){
            assertEquals(".100 is not valid double", ex.getMessage());
        }
    }

    @Test
    void symbol() {
        try {
            String solve = Solver.Solve("1jmfjk00 + 8");
        } catch (NumberFormatException ex){
            assertEquals("For input string: \"1jmfjk00\"", ex.getMessage());
        }
    }
}