import java.util.ArrayList;

abstract class Component {
    Component parent;

    abstract Component get();
    abstract void add(Component c);
    abstract Component remove();
    abstract Integer evaluate();
}

class Composite extends Component {
    private final ArrayList<Component> components = new ArrayList<>();

    @Override
    public Component get() {
        return this;
    }

    public ArrayList<Component> getComponents() { return this.components; }

    @Override
    void add(Component c) {
        components.add(c);
        c.parent = this;
    }

    @Override
    Component remove() {
        if (!components.isEmpty()) {
            return components.remove(components.size() - 1);
        }
        return null;
    }

    @Override
    Integer evaluate() {
        int result = 0;
        for (Component component : components) {
            result += component.evaluate();
        }
        return result;
    }
}

class Leaf extends Component {
    @Override
    public Component get() {
        return this;
    }

    @Override
    public void add(Component c) {
    }

    @Override
    public Component remove() {
        return null;
    }

    @Override
    Integer evaluate() {
        return 0;
    }
}

class ArithmeticExpr extends Composite {
}

class Operand extends Leaf {
    public Integer value;

    public Operand(Integer value) {
        this.value = value;
    }
}

class PlusExpr extends ArithmeticExpr {
    @Override
    public Integer evaluate() {
        int sum = 0;
        for (Component component : getComponents()) {
            sum += component.evaluate();
        }
        return sum;
    }
}

class MinusExpr extends ArithmeticExpr {
    @Override
    public Integer evaluate() {
        if (getComponents().isEmpty()) return 0;
        int result = getComponents().getFirst().evaluate();
        for (int i = 1; i < getComponents().size(); i++) {
            result -= getComponents().get(i).evaluate();
        }
        return result;
    }
}

class MultExpr extends ArithmeticExpr {
    @Override
    public Integer evaluate() {
        int result = 1;
        for (Component component : getComponents()) {
            result *= component.evaluate();
        }
        return result;
    }
}

class DivExpr extends ArithmeticExpr {
    @Override
    public Integer evaluate() {
        if (getComponents().isEmpty()) return 0;
        int result = getComponents().getFirst().evaluate();
        for (int i = 1; i < getComponents().size(); i++) {
            int divisor = getComponents().get(i).evaluate();
            if (divisor != 0) {
                result /= divisor;
            } else {
                throw new ArithmeticException("Division by zero");
            }
        }
        return result;
    }
}

class Variable extends Operand {
    public String name;

    public Variable(String name, Integer value) {
        super(value);
        this.name = name;
    }

    @Override
    public Integer evaluate() {
        return this.value;
    }
}

class Constant extends Operand {
    public Constant(Integer value) {
        super(value);
    }

    @Override
    public Integer evaluate() {
        return this.value;
    }
}

public class Main {
    public static void main(String[] args) {
        Variable x = new Variable("x", 10);
        Constant c1 = new Constant(5);
        Constant c2 = new Constant(2);

        PlusExpr plus = new PlusExpr();
        plus.add(x);
        plus.add(c1);

        MinusExpr minus = new MinusExpr();
        minus.add(plus);
        minus.add(c2);

        System.out.println("Result: " + minus.evaluate());
    }
}
