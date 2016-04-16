package cn.fh.interpreter.test;

import cn.fh.interpreter.ASTree;
import cn.fh.interpreter.Lexer;
import org.junit.Test;

/**
 * Created by whf on 4/16/16.
 */
public class RegularExTest {

    @Test
    public void testLexer() {
        String exp = "int i = 0 i = i + 1";
        Lexer lexer = new Lexer();
        lexer.parseLine(exp);

        lexer.showTokens();;
    }

    @Test
    public void testASTree() {
        String exp = "16 + 2 * 3";
        Lexer lexer = new Lexer();
        lexer.parseLine(exp);
        lexer.showTokens();

        ASTree tree = new ASTree(lexer);
        int result = tree.result();
        System.out.println(result);
    }

    @Test
    public void testASTreeComplicate() {
        String exp = "10 * 20 + 1 + 10 + 2 * 4";
        Lexer lexer = new Lexer();
        lexer.parseLine(exp);
        lexer.showTokens();

        ASTree tree = new ASTree(lexer);
        int result = tree.result();
        System.out.println(result);
    }

    @Test
    public void testASTreeComplicate2() {
        String exp = "20 + 10 + 10 * 20 + 1 + 10 + 2 * 4";
        Lexer lexer = new Lexer();
        lexer.parseLine(exp);
        lexer.showTokens();

        ASTree tree = new ASTree(lexer);
        int result = tree.result();
        System.out.println(result);
    }

    @Test
    public void testASTreeComplicateWithSub() {
        String exp = "20 + 10 + 10 * 20 + 1 - 10 + 2 * 4";
        Lexer lexer = new Lexer();
        lexer.parseLine(exp);
        lexer.showTokens();

        ASTree tree = new ASTree(lexer);
        int result = tree.result();
        System.out.println(result);
    }

}
