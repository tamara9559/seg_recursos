package controller;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/apiarchivos")
public class ArchivoController {

    private final ResourceLoader resourceLoader;

    public ArchivoController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/saludo/{nombre}/{apellido}")
    public String holaNombre(@PathVariable String nombre, @PathVariable String apellido){
        return "Hola "+nombre+ " "+apellido;
    }

    @GetMapping("/html")
    public String getHtml() {
        return "hola.html";
    }

    @GetMapping("/xml")
    @ResponseBody
    public ResponseEntity<Resource> getXml() {
        Resource resource = resourceLoader.getResource("classpath:static/mensaje_hola.xml");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(resource);
    }

    @GetMapping("/pdf")
    @ResponseBody
    public ResponseEntity<Resource> getPdf() {
        Resource resource = resourceLoader.getResource("classpath:static/Apis_s.pdf");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping(value = "/foto", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> getPng() {
        Resource resource = resourceLoader.getResource("classpath:static/photo9.png");
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(resource);
    }
}
