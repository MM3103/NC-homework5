package hw5.controller;

import hw5.database.Employees;
import hw5.email.Sender;
import hw5.model.Email;
import hw5.model.Employee;
import hw5.model.RequestData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

@Controller
public class EmployeeController {

    @GetMapping("/start")
    public String getStartPage(Model model) {
        return "start";
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "add";
    }

    @GetMapping("/find")
    public String getEmployeeInfo(Model model) {
        model.addAttribute("requestData", new RequestData());
        return "find";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute Employee employee) {
        Employees.addEmployee(employee);
        return "info";
    }

    @PostMapping("/find")
    public String getEmployeeInfo(@ModelAttribute RequestData requestData, Model model, @RequestHeader(value = "User-Agent") String userAgent) {
        Employee employee = Employees.getEmployeeInfo(requestData);
        if (employee == null) {
            return "error-request";
        }
        requestData.setTime(new Date());
        requestData.setUserAgent(userAgent);
        model.addAttribute(requestData);
        model.addAttribute(employee);
        return "info";
    }

    @PostMapping("/uploading-from-file")
    public String uploadFromFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, Model model) throws IOException {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Select a file to upload and try again.");
            return "redirect:/start";
        }

        Employee employee = Employees.uploadEmployeeFromFile(file);

        if (employee == null) {
            return "error-uploading";
        }
        model.addAttribute("employee", employee);
        return "add";
    }

    @GetMapping("/send-email")
    public String sendEmail(Model model, @ModelAttribute Email email, @RequestParam(value = "email", required = false) String emailAddress) {
        System.out.println(emailAddress);
        model.addAttribute("email", new Email(emailAddress));
        return "send-email";
    }

    @PostMapping("/send-email")
    public String sendEMail(@ModelAttribute Email email) throws MessagingException {
        Sender sender = new Sender("homework0005@gmail.com","homework1234");
        sender.sendMessage(email);
        return "redirect:/start";
    }
}
