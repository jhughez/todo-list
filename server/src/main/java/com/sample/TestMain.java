package com.sample;


//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
//import com.sample.model.Server;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;

public class TestMain {

//    private static final Logger logger = LoggerFactory.getLogger(TestMain.class);
//
//    private String filePath = "C:\\Users\\hughesj\\Documents\\pickup";


    public static void main(String[] args){
//        TestMain tm = new TestMain();
//        tm.reportCurrentTime();
    }

//    public void reportCurrentTime() {
//        logger.debug("Checking for files in: " + filePath);
//
//        Path watchedDir = Paths.get(filePath);
//        if (!Files.isDirectory(watchedDir)) {
//            logger.error(filePath + " is not a directory!");
//            return;
//        }
//
//        try {
//            Files.list(watchedDir)
//                    .filter(p -> p.toString().toLowerCase().endsWith(".xml"))
//                    .map(p -> createTempFile(p))
//                    .filter(p -> p != null)
//                    .map(p -> unMarshalServer(p, Server.class))
//                    .filter(s -> s != null)
//                    .forEach( (Server s) -> {
//                        if(s != null){
//                            logger.error(s.getName());
//                        }
//                    });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private Path createTempFile(Path path) {
//        Path tempFile = null;
//        try {
//            tempFile = Files.move(path, Paths.get(path.toString().toLowerCase().replace(".xml", "tmp")),
//                    StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            logger.error("Can't move file " + path.toString());
//        }
//        return tempFile;
//    }
//
//
//    private <T extends Object> T unMarshalServer(Path path, Class<T> type) {
//        logger.debug("unMarshal " + path.toString() );
//        T unMarsheled = null;
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(type);
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            unMarsheled =  (T) jaxbUnmarshaller.unmarshal(path.toFile());
//        } catch (JAXBException jaxbe) {
//            logger.error("Error parsing XML", jaxbe);
//        } catch (Exception e) {
//            logger.error("Error processing files", e);
//        }
//        return unMarsheled;
//    }
}
