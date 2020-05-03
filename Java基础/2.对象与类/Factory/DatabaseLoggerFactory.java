package ObjectAndClass.Factory;

public class DatabaseLoggerFactory implements LoggerFactory{

    @Override
    public Logger caeateLogger() {
        Logger logger = new DatabaseLogger();

        return logger;
    }
}
