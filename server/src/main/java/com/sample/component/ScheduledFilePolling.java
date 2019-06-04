package com.sample.component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sample.model.Server;
import com.sample.services.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@Component
@ConditionalOnExpression("'${myapp.enable.scheduling}'=='true'")
public class ScheduledFilePolling {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledFilePolling.class);

    @Value("${myapp.pickup.location}")
    private String filePath;


    @Autowired
    ServerService serverService;

    @Scheduled(fixedRateString = "${myapp.pickup.timer}")
    public void pollPickupDirectory() {
        logger.debug("Checking for files in: " + filePath);

        File watchedDir = new File(filePath);
        List<File> xmlFiles = getXMLFiles(watchedDir);
        String errorPath = filePath + "/error/";
        String completedPath = filePath + "/done/";

        for (File file : xmlFiles) {
            File tempFile = new File(file.getAbsolutePath().toLowerCase().replace(".xml", ".tmp"));
            if(!file.renameTo(tempFile)){
                logger.error("Unable to create temp file :" + tempFile);
                return;
            }
            Server server = unMarshalServer(tempFile, Server.class);
            try {
                serverService.addNewServer(server);
            } catch (DataIntegrityViolationException dte) {
                logger.error("Problem saving server: { id: " + server.getId()
                        + ", name: " + server.getName() + "} ID already exists. Moving file to error dir.", dte);
                if(!tempFile.renameTo(new File(createDirIfRequired(errorPath) + file.getName()))){
                    logger.error("Unable to move file [ " + tempFile + " ] to error directory.");
                }
            }
            logger.debug("Server: { id: " + server.getId()
                    + ", name: " + server.getName() + "} processed. Moving file to done dir.");
            if(!tempFile.renameTo(new File(createDirIfRequired(completedPath) + file.getName()))){
                logger.error("Unable to move file [ " + tempFile + " ] to done directory.");
            }
        }

    }

    private String createDirIfRequired(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            if(!dir.mkdir()){
                logger.error("Unable to create directory :" + path);
            }
        }
        return path;
    }

    private List<File> getXMLFiles(File dir) {
        List<File> fileList = new ArrayList<>();

        if (!dir.isDirectory()) {
            logger.error(dir.toString() + " is not a directory!");
            return fileList;
        }
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".xml")) {
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }

    private <T extends Object> T unMarshalServer(File file, Class<T> type) {
        logger.debug("unMarshal " + file.getAbsolutePath());
        T unMarsheled = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(type);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            unMarsheled = (T) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException jaxbe) {
            logger.error("Error parsing XML", jaxbe);
        } catch (Exception e) {
            logger.error("Error processing files", e);
        }
        return unMarsheled;
    }
}
