package com.company.varnaa.controller;

import com.company.varnaa.entity.prescription;
import com.company.varnaa.service.prescriptionService;
import com.company.varnaa.service.appointmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class prescriptionController {

    @Autowired
    private prescriptionService service;
    
    @Autowired
    private appointmentService service1;
    
    @GetMapping("/viewPrescription")
    public String viewPrescription(Model model) {
        String patientName = "Guest"; // Placeholder for patient name
        List<prescription> prescriptions = service.findByPatientName(patientName);
        model.addAttribute("prescriptions", prescriptions);
        return "viewPrescriotions";
    }
    
    @GetMapping("/savePrescription")
    public String saveProduct(@ModelAttribute prescription prescription,
            BindingResult result, ModelMap model,
            RedirectAttributes redirectAttributes
            ) {
        Integer id= prescription.getAppointmentID();
        service1.setPrescription("prescribed", id);
        prescription.setDoctorName("DoctorName"); // Placeholder for doctor name
       service.save(prescription);
       String message = "Prescription was successfully saved ";
       redirectAttributes.addFlashAttribute("message", message);
       redirectAttributes.addFlashAttribute("alertClass", "alert-success");
       return "redirect:/doctorAppointments";
}

    
}