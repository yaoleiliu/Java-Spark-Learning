package ObjectAndClass.Factory;

public class FileLoggerFactory implements LoggerFactory{

    @Override
    public Logger caeateLogger() {
        Logger logger = new FileLogger();

        return logger;
    }
}
