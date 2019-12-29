package me.ciu.o.pattern;

public class Iterator {

    public interface ItemIterator {
        public boolean hasNext();

        public Object next();
    }

    public interface Container {
        public ItemIterator getIterator();
    }

    public static class NameRepository implements Container {
        public String names[] = {"Robert", "John", "Julie", "Lora"};

        @Override
        public ItemIterator getIterator() {
            return new NameIterator();
        }

        private class NameIterator implements ItemIterator {
            int index;

            @Override
            public boolean hasNext() {
                if (index < names.length) {
                    return true;
                }
                return false;
            }

            @Override
            public Object next() {
                if (this.hasNext()) {
                    return names[index++];
                }
                return null;
            }
        }
    }

    public static void main(String[] args) {
        NameRepository namesRepository = new NameRepository();
        for (ItemIterator iter = namesRepository.getIterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            System.out.println("Name : " + name);
        }
    }
}