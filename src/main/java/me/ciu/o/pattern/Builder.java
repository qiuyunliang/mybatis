package me.ciu.o.pattern;

public abstract class Builder {
    public abstract void head();

    public abstract void body();

    public abstract void foot();

    public static class ExampleBuilder extends Builder {
        @Override
        public void head() {
            System.out.println("build head.");
        }

        @Override
        public void body() {
            System.out.println("build body.");
        }

        @Override
        public void foot() {
            System.out.println("build foot.");
        }

        public String build() {
            return "instance";
        }
    }

    public static class Director {
        private Builder builder;

        public Builder getBuilder() {
            return builder;
        }

        public void setBuilder(Builder builder) {
            this.builder = builder;
        }

        public void construct() {
            builder.head();
            builder.body();
            builder.foot();
        }
    }

    public static void main(String[] args) {
        ExampleBuilder builder = new ExampleBuilder();  // 确定具体的建造方法
        Director director = new Director();  // 建造实例，Director控制流程，ExampleBuilder执行建造方法
        director.setBuilder(builder);
        director.construct();
        String result = builder.build();  // 得到最终产品
    }
}