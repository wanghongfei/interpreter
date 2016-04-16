package cn.fh.interpreter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 词法分析器
 * Created by whf on 4/16/16.
 */
public class Lexer {
    private ArrayList<Token> tokens = new ArrayList<>();

    //private String expPat = "\\s*[a-z]+\\s*|\\s*[0-9]+\\s*|=|\\s*\\+\\s*";
    private String expPat = "(\\s*[a-z]+\\s*)|(\\s*[0-9]+\\s*)|(=|\\+|\\*|-)";
    private Pattern pattern = Pattern.compile(expPat);

    private int index = 0;

    public Token read() {
        if (index >= tokens.size()) {
            return Token.EOF;
        }

        return tokens.get(index++);
    }

    public Token read(int next) {
        if (index + next >= tokens.size()) {
            return Token.EOF;
        }

        return tokens.get(index + next);
    }


    public void parseLine(String line) {
        Matcher matcher = pattern.matcher(line);

        int start = 0;
        int LEN = line.length();

        while (start < LEN) {
            matcher.region(start, LEN);

            if (matcher.lookingAt()) {

                tokens.add(parseToken(matcher));
                start = matcher.end();
            }
        }
    }

    private Token parseToken(Matcher matcher) {
        for (int ix = 1 ; ix <= matcher.groupCount() ; ++ix) {
            String val = matcher.group(ix);

            if (null != val) {
                val = val.trim();
                switch (ix) {
                    case 1:
                        if (val.equals("int")) {
                            return new Token(val, Token.TokenType.KEYWORD);
                        } else {
                            return new Token(val, Token.TokenType.IDENTIFIER);
                        }

                    case 2:
                        return new Token(val, Token.TokenType.NUMBER);

                    case 3:
                        return new Token(val, Token.TokenType.OPERATOR);
                }
            }
        }


        return null;
    }

    public void showTokens() {
        System.out.println(tokens);
    }
}
