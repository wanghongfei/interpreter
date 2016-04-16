package cn.fh.interpreter;

/**
 * Created by whf on 4/16/16.
 */
public class Token {
    /**
     * token的类型
     */
    private TokenType type;
    /**
     * token字面值
     */
    private String token;

    public static Token EOF = new Token("EOF", TokenType.EOF);

    public Token(String token, TokenType type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return this.token;
    }

    public TokenType getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (false == o instanceof Token) return false;

        Token other = (Token) o;
        return this.token.equals(other.token) && this.type == other.type;

    }


    @Override
    public String toString() {
        return "{type: " + this.type.name() + ", val: " + token + "}";
    }

    public enum TokenType {
        /**
         * 关键字
         */
        KEYWORD,
        /**
         * 标识符
         */
        IDENTIFIER,
        /**
         * 数字
         */
        NUMBER,
        /**
         * 操作符
         */
        OPERATOR,
        EOF;

    }

    public int compareTo(Token o) {
        if (this.getToken().equals("+") && o.getToken().equals("*")) {
            return -1;
        }
        if (this.getToken().equals("+") && o.getToken().equals("+")) {
            return 0;
        }
        if (this.getToken().equals("*") && o.getToken().equals("+")) {
            return 1;
        }

        return -100;
    }

}
