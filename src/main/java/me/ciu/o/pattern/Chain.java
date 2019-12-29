package me.ciu.o.pattern;

public class Chain {

    public static abstract class AbstractLogger {
        public static int INFO = 1;
        public static int DEBUG = 2;
        public static int ERROR = 3;
        protected int level;
        protected AbstractLogger nextLogger;

        public void setNextLogger(AbstractLogger nextLogger) {
            this.nextLogger = nextLogger;
        }

        public void logMessage(int level, String message) {
            if (this.level <= level) {
                write(message);
            } else {
                if (nextLogger != null) {
                    nextLogger.logMessage(level, message);
                }
            }
        }

        abstract protected void write(String message);
    }

    public static class InfoLogger extends AbstractLogger {
        public InfoLogger(int level) {
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("Info Logger: " + message);
        }
    }

    public static class DebugLogger extends AbstractLogger {
        public DebugLogger(int level) {
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("Warring Logger: " + message);
        }
    }

    public static class ErrorLogger extends AbstractLogger {
        public ErrorLogger(int level) {
            this.level = level;
        }

        @Override
        protected void write(String message) {
            System.out.println("Error Logger: " + message);
        }
    }

    private static AbstractLogger getChainOfLoggers() {
        AbstractLogger infoLogger = new InfoLogger(AbstractLogger.INFO);
        AbstractLogger debugLogger = new DebugLogger(AbstractLogger.DEBUG);
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        infoLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(errorLogger);
        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();
        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");
        loggerChain.logMessage(AbstractLogger.DEBUG, "This is a debug level information.");
        loggerChain.logMessage(AbstractLogger.ERROR, "This is an error information.");
    }
}