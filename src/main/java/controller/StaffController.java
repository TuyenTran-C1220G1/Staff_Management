package controller;

import model.Staff;
import model.StaffForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.IStaffService;
import service.StaffService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    //    private final IStaffService iStaffService = new StaffService();
    @Value("${file-update}")
    private String fileUpdate;

    @GetMapping("")
    public String index(Model model) {
        List<Staff> staffList = staffService.getAllStaff();
        model.addAttribute("staffs", staffList);
        return "/index";
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("staffForm", new StaffForm());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveStaff(@ModelAttribute StaffForm staffForm) {
        MultipartFile multipartFile = staffForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(staffForm.getAvatar().getBytes(), new File(fileUpdate + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Staff staff = new Staff(staffForm.getName(),
                staffForm.getBirthday(), fileName, staffForm.getGender());
        staffService.create(staff);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("staffForm", staffForm);
        modelAndView.addObject("message", "Created new staff successfully !");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        Staff editStaff = staffService.findById(id);
        StaffForm editStaffForm=new StaffForm(editStaff.getId(),editStaff.getName(),editStaff.getBirthday(),editStaff.getGender());
        model.addAttribute("staffForm", editStaffForm);
        return "/edit";
    }

    @PostMapping("/update")
    public ModelAndView editStaff(@ModelAttribute StaffForm staffForm) {
        MultipartFile multipartFile = staffForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(staffForm.getAvatar().getBytes(), new File(fileUpdate + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Staff staff = new Staff(staffForm.getId(),staffForm.getName(),
                staffForm.getBirthday(), fileName, staffForm.getGender());
        staffService.update(staff);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("staffForm", staffForm);
        modelAndView.addObject("message", "Created new staff successfully !");
        return modelAndView;
    }
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, RedirectAttributes redirect) {
        staffService.delete(id);
        redirect.addFlashAttribute("success", "Removed product successfully!");
        return "redirect:/staff";
    }

//    @GetMapping("/{id}/view")
//    public String view(@PathVariable int id, Model model) {
//        model.addAttribute("staff", staffService.findById(id));
//        return "/view";
//    }
    @GetMapping("/search")
    public String search(@RequestParam String name, Model model) {
        List<Staff> staffList = staffService.findByName(name);
        model.addAttribute("staffs", staffList);
        return "/index";
    }
}
