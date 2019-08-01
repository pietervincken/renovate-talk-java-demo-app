package demo.app.controllers;

import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class InfoController {

    @GetMapping("/info")
    public String getInfo(Model model) throws Exception {
        model.addAttribute("appVersion", getClass().getPackage().getImplementationVersion());

        model.addAttribute("isDocker", Files.exists(Paths.get("/.dockerenv")));
        model.addAttribute("isKube", Files.exists(Paths.get("/var/run/secrets/kubernetes.io")));
        
        model.addAttribute("hostname", InetAddress.getLocalHost().getHostName());   
        model.addAttribute("osName", System.getProperties().getProperty("os.name"));   
        model.addAttribute("osVersion", System.getProperties().getProperty("os.version"));   
        model.addAttribute("osArch", System.getProperties().getProperty("os.arch"));   
        model.addAttribute("cpuCount", Runtime.getRuntime().availableProcessors());        

        model.addAttribute("javaVersion", System.getProperties().getProperty("java.version"));   
        model.addAttribute("javaVendor", System.getProperties().getProperty("java.vm.name"));   

        Map<String, String> sortedVars = new TreeMap<String, String>(System.getenv());
        model.addAttribute("envVars", sortedVars);

        return "info";
    }
}