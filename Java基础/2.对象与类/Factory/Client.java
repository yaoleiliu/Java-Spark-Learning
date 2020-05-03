package ObjectAndClass.Factory;

public class Client {
    public static void main(String[] args){
        LoggerFactory factory;
        Logger logger;
        factory = new FileLoggerFactory();
        LoggerFactory factory2 = new DatabaseLoggerFactory();
        logger = factory.caeateLogger();
        logger.writeLog();
        Logger logger2 = factory2.caeateLogger();
        logger2.writeLog();
    }
}
