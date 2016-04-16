package cn.fh.interpreter;

/**
 * 抽象语法树
 * Created by whf on 4/16/16.
 */
public class ASTree {
    private Node root;

    public ASTree(Lexer lexer) {
        this.root = buildTree(lexer);
    }


    /**
     * 构造语法树
     * @return
     */
    private Node buildTree(Lexer lexer) {
        // 读取下一个操作数
        Token num = lexer.read();
        // 读取下一个操作符
        Token opt = lexer.read();

        // 构造根结点
        Node root = new Node();
        root.token = opt;

        // 构造左孩子
        Node left = new Node();
        left.token = num;
        left.value = Integer.parseInt(num.getToken());
        root.left = left;

        // 右孩子
        Node right;

        // 预读取下一个操作符
        Token nextOpt = lexer.read(1); // 可能会是EOF

        // 比较下一个操作符与当前操作符的优先级
        int comp = nextOpt.compareTo(opt);
        // 下一个优先级高
        if (comp > 0) {
            // 右孩子也是树
            // 递归构造右子树
            right = buildTree(lexer);
        } else if (comp == 0) {
            // 同等级
            right = buildTree(lexer);
        } else {
            // 下一个优先级低
            right = new Node();
            right.token = lexer.read();
            right.value = Integer.parseInt(right.token.getToken());

            root.right = right;
            evaluateNode(root);

            // 判断是否还有下一个操作符
            if (nextOpt.getType() != Token.TokenType.EOF) {
                // 有
                // 构造新树
                // 并将当前树变为新树的左子树
                Node nextRoot = new Node();
                nextRoot.token = lexer.read();
                nextRoot.left = root;
                nextRoot.right = buildTree(lexer);



                root = nextRoot;
                evaluateNode(root);

                return root;
            }


        }

        root.right = right;

        evaluateNode(root);

        return root;

    }

    public int result() {
        return root.value;
    }

    /**
     * 对节点进行求值
     * @param node
     */
    private void evaluateNode(Node node) {
        switch (node.token.getToken()) {
            case "+":
                node.value = node.left.value + node.right.value;
                break;

            case "*":
                node.value = node.left.value * node.right.value;
                break;

            case "-":
                node.value = node.left.value - node.right.value;
                break;

        }
    }


    private class Node {
        public Token token;
        private Node left;
        private Node right;

        private int value;
    }
}
