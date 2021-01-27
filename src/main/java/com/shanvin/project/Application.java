package com.shanvin.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
public class Application {

	private static final Logger logger = LoggerFactory.getLogger("ApplicationLogger");

	public static void main(String[] args) {
		System.setProperty("logPath", getLogPath(args));
		SpringApplication application = new SpringApplication(Application.class);
		application.addListeners(new ApplicationPidFileWriter(getPidFile(args)));
		application.run(args);
	}

	private static String getLogPath(String[] args) {
		String logPath = "../logs/process";
		Optional<String> optional = Arrays.stream(args).filter(x -> x.contains("logging.file.path")).findFirst();
		if (optional.isPresent()) {
			String[] param = optional.get().split("=");
			logPath = param[1];
		}
		logger.info("logPath: {}", logPath);
		return logPath;
	}

	private static File getPidFile(String[] args) {
		return new File(String.valueOf(Paths.get(System.getProperty("user.dir"), getPidFileName(args))));
	}

	private static String getPidFileName(String[] args) {
		String pidFileName = "process.pid";
		Optional<String> optional = Arrays.stream(args).filter(x -> x.contains("logging.file.path")).findFirst();
		if (optional.isPresent()) {
			String param = optional.get();
			pidFileName = param.substring(param.indexOf("=") + 1)
					.replace("logs", "bin")
					.concat(".pid");
		}
		logger.info("pidFileName: {}", pidFileName);
		return pidFileName;
	}

}
